package com.app.rys.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rys.enums.BookingState;
import com.app.rys.enums.SeatState;
import com.app.rys.models.Booking;
import com.app.rys.models.Building;
import com.app.rys.models.Floor;
import com.app.rys.models.Seat;
import com.app.rys.models.User;
import com.app.rys.repository.BookingRepository;
import com.app.rys.repository.BuildingRepository;
import com.app.rys.repository.SeatRepository;
import com.app.rys.repository.UserRepository;
import com.app.rys.utility.UtilityRYS;

/**
 * Servicio de reservación
 * 
 * @author Naoufal
 *
 */
@Service
public class BookingService implements IBookingService {

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private ISeatService seatService;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Booking createReservation(Long id, String seatNumber, String adrress, String floorNumber, String city,
			Date reservationDate) {
		try {
			Building building = buildingRepository.findByCityAndAdrress(city, adrress).get(0);
			Optional<Floor> oFloor = building.getFloors().stream()
					.filter(floor -> floor.getFloorNumber().equals(floorNumber)).findFirst();

			if (oFloor.isPresent()) {
				// puedes hacer try/catch
				Floor floor = oFloor.orElseThrow();
				Optional<Seat> oSeat = floor.getSeats().stream().filter(seat -> seat.getSeatNumber().equals(seatNumber))
						.findFirst();

				if (oSeat.isPresent()) {
					Seat existedSeat = oSeat.get();

					Long reservationCounter = bookingRepository.count();
					Booking booking = new Booking(UtilityRYS.generateBookingCode(reservationCounter), reservationDate,
							BookingState.PENDIENTE.getState());
					// Floor floor = existedSeat.getFloor();
					// Building building = floor.getBuilding();
					booking.setInformacionDeReserva(
							new StringBuilder(existedSeat.getSeatNumber() + "/" + floor.getFloorNumber() + " "
									+ building.getAdrress() + ", " + building.getCity()).toString());
					existedSeat.setState(SeatState.NO_DISPONIBLE.getState());
					List<User> users = userRepository.findAll();
					User user = users.get(0);
					booking.setUser(user);
					try {
						userRepository.save(user);
						bookingRepository.save(booking);
						seatRepository.save(existedSeat);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return booking;

				}
			}
		} catch (Exception e) {
			System.out.println("Seat not available");
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Booking deletReservation(Long id) {
		try {
			if (bookingRepository.existsById(id)) {
				Booking booking = bookingRepository.getReferenceById(id);
				String city = (booking.getInformacionDeReserva().split(",")[1]).substring(1);
				String adrress = (booking.getInformacionDeReserva().split(" ")[1] + " "
						+ booking.getInformacionDeReserva().split(" ")[2] + " "
						+ booking.getInformacionDeReserva().split(" ")[3]).split(",")[0];
				String floorNumber = booking.getInformacionDeReserva().split("/")[1].split(" ")[0];
				String seatNumber = booking.getInformacionDeReserva().split("/")[0];
				bookingRepository.delete(booking);
				try {
					Optional<Seat> oSeat = seatService.getSeats(city, adrress, floorNumber).stream()
							.filter(seat -> seat.getSeatNumber().equals(seatNumber)).findFirst();
					if (oSeat.isPresent()) {
						Seat seat = oSeat.get();
						seat.setState(SeatState.DISPONIBLE.getState());
						seatRepository.save(seat);
					}
				} catch (Exception e) {
					System.out.println("unknown seat");
				}
				return booking;
			}
		} catch (Exception e) {
			System.out.println("Booking not available");
		}
		return null;
	}

	// nuevo para que dependa la reserva de la fecha
	// Recuperar todas las reservas existentes para la silla específica.
	// Verificar si existe alguna reserva con la fecha dada.
	// Devolver un valor booleano indicando si la fecha está disponible o no.
	public boolean checkDateAvailability(Date reservationDate, String informacionDeReserva) {
		List<Booking> bookings = bookingRepository.findByReservationDateAndInformacionDeReserva(reservationDate,
				informacionDeReserva);
		return bookings.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<Booking> getReservation() {
		return bookingRepository.findAll();
	}

}

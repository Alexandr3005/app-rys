package com.app.rys.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.app.rys.controller.UserController;
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



@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")

/**
 * Servicio de reservación
 * 
 * 
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
	@Autowired
	private UserRepository repository;
	
	private Long userId;
	
	
	@PostMapping("/login/")
	public ResponseEntity<User> loginUser(@RequestBody User userData) {
	    User user = repository.findByEmail(userData.getEmail());

	    if (user != null && user.getPassword().equals(userData.getPassword())) {
	    	 userId = user.getId();
	    	  //userData.setId(userId);
	        return ResponseEntity.ok(user);
	    } else {
	        return ResponseEntity.status(500).build();
	    }
	}
	

	@PostMapping("/register/")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
	    // Verificar si el correo ya existe
	    User existingUser = userRepository.findByEmail(user.getEmail());
	    if (existingUser != null) {
	        return ResponseEntity.badRequest().body("Email already exists");
	    }

	    try {
	        User savedUser = userRepository.save(user);
	        userId = savedUser.getId(); // almacenamiento del ID del usuario registrado
	        return ResponseEntity.ok(savedUser);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).build();
	    }
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userData, HttpServletRequest request) {
	    // Obtener el usuario actual de la base de datos
	    Optional<User> optionalUser = userRepository.findById(id);
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        // Actualizar los datos del usuario
	        user.setEmail(userData.getEmail());
	        user.setPassword(userData.getPassword());
	        // ...
	        // Guardar los cambios en la base de datos
	        userRepository.save(user);
	        return ResponseEntity.ok(user);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}



	public Booking createReservation(String seatNumber, String adrress, String floorNumber, String city, Date reservationDate) {
	    
		    Building building = buildingRepository.findByCityAndAdrress(city, adrress).get(0);
		    Optional<Floor> oFloor = building.getFloors().stream()
		            .filter(floor -> floor.getFloorNumber().equals(floorNumber)).findFirst();

	    if (oFloor.isPresent()) {
	        Floor floor = oFloor.orElseThrow();
	        Optional<Seat> oSeat = floor.getSeats().stream()
	                .filter(seat -> seat.getSeatNumber().equals(seatNumber)).findFirst();

	        if (oSeat.isPresent()) {
	            Seat existedSeat = oSeat.get();

	            Long reservationCounter = bookingRepository.count();
	            Booking booking = new Booking(UtilityRYS.generateBookingCode(reservationCounter), reservationDate,
	                    BookingState.PENDIENTE.getState());

	            booking.setInformacionDeReserva(new StringBuilder(existedSeat.getSeatNumber() + "/" + floor.getFloorNumber()
	                    + " " + building.getAdrress() + ", " + building.getCity()).toString());
	            existedSeat.setState(SeatState.NO_DISPONIBLE.getState());


	            User user = userRepository.findByid(userId); // Buscar usuario por userId en la base de datos
	        
	            if (user == null) {
	                // Si el usuario no se encuentra en la base de datos, puedes lanzar una excepción
	                // throw new RuntimeException("User not found");
	            }

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

/*

	@Override
	public Booking updateReservationStatus(Long id, String bookingState) {
	    Booking booking = (Booking) bookingRepository.findByid(id);
	    booking.setBookingState(bookingState);
	    return bookingRepository.save(booking);
	}
	*/
	
	public Booking updateReservationStatus(Long id, String bookingState) {
	    Optional<Booking> optionalBooking = bookingRepository.findById(id);
	   
	        Booking booking = optionalBooking.get();
	        booking.setBookingState(bookingState);
	        return bookingRepository.save(booking);
	   
	}
	
	
	/*
	User user = userRepository.findById(userId).orElse(null);
	if (user == null) {
		throw new Exception("User not found");
	}
	
	*/

}

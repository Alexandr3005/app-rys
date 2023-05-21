package com.app.rys.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rys.dto.FloorDTO;
import com.app.rys.models.Booking;
import com.app.rys.models.Building;
import com.app.rys.models.Seat;
import com.app.rys.repository.BookingRepository;
import com.app.rys.repository.BuildingRepository;
import com.app.rys.service.IBookingService;
import com.app.rys.service.ISeatService;
import com.app.rys.utility.DateUtility;

@RestController
@RequestMapping("/api/reservation")
public class AppController {

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private ISeatService seatService;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BookingRepository bookingRepository;

	
	// Crear reserva asociada a un usuario y a una fecha
	
	@PostMapping("/reserve/")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Booking> createReservation(@RequestBody BookingPostBody bookingPostBody,
			HttpServletRequest request) throws ParseException {
		Date reservationDate = DateUtility.convertStringToDate(bookingPostBody.getReservationDate());
		String informacionDeReserva = new StringBuilder(bookingPostBody.getSeatNumber() + "/" + 
														bookingPostBody.getFloorNumber() + " "+
														bookingPostBody.getAddress() + ", " +
														bookingPostBody.getCity().toUpperCase()).toString();
		
		if (!bookingService.checkDateAvailability(reservationDate, informacionDeReserva)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Booking booking = bookingService.createReservation(bookingPostBody.getSeatNumber(),
				bookingPostBody.getAddress(), bookingPostBody.getFloorNumber(), 
				bookingPostBody.getCity().toUpperCase(),
				reservationDate);
		if (booking != null) {
			return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Booking> deleteReservation(@PathVariable(value = "id") Long id) {
		Booking booking = bookingService.deletReservation(id);
		return new ResponseEntity<Booking>(booking, HttpStatus.ACCEPTED);
	}
	

	@PatchMapping("/update-status/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Booking> updateReservationStatus(@PathVariable(value = "id") Long id,
			@RequestBody Booking booking) {
	    Booking updatedBooking = bookingService.updateReservationStatus(id, booking.getBookingState());
	    if (updatedBooking != null) {
	        return new ResponseEntity<Booking>(updatedBooking, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}

	@GetMapping("/")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Booking>> getReservations() {
		List<Booking> reservations = bookingService.getReservation();
		return new ResponseEntity<List<Booking>>(reservations, HttpStatus.OK);
	}

	// Devuelve lista de edificios a partir de la ciudad
	@GetMapping("/builds/{city}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Building>> getBuilds(@PathVariable(value = "city") String city) {
		
		return new ResponseEntity<List<Building>>(buildingRepository.findByCity(city.toUpperCase()), HttpStatus.OK);
	}

	// Devuelve las plantas del edificio a partir de su dirección ??? puede estar
	// mal si hay la misma dirección en # ciudad
	@GetMapping("/floors/{adrress}/{city}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<FloorDTO>> getFloors(@PathVariable(value = "adrress") String adrress,
			@PathVariable(value = "city") String city) {
		Building building = CollectionUtils
				.extractSingleton(buildingRepository.findByCityAndAdrress(city.toUpperCase(), adrress));
		List<FloorDTO> floors = building.getFloors().stream()
				.map(floor -> new FloorDTO(floor.getFloorNumber(),
						(int) seatService.getOccupancy(floor.getFloorNumber(), adrress, city)))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FloorDTO>>(floors, HttpStatus.OK);
	}

	// Devuelve los puestos de la planta indicada a partir de (ciudad, dirección de
	// edificio, planta) estoy aqui, he creado para ello
	// el seatGetBody
	@PostMapping("/seats/")
	@CrossOrigin(origins = "http://localhost:4200")
	// creando objeto BookingPostBody para agrupar tres strings
	public ResponseEntity<List<Seat>> getSeats(@RequestBody SeatGetBody seatGetBody) {
		List<Seat> seats = seatService.getSeats(seatGetBody.getCity(), seatGetBody.getAdrress(),
				seatGetBody.getFloorNumber());
		if (CollectionUtils.isEmpty(seats)) {
			return new ResponseEntity<List<Seat>>(List.of(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Seat>>(seats, HttpStatus.OK);
		}
	}

	// Método para calcular el % de ocupación de puestos en una planta
	@GetMapping("/occupancy/{floorNumber}/{address}/{city}")
	public int getPercentageOccupancy(@PathVariable(value = "floorNumber") String floorNumber,
			@PathVariable(value = "address") String address, @PathVariable(value = "city") String city) {
		return (int) seatService.getOccupancy(floorNumber, address, city);
	}
}

package com.app.rys.service;

import java.util.Date;
import java.util.List;

import com.app.rys.models.Booking;

/**
 * Servicio de reservación
 * 
 * @author Naoufal
 *
 */
public interface IBookingService {
	
	/**
	 * Método para crear una reserva
	 * 
	 * @return
	 */
	public Booking createReservation(Long id, String seatNumber, String adrress, String floorNumber, String city, Date reservationDate);
	
	/**
	 * Método para eliminar una reserva
	 * 
	 * @return
	 */
	public Booking deletReservation(Long id);
	
	/**
	 * Método para devolver las reservas
	 * 
	 * @return
	 */
	public List<Booking> getReservation();
	
	/**
	 * Método para comprobar la disponibilidad de sillas por la fecha
	 * 
	 * @return
	 */
	public boolean checkDateAvailability(Date reservationDate, String seatNumber);
}

package com.app.rys.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidad reserva
 * 
 * @author Oleksandr
 */
@Entity
public class Booking {

	// propiedades
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Pattern(message = "Booking code not valid", regexp = "^[a-z]{1}-[0-9]")
	private String bookingCode;

	//@Temporal(TemporalType.TIMESTAMP) // Fecha y hora
	@Temporal(TemporalType.DATE)
	private Date reservationDate;

	private String bookingState;

	private String informacionDeReserva;
	
	// una reserva asignada a un único usuario @ManyToOne
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	// Constructores: vacio y con campos
	public Booking(@Pattern(message = "Booking code not valid", regexp = "^[a-z]{1}-[0-9]") String bookingCode,
			Date reservationDate, String bookingState) {
		this.bookingCode = bookingCode;
		this.reservationDate = reservationDate;
		this.bookingState = bookingState;
	}

	public Booking() {

	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getBookingState() {
		return bookingState;
	}

	public void setBookingState(String bookingState) {
		this.bookingState = bookingState;
	}
	
	public String getInformacionDeReserva() {
		return informacionDeReserva;
	}

	public void setInformacionDeReserva(String informacionDeReserva) {
		this.informacionDeReserva = informacionDeReserva;
	}

	public Long getUser() {
		return user.getId();
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Long getUserId() {
	    return user.getId();
	}
	
	
	// equals & hashcode
	@Override
	public int hashCode() {
		return Objects.hash(reservationDate, bookingCode, bookingState, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(reservationDate, other.reservationDate) && Objects.equals(bookingCode, other.bookingCode)
				&& Objects.equals(bookingState, other.bookingState) && Objects.equals(id, other.id);
	}

	// toString
	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingCode=" + bookingCode + ", ReservationDate=" + reservationDate
				+ ", bookingState=" + bookingState +  "]";
	}

}

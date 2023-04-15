package com.app.rys.enums;

/**
 * Enum del estado reserva
 * 
 * @author Oleksandr
 */
public enum BookingState {
	ANULADA("Anulada"), PENDIENTE("Pendiente"), CONFIRMADA("Confirmada");

	private String bookingState;

	public String getState() {
		return bookingState;
	}

	private BookingState(String bookingState) {
		this.bookingState = bookingState;
	}

}

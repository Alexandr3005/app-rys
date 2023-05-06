package com.app.rys.enums;

/**
 * Enum del estado reserva
 * 
 * @author Oleksandr
 */
public enum BookingState {
	ANULADA("Cancelled"), PENDIENTE("Pending"), CONFIRMADA("Confirmed");

	private String bookingState;

	public String getState() {
		return bookingState;
	}

	private BookingState(String bookingState) {
		this.bookingState = bookingState;
	}

}

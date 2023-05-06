package com.app.rys.enums;

/**
 * Enum del estado de asiento
 * 
 * @author Naoufal
 *
 */
public enum SeatState {
	DISPONIBLE("Available") ,NO_DISPONIBLE("Not available");
	
	private String seatState;

	SeatState(String seatState) {
		this.seatState=seatState;
	}

	public String getState() {
		return seatState;
	}
	
}

package com.app.rys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.rys.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

	List<Booking> findByReservationDateAndInformacionDeReserva(Date reservationDate, String informacionDeReserva);
	
	 List<Booking> findByReservationDate(Date reservationDate);
	 
	 List<Booking> findByid(Long id);
}

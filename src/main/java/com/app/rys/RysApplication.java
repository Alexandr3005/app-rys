package com.app.rys;

import java.util.Arrays;
import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.rys.models.Building;
import com.app.rys.models.Floor;
import com.app.rys.models.Seat;
import com.app.rys.models.User;
import com.app.rys.repository.BuildingRepository;
import com.app.rys.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@SpringBootApplication


public class RysApplication {
	
	@SpringBootApplication
	@ComponentScan(basePackages = "com.example")
	public class YourApplication {

	    public static void main(String[] args) {
	        SpringApplication.run(YourApplication.class, args);
	    }
	}
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RysApplication.class, args);
	}
	
	// ejecucion del codigo despues de la creacion del bean
	@PostConstruct
	public void initDB() {
		
		// 1 usuario
		User user = new User();
		user.setFullName("admin");
		user.setEmail("admin.admin@gmail.com");
		user.setPhone("985858565");
		user.setPassword("admin");
		user.setConfirmPassword("admin");
		
		User user2 = new User();
		user2.setFullName("user");
		user2.setEmail("user.admin@gmail.com");
		user2.setPhone("985858565");
		user2.setPassword("user");
		user2.setConfirmPassword("user");
		
		// edificio 1 y 2 de Madrid con 4 plantas (1->2) y (3->4)
		// edificio 3 y 4 de Barcelona con 2 plantas (5->6) y (7->8)
		Building building1 = new Building();
		Building building2 = new Building();
		Building building3 = new Building();
		Building building4 = new Building();
		
		// 8 plantas en total cada planta con 2 asientos
		Floor floor1 = new Floor();
		Floor floor2 = new Floor();
		Floor floor3 = new Floor();
		Floor floor4 = new Floor();
		Floor floor5 = new Floor();
		Floor floor6 = new Floor();
		Floor floor7 = new Floor();
		Floor floor8 = new Floor();
		Floor floor9 = new Floor(); 
		
		// 16 asientos num 1 y 2 de cada planta
		// seat sin darle estado por haber hecho prePersist
		Seat seat1 = new Seat();
		seat1.setSeatNumber("1");
		seat1.setFloor(floor1);
		
		Seat seat2 = new Seat();
		seat2.setSeatNumber("2");
		seat2.setFloor(floor1);
		
		Seat seat3 = new Seat();
		seat3.setSeatNumber("3");
		seat3.setFloor(floor1);
		
		Seat seat4 = new Seat();
		seat4.setSeatNumber("4");
		seat4.setFloor(floor1);
		
		Seat seat5 = new Seat();
		seat5.setSeatNumber("5");
		seat5.setFloor(floor1);
		
		Seat seat6 = new Seat();
		seat6.setSeatNumber("6");
		seat6.setFloor(floor1);
		
		Seat seat7 = new Seat();
		seat7.setSeatNumber("7");
		seat7.setFloor(floor1);
		
		Seat seat8 = new Seat();
		seat8.setSeatNumber("8");
		seat8.setFloor(floor1);
		
		Seat seat9 = new Seat();
		seat9.setSeatNumber("1");
		seat9.setFloor(floor2);
		
		Seat seat10 = new Seat();
		seat10.setSeatNumber("2");
		seat10.setFloor(floor2);
		
		Seat seat11 = new Seat();
		seat11.setSeatNumber("3");
		seat11.setFloor(floor2);
		
		Seat seat12 = new Seat();
		seat12.setSeatNumber("4");
		seat12.setFloor(floor2);
		
		Seat seat13 = new Seat();
		seat13.setSeatNumber("5");
		seat13.setFloor(floor2);
		
		Seat seat14 = new Seat();
		seat14.setSeatNumber("6");
		seat14.setFloor(floor2);
		
		Seat seat15 = new Seat();
		seat15.setSeatNumber("7");
		seat15.setFloor(floor2);
		
		Seat seat16 = new Seat();
		seat16.setSeatNumber("8");
		seat16.setFloor(floor2);
		
		Seat seat17 = new Seat();
		seat17.setSeatNumber("1");
		seat17.setFloor(floor3);
		
		Seat seat18 = new Seat();
		seat18.setSeatNumber("2");
		seat18.setFloor(floor3);
		
		Seat seat19 = new Seat();
		seat19.setSeatNumber("3");
		seat19.setFloor(floor3);
		
		Seat seat20 = new Seat();
		seat20.setSeatNumber("4");
		seat20.setFloor(floor3);
		
		Seat seat21 = new Seat();
		seat21.setSeatNumber("5");
		seat21.setFloor(floor3);
		
		Seat seat22 = new Seat();
		seat22.setSeatNumber("6");
		seat22.setFloor(floor3);
		
		Seat seat23 = new Seat();
		seat23.setSeatNumber("7");
		seat23.setFloor(floor3);
		
		Seat seat24 = new Seat();
		seat24.setSeatNumber("8");
		seat24.setFloor(floor3);
		
		Seat seat25 = new Seat();
		seat25.setSeatNumber("1");
		seat25.setFloor(floor4);
		
		Seat seat26 = new Seat();
		seat26.setSeatNumber("2");
		seat26.setFloor(floor4);
		
		Seat seat27 = new Seat();
		seat27.setSeatNumber("3");
		seat27.setFloor(floor4);
		
		Seat seat28 = new Seat();
		seat28.setSeatNumber("4");
		seat28.setFloor(floor4);
		
		Seat seat29 = new Seat();
		seat29.setSeatNumber("5");
		seat29.setFloor(floor4);
		
		Seat seat30 = new Seat();
		seat30.setSeatNumber("6");
		seat30.setFloor(floor4);
		
		Seat seat31 = new Seat();
		seat31.setSeatNumber("7");
		seat31.setFloor(floor4);
		
		Seat seat32 = new Seat();
		seat32.setSeatNumber("8");
		seat32.setFloor(floor4);
		
		Seat seat33 = new Seat();
		seat33.setSeatNumber("1");
		seat33.setFloor(floor5);
		
		Seat seat34 = new Seat();
		seat34.setSeatNumber("2");
		seat34.setFloor(floor5);
		
		Seat seat35 = new Seat();
		seat35.setSeatNumber("3");
		seat35.setFloor(floor5);
		
		Seat seat36 = new Seat();
		seat36.setSeatNumber("4");
		seat36.setFloor(floor5);
		
		Seat seat37 = new Seat();
		seat37.setSeatNumber("5");
		seat37.setFloor(floor5);
		
		Seat seat38 = new Seat();
		seat38.setSeatNumber("6");
		seat38.setFloor(floor5);
		
		Seat seat39 = new Seat();
		seat39.setSeatNumber("7");
		seat39.setFloor(floor5);
		
		Seat seat40 = new Seat();
		seat40.setSeatNumber("8");
		seat40.setFloor(floor5);
		
		Seat seat41 = new Seat();
		seat41.setSeatNumber("1");
		seat41.setFloor(floor6);
		
		Seat seat42 = new Seat();
		seat42.setSeatNumber("2");
		seat42.setFloor(floor6);
		
		Seat seat43 = new Seat();
		seat43.setSeatNumber("3");
		seat43.setFloor(floor6);
		
		Seat seat44 = new Seat();
		seat44.setSeatNumber("4");
		seat44.setFloor(floor6);
		
		Seat seat45 = new Seat();
		seat45.setSeatNumber("5");
		seat45.setFloor(floor6);
		
		Seat seat46 = new Seat();
		seat46.setSeatNumber("6");
		seat46.setFloor(floor6);
		
		Seat seat47 = new Seat();
		seat47.setSeatNumber("7");
		seat47.setFloor(floor6);
		
		Seat seat48 = new Seat();
		seat48.setSeatNumber("8");
		seat48.setFloor(floor6);
		
		Seat seat49 = new Seat();
		seat49.setSeatNumber("1");
		seat49.setFloor(floor7);
		
		Seat seat50 = new Seat();
		seat50.setSeatNumber("2");
		seat50.setFloor(floor7);
		
		Seat seat51 = new Seat();
		seat51.setSeatNumber("3");
		seat51.setFloor(floor7);
		
		Seat seat52 = new Seat();
		seat52.setSeatNumber("4");
		seat52.setFloor(floor7);
		
		Seat seat53 = new Seat();
		seat53.setSeatNumber("5");
		seat53.setFloor(floor7);
		
		Seat seat54 = new Seat();
		seat54.setSeatNumber("6");
		seat54.setFloor(floor7);
		
		Seat seat55 = new Seat();
		seat55.setSeatNumber("7");
		seat55.setFloor(floor7);
		
		Seat seat56 = new Seat();
		seat56.setSeatNumber("8");
		seat56.setFloor(floor7);
		
		Seat seat57 = new Seat();
		seat57.setSeatNumber("1");
		seat57.setFloor(floor8);
		
		Seat seat58 = new Seat();
		seat58.setSeatNumber("2");
		seat58.setFloor(floor8);
		
		Seat seat59 = new Seat();
		seat59.setSeatNumber("3");
		seat59.setFloor(floor8);
		
		Seat seat60 = new Seat();
		seat60.setSeatNumber("4");
		seat60.setFloor(floor8);
		
		Seat seat61 = new Seat();
		seat61.setSeatNumber("5");
		seat61.setFloor(floor8);
		
		Seat seat62 = new Seat();
		seat62.setSeatNumber("6");
		seat62.setFloor(floor8);
		
		Seat seat63 = new Seat();
		seat63.setSeatNumber("7");
		seat63.setFloor(floor8);
		
		Seat seat64 = new Seat();
		seat64.setSeatNumber("8");
		seat64.setFloor(floor8);
		
		// plantas
		floor1.setFloorNumber("1");
		floor1.setBuilding(building1);
		floor1.setSeats(Arrays.asList(seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8));
		
		floor2.setFloorNumber("2");
		floor2.setBuilding(building1);
		floor2.setSeats(Arrays.asList(seat9, seat10, seat11, seat12, seat13, seat14, seat15, seat16));
		
		floor3.setFloorNumber("1");
		floor3.setBuilding(building2);
		floor3.setSeats(Arrays.asList(seat17, seat18, seat19, seat20, seat21, seat22, seat23, seat24));
		
		floor4.setFloorNumber("2");
		floor4.setBuilding(building2);
		floor4.setSeats(Arrays.asList(seat25, seat26, seat27, seat28, seat29, seat30, seat31, seat32));
		
		floor5.setFloorNumber("1");
		floor5.setBuilding(building3);
		floor5.setSeats(Arrays.asList(seat33, seat34, seat35, seat36, seat37, seat38, seat39, seat40));
		
		floor6.setFloorNumber("2");
		floor6.setBuilding(building3);
		floor6.setSeats(Arrays.asList(seat41, seat42, seat43, seat44, seat45, seat46, seat47, seat48));
		
		floor7.setFloorNumber("1");
		floor7.setBuilding(building4);
		floor7.setSeats(Arrays.asList(seat49, seat50, seat51, seat52, seat53, seat54, seat55, seat56));
		
		floor8.setFloorNumber("2");
		floor8.setBuilding(building4);
		floor8.setSeats(Arrays.asList(seat57, seat58, seat59, seat60, seat61, seat62, seat63, seat64));
		
		//edificio
		building1.setAdrress("Calle Atocha 42");
		building1.setCity("madrid");
		building1.setFloors(Arrays.asList(floor1,floor2));
		
		building2.setAdrress("Calle Francia 41");
		building2.setCity("madrid");
		building2.setFloors(Arrays.asList(floor3,floor4));
		
		building3.setAdrress("Calle Moncloa 40");
		building3.setCity("barcelona");
		building3.setFloors(Arrays.asList(floor5,floor6));
		
		building4.setAdrress("Calle Francia 41");
		building4.setCity("barcelona");
		building4.setFloors(Arrays.asList(floor7,floor8));
		
		userRepository.save(user);
		userRepository.save(user2);
		buildingRepository.save(building1);
		buildingRepository.save(building2);
		buildingRepository.save(building3);
		buildingRepository.save(building4);
		//floorRepository.saveAll(Arrays.asList(floor1, floor2));
		//seatRepository.saveAll(Arrays.asList(seat1, seat2, seat3, seat4));

	}
	
}
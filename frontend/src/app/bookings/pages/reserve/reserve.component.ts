import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Seat } from '../../interfaces/seat';
import { BookingPostBody } from '../../interfaces/bookingPostBody';
import { SharedService } from '../../services/shared.service';
import { BookingService } from '../../services/booking.service';


@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.css']
})
export class ReserveComponent implements OnInit {

  seats: Seat[];
  date: any;
  floor: String;
  adrress: String;
  city: String;
  selectedSeat: any;
  bookingPostBody: BookingPostBody;
  // cajas seleccionables
  Selected = false;

  constructor(private shared: SharedService, private bookingService: BookingService, private router: Router) { }

  ngOnInit(): void {
    this.shared.currentSeats.subscribe(data =>
      this.seats = data);
    this.shared.currentFloor.subscribe(data =>
      this.floor = data);
    this.shared.currentAdrress.subscribe(data =>
      this.adrress = data);
    this.shared.currentCity.subscribe(data =>
      this.city = data);
    this.shared.currentDate.subscribe(data =>
      this.date = data);
  }

  
  protected crearReserva() {
    this.bookingPostBody = {
      seatNumber: this.selectedSeat,
      address: this.adrress,
      floorNumber: this.floor,
      city: this.city,
      reservationDate: this.date
    }
    this.bookingService.createReservation(this.bookingPostBody).subscribe(data=>{
      console.log(data);
    });
  }
///MIRAR ESTO
  validateAndSave(){
    if(this.selectedSeat){
      this.Selected = true;
      this.crearReserva();
    }
  }
  // cajas seleccionables no sé si hace algo el onSelect
  onSelectSeat(seatNumber: any) {
    this.selectedSeat = seatNumber;
  }


  //Estado cajas 

  getSeatStateClass(seats: Seat) {
    if (seats.state === 'ND') {
      return 'btn btn-info disabled';
    } else if (seats.state === 'D') {
      return 'btn btn-info';
    } else {
      return '';
    }
  }
   
  
}

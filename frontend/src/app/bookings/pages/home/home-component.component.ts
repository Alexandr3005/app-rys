import { Component, OnInit } from '@angular/core';
import { Booking } from '../../interfaces/booking';
import { AuthService } from '../../../auth/services/auth.service';
import { BookingService } from '../../services/booking.service';


@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {
  title = 'RYS';
  slogan = 'Reserve your seat';
  showModal: boolean = false;
  isModalOpen = false;
  selectedReservationId: number;
  bookings: Booking[];

  currentDay = new Date();
  currentMonth = this.currentDay.getUTCMonth() + 1;
  currentYear = this.currentDay.getFullYear();
  monthAndYear = new Map();

  currentUser: any;
  router: any;

  constructor(private bookingService: BookingService, public authService: AuthService) { }



  ngOnInit(): void {
    this.obtenerReservas();
    this.currentUser = this.authService.currentUser;
    console.log(this.currentUser);
    this.currentUser = this.authService.currentUser;

  }

  //olek
  onDeleteReservation(id: number) {
    this.deleteReservation(id);
  }

  openModal(reservationId: number) {
    this.selectedReservationId = reservationId;
    this.showModal = true;
    this.isModalOpen = true;

  }
  closeModal() {
    this.showModal = false;
    this.isModalOpen = false;
  }
  
  deleteReservation(id: number) {

    this.bookingService.deleteReservation(id).subscribe({});

  }

  private obtenerReservas() {
    this.bookingService.obtenerListadoDeReservas().subscribe(dato => {
      this.bookings = dato;
      this.bookings.forEach(booking => {
        this.monthAndYear.set(Number((String)(booking.reservationDate).split('-')[1]), Number((String)(booking.reservationDate).split('-')[0]));
      });
    });
  }



  // objetivo : para poner clas reserva de bajo de su fecha(mes y año) correspondiente en el template
  // lo que hace: compara el mes y el año que estan en el mapa con el mes y el año de cada resrva
  protected checkMonthAndYear(key: Number, value: Number, reservationDate: any) {
    if (Number((String)(reservationDate).split('-')[1]) === key && Number((String)(reservationDate).split('-')[0]) === value) {
      return true;
    } else {
      return false;
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { Booking } from '../service/booking';
import { BookingService } from '../service/booking.service';
import { AuthService } from '../service/auth.service';




@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {
  title = 'RYS';
  slogan = 'Reserve your seat';
  /*Alert olek*/
  showModal: boolean = false;
  isModalOpen = false;
  selectedReservationId : number;
  /*Alert olek*/
  bookings: Booking[];
  currentDay = new Date();
  currentMonth = this.currentDay.getUTCMonth() + 1;
  currentYear = this.currentDay.getFullYear();
  monthAndYear = new Map();

  currentUser :any;

  constructor(private bookingService: BookingService, public authService: AuthService) { }

  ngOnInit(): void {
    this.obtenerReservas();
   this.currentUser= this.authService.currentUser;
    console.log(this.currentUser);
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
  // para personalizar el alert de confirmación de la eliminación de reserva usamos ngx-sweetalert2
  /*deleteReservation(id: number) {
    if(window.confirm('¿Estas seguro de eliminar esta reserva?')){
      this.bookingService.deleteReservation(id).subscribe({
      });
    }
  }*/
  /*deleteReservation(id: number) {
    if (window.confirm('Are you sure to delete this reservation?')) {
      this.bookingService.deleteReservation(id).subscribe({});
      alert('The reservation has been removed');
    }
  }*/
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

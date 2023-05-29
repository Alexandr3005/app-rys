import { Component, OnInit } from '@angular/core';
import { Booking } from '../../interfaces/booking';
import { AuthService } from '../../../auth/services/auth.service';
import { BookingService } from '../../services/booking.service';
import { HttpClient } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';


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

  nBookings: number = 3;
  selectedMaxBookings: number = this.nBookings; 
  userReservationsCount: any = {}; 
  avisoNumeroReservas = false;


  constructor(private bookingService: BookingService, public authService: AuthService, private http: HttpClient
    , private translateService: TranslateService) { }

    nBookingsUpdate() {
      this.nBookings = this.selectedMaxBookings;
      localStorage.setItem('selectedMaxBookings', this.selectedMaxBookings.toString());
      this.avisoNumeroReservas = true;
    }
    

    ngOnInit(): void {
      this.obtenerReservas();
      this.currentUser = this.authService.currentUser;
      const storedMaxBookings = localStorage.getItem('selectedMaxBookings');
      this.selectedMaxBookings = storedMaxBookings ? parseInt(storedMaxBookings, 10) : this.selectedMaxBookings;
    }
    
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
        this.userReservationsCount = this.contarReservasPorUsuario();

        this.monthAndYear.set(Number((String)(booking.reservationDate).split('-')[1]),
          Number((String)(booking.reservationDate).split('-')[0]));
      });
      
    });
  }

  
  

  updateReservationState(booking: Booking, newState: string): void {
    booking.bookingState = this.translateService.instant(newState);

    this.bookingService.updateReservationStatus(booking)
      .subscribe((updatedBooking: Booking) => {
        console.log('Estado de reserva actualizado:', updatedBooking);
      });
  }

  contarReservasPorUsuario() {
    const count: { [userId: number]: number } = {};
    this.bookings.forEach(booking => {
      const userId = booking.userId;
      count[userId] = count[userId] ? count[userId] + 1 : 1;
    });
    return count;
  }
  


  updateReservationStateCancel(booking: Booking): void {
    this.updateReservationState(booking, 'Cancelled');
  }

  updateReservationStateConfirm(booking: Booking): void {
    this.updateReservationState(booking, 'Confirmed');
  }






  /*
  updateReservationStateCancel(booking: Booking): void {
    booking.bookingState = this.translateService.instant('Cancelled');

    this.bookingService.updateReservationStatus(booking)
        .subscribe((updatedBooking: Booking) => {
            console.log('Estado de reserva actualizado:', updatedBooking);
        });
  }

  updateReservationStateConfirm(booking: Booking): void {
    booking.bookingState = this.translateService.instant('Confirmed');

    this.bookingService.updateReservationStatus(booking)
        .subscribe((updatedBooking: Booking) => {
            console.log('Estado de reserva actualizado:', updatedBooking);
        });
  }

*/



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



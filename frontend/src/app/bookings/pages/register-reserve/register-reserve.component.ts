import { Component, OnInit } from '@angular/core';
import { Building } from 'src/app/bookings/interfaces/building';
import { Floor } from 'src/app/bookings/interfaces/floor';

import { Seat } from 'src/app/bookings/interfaces/seat';

import { SharedService } from 'src/app/bookings/services/shared.service';
import { SeatGetBody } from '../../interfaces/seatGetBody';
import { BuildingService } from '../../services/building.service';
import { FloorService } from '../../services/floor.service';
import { SeatService } from '../../services/seat.service';


@Component({
  selector: 'app-register-reserve',
  templateUrl: './register-reserve.component.html',
  styleUrls: ['./register-reserve.component.css']
})
export class RegisterReserveComponent implements OnInit {

  //yyyy-MM-dd
  todayDate: any = "";
  buildings: Building[];
  floors: Floor[];
  seats: Seat[];
  selectedFloor: any;
  // select
  ciudades: String[] = ["Madrid", "Barcelona"];
  ciudad: String = this.ciudades[0];
  adrress: String;
  seatGetBody: SeatGetBody;

  currentDay = new Date();

  // cajas seleccionables
  isSelected = false;
  lista: number[]; // lista de % ocupancy floor
  // select
  constructor(private buildingService: BuildingService, private floorService: FloorService, private seatService: SeatService, private shared: SharedService) {
    //this.fecha = new Date();
  }

  ngOnInit(): void {
    this.obtenerEdificios();
    this.todayDate = this.getTodayDate(this.currentDay);
    
  }

  protected obtenerEdificios() {
    this.buildingService.obtenerListadoDeEdificios(this.ciudad).subscribe(dato => {
      this.buildings = dato;
      this.adrress = this.buildings[0].adrress;
      this.obtenerPlantas();
    });
  }

  protected obtenerPlantas() {
    this.floorService.obtenerListadoDePlantas(this.adrress, this.ciudad).subscribe(dato => {
      this.floors = dato;
      //this.selectedFloor = dato[0].floorNumber;
    });
  }
  // cajas seleccionables
  onSelectFloor(floorNumber: any) {
    this.selectedFloor = floorNumber;
    this.obtenerSillas();
  }
  // cajas

  protected obtenerSillas() {
    this.seatGetBody = {
      city: this.ciudad,
      adrress: this.adrress,
      floorNumber: this.selectedFloor
    }
    this.seatService.obtenerListadoDeSillas(this.seatGetBody).subscribe(data => {
      this.seats = data;
      this.shared.shareData(this.seats, this.selectedFloor, this.adrress, this.ciudad, this.todayDate);
    });
  }
  
  // convierte currentDay a todayDate
  private getTodayDate(date: Date) {
    let currentYear = date.getFullYear();
    let currentMonth = date.getUTCMonth() + 1;
    let currentDay = date.getUTCDate();

    let finalMonth: any;
    let finalDay: any;

    if (currentMonth < 10) {
      finalMonth = "0" + currentMonth;
    } else {
      finalMonth = currentMonth;
    }

    if (currentDay < 10) {
      finalDay = "0" + currentDay;
    } else {
      finalDay = currentDay;
    }

    return this.todayDate = currentYear + "-" + finalMonth + "-" + finalDay;
  }
} 

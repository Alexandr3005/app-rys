import { HttpClient } from '@angular/common/http';
import { ExpansionCase } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { observable, Observable } from 'rxjs';
import { Building } from '../interfaces/building';

@Injectable({
  providedIn: 'root'
})
export class BuildingService {
  obtenerListadoDePlantas(seleccionado: string) {
    throw new Error('Method not implemented.');
  }
  
  
  // Esta url obtiene el listado de las reservas en el back-end
  private baseURL = "http://localhost:8080/api/reservation/builds/";

  constructor(private httpClient : HttpClient) { }

  // Este método nos sirve para obtener las reservas, un observable es un patrón de diseño
  obtenerListadoDeEdificios(city:String):Observable<Building[]>{

      return this.httpClient.get<Building[]>(`${this.baseURL+city}`);

  }
}

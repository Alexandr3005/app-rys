import { HttpClient } from "@angular/common/http";
import { Injectable, ɵresetJitOptions } from "@angular/core";
import { Observable, switchMap } from "rxjs";
import { SeatGetBody } from "../interfaces/seatGetBody";
import { Seat } from "../interfaces/seat";


@Injectable({
  providedIn: 'root'
})
export class SeatService {
  
  
  // Esta url obtiene el listado de las reservas en el back-end
  private baseURL = "http://localhost:8080/api/reservation/seats/";

  constructor(private httpClient : HttpClient) { }
    // Este método nos sirve para obtener las sillas de una planta, un observable es un patrón de diseño
    obtenerListadoDeSillas(getBody: SeatGetBody): Observable<Seat[]> {
        return this.httpClient.post<Seat[]>(`${this.baseURL}`, getBody);
    }
    
}
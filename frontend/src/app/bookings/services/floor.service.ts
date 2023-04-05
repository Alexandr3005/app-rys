import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { SeatGetBody } from "../interfaces/seatGetBody";
import { Floor } from "../interfaces/floor";


@Injectable({
  providedIn: 'root'
})
export class FloorService {


  // Esta url obtiene el listado de las reservas en el back-end
  private baseURL = "http://localhost:8080/api/reservation/floors/";

  constructor(private httpClient: HttpClient) { }

  // Este método nos sirve para obtener las reservas, un observable es un patrón de diseño
  obtenerListadoDePlantas(adrress: String, city: String): Observable<Floor[]> {
    return this.httpClient.get<Floor[]>(`${this.baseURL + adrress + "/" + city}`);
  }
  
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from '../user';



//Para guardar el usuario
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  currentUser: any;
  private baseURL = "http://localhost:8080/api/reservation/";
  private currentUserKey = 'currentUser';

  constructor(private httpClient: HttpClient) {

     // Recuperar el usuario actual al inicio de la aplicación
     const currentUser = localStorage.getItem(this.currentUserKey);
     if (currentUser) {
       this.currentUser = JSON.parse(currentUser);
     }
    }

   login(user: User): Observable<object> {
    console.log(user);
    return this.httpClient.post(`${this.baseURL}login/`, user).pipe(
      map((response: any) => {
        this.currentUser = response; //informacion del ususario
        console.log(response);
        // Guardar el usuario actual en localStorage o sessionStorage
        localStorage.setItem(this.currentUserKey, JSON.stringify(this.currentUser));
        // Retornar la respuesta del servidor
        return response;
      })
    );
  }

  logout() {
    // Remover el usuario actual de localStorage o sessionStorage
    localStorage.removeItem(this.currentUserKey);
    this.currentUser = null;
  }
 
 
}
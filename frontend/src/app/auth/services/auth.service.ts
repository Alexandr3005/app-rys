import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from '../interfaces/user';



//Para guardar el usuario
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  currentUser: any;
  userId: number; // Agregar nueva propiedad
    tokenKey = 'myToken';


  private baseURL = "http://localhost:8080/api/reservation/";
  private currentUserKey = 'currentUser';

  constructor(private httpClient: HttpClient) {

     // Recuperar el usuario actual al inicio de la aplicación
     const currentUser = localStorage.getItem(this.currentUserKey);
     if (currentUser) {
       this.currentUser = JSON.parse(currentUser);
       this.userId = this.currentUser.id;
     }
    }

    
    login(user: User): Observable<object> {
      console.log(user);
      return this.httpClient.post(`${this.baseURL}login/`, user).pipe(
        map((response: any) => {
          this.currentUser = response; //informacion del ususario
          console.log(response);
          // Guardar el usuario actual y el token de sesión en localStorage o sessionStorage
          localStorage.setItem(this.currentUserKey, JSON.stringify(this.currentUser));
          localStorage.setItem(this.tokenKey, response.token); 
          // Retornar la respuesta del servidor
          return response;
        })
      );
    }
    
    isLoggedIn(): boolean {
      const token = localStorage.getItem(this.tokenKey);
      console.log(token);

      // Verifico si el token existe y es válido
      if (token !== null && token !== undefined) {

        // Verifico si el usuario actual existe y es válido
        const currentUserString = localStorage.getItem(this.currentUserKey) || sessionStorage.getItem(this.currentUserKey);
        const currentUser = currentUserString !== null ? JSON.parse(currentUserString) : null;
        return currentUser !== null && currentUser !== undefined;
      }
      return false;
    }
    
    

  register(user: User): Observable<object> {
    console.log(user);
    return this.httpClient.post(`${this.baseURL}register/`, user).pipe(
      map((response: any) => {
        this.currentUser = response; 
        console.log(response);

        localStorage.setItem(this.currentUserKey, JSON.stringify(this.currentUser));
     
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


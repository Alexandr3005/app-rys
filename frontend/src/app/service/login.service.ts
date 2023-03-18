import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseURL = "http://localhost:8080/api/reservation/";

  constructor(private httpClient: HttpClient) { }

  login(user:User):Observable<object>{
    console.log(user);
    return this.httpClient.post(`${this.baseURL}login/`,user);
  }
}

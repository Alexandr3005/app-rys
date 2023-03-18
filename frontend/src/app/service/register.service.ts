import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {


  constructor(private httpClient: HttpClient) { }

  private baseURL = "http://localhost:8080/api/reservation/";

  registerUser(user:User):Observable<object>{
    console.log(user);
    return this.httpClient.post(`${this.baseURL}register/`,user);
  }

}

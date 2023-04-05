import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { LoginService } from '../service/login.service';
import { User } from '../../interfaces/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //Objeto user 
  user:User = new User();

  constructor(private router: Router, public authService: AuthService) { }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.user).subscribe(
      (response: any) => {
        this.authService.currentUser = response;
        this.router.navigate(['/home']);
      },

      (error) => {
        console.error(error);
        alert('Enter the correct name and password');
      }
    );
  }
  
  //Salir del usuario 
  logout(){
  this.authService.logout();
  } 

    
}



  
  

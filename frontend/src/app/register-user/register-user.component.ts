import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from '../service/register.service';
import { User } from '../user';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  //Objeto user 
  user:User = new User();
  

  //Router redirigir

  constructor( private router:Router, public authService: AuthService) { }


  ngOnInit(): void {
    
  }
/*
  userRegister(){
    this.registerService.registerUser(this.user).subscribe(data=>{
      this.router.navigate(['/home']);
      
    }, error=>alert("Sorry not register"))
  
  }
*/
  userRegister() {
    this.authService.register(this.user).subscribe(
      (response: any) => {
        this.authService.currentUser = response;
        this.router.navigate(['/home']);
      },

      (error: any) => {
        console.error(error);
        alert('Enter the correct name and password');
      }
    );
  }




}

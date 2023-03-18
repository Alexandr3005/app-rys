import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //Objeto user 
  user:User = new User();

  constructor(private loginservice:LoginService, private router: Router) { }

  ngOnInit(): void {
  }

 
/*
  volverHome() {

    this.router.navigate(['']);

  }
  */

  login(){
    console.log(this.user);
    this.loginservice.login(this.user).subscribe(data=>{
      this.router.navigate(['/home']);
   
    }, error=>alert("Enter the correct name and password"));
  }

  

  onSubmit() {
    // Aquí se procesa la lógica de inicio de sesión
    // ...

    // Después de procesar el inicio de sesión, redirigir a la página de home
    this.router.navigate(['/']);
  }
  
}

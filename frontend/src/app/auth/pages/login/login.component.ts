import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { LoginService } from '../service/login.service';
import { User } from '../../interfaces/user';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //Objeto user 
  user: User = new User();
  loginForm: FormGroup;
  loginError = false;


  constructor(private router: Router, public authService: AuthService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  invalidField(field: string) {

    return this.loginForm.controls[field].invalid && (this.loginForm.controls[field].touched || this.loginForm.controls[field].dirty);

  }


  onSubmit() {
    if (this.loginForm.valid) {
      // Si es válido, realiza las acciones que necesites aquí
    } else {
      // Si el formulario es inválido, marca todos los campos como tocados
      Object.values(this.loginForm.controls).forEach(control => control.markAsTouched());

      return;
    }


    this.user.email = this.loginForm.controls['email'].value;
    this.user.password = this.loginForm.controls['password'].value;

    this.login();

  }



  login() {
    this.authService.login(this.user).subscribe(
      (response: any) => {
        this.authService.currentUser = response;
        this.router.navigate(['/home']);
      },

      (error) => {
        console.error(error);
        this.loginError = true;
      }
    );
  }

  //Salir del usuario 
  logout() {
    this.authService.logout();
  }


}






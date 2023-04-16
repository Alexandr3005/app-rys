import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../interfaces/user';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  //Objeto user 
  user: User = new User();


  //Router redirigir

  constructor(public authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  //Formulario reactivo -- validaciones

  registerForm: FormGroup;


  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      fullName: ['', Validators.required],
      password: ['', [Validators.required]],
      confirmPassword: ['', Validators.required],
      phone: ['', [Validators.required]]
    }, { validator: this.checkPasswords });

  }

  onSubmit() {

     // Validar el formulario
  if (this.registerForm.invalid) {
    alert('Please fill all required fields');
    return;
  }

  // Actualizar el objeto user con los datos del formulario
  this.user.email = this.registerForm.controls['email'].value;
  this.user.fullName = this.registerForm.controls['fullName'].value;
  this.user.password = this.registerForm.controls['password'].value;
  this.user.confirmPassword = this.registerForm.controls['confirmPassword'].value;
  this.user.phone = this.registerForm.controls['phone'].value;

  // Llamar a userRegister con el objeto user actualizado
  this.userRegister();

    

    }

  checkPasswords(group: FormGroup) { // Validador personalizado para comparar password y confirmPassword
    let pass = group.controls['password'].value;
    let confirmPass = group.controls['confirmPassword'].value;

    return pass === confirmPass ? null : { notSame: true }
  }

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

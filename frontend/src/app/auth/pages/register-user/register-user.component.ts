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
  showAlert = false;
  emailExists = false;
  submitted = false;


  //Router redirigir

  constructor(public authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  //Formulario reactivo -- validaciones

  registerForm: FormGroup;


  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.pattern(/^\S+@\S+\.\S+$/)]],
      fullName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+(\s[a-zA-Z]+)+$/)]],

      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', []],
      phone: ['', [Validators.required, Validators.pattern(/^\d{9}$/)]]
    }, { validator: this.checkPasswords });
  }

  // Validador personalizado para comparar password y confirmPassword
  checkPasswords(group: FormGroup) {
    let pass = group.controls['password'].value;
    let confirmPass = group.controls['confirmPassword'].value;

    return pass === confirmPass ? null : { notSame: true };
  }



  invalidField(field: string) {

    return this.registerForm.controls[field].invalid && (this.registerForm.controls[field].touched || this.registerForm.controls[field].dirty);

  }

  isConfirmPasswordInvalid() {
    const control = this.registerForm.controls['confirmPassword'];
    return this.registerForm.hasError('notSame') && control.touched;
  }

  onSubmit() {

    // Verifica si el formulario es válido
    if (this.registerForm.valid) {
      // Si es válido, realiza las acciones que necesites aquí
    } else {
      // Si el formulario es inválido, marca todos los campos como tocados
      Object.values(this.registerForm.controls).forEach(control => control.markAsTouched());

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



  userRegister() {
    this.authService.register(this.user).subscribe(
      (response: any) => {
        this.authService.currentUser = response;
        this.router.navigate(['/home']);
      },

      (error: any) => {
        console.error(error);

        this.emailExists = true;
      }
    );
  }




}

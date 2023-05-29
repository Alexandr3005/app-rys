import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../../interfaces/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-settings',
  templateUrl: './profile-settings.component.html',
  styleUrls: ['./profile-settings.component.css']
})
export class ProfileSettingsComponent implements OnInit {

  user: User;
  settingsForm: FormGroup;
  loginError = false;
  updated = false;
  private baseURL = "http://localhost:8080/api/reservation/";


  constructor(private http: HttpClient, private formBuilder: FormBuilder, 
    authService: AuthService, private router: Router) {}

  ngOnInit() {
    const currentUserString = localStorage.getItem('currentUser');
    const currentUser = currentUserString ? JSON.parse(currentUserString) : null;
    this.user = currentUser;
    
    this.settingsForm = this.formBuilder.group({
      fullName: [this.user.fullName, [Validators.required, Validators.pattern(/^[a-zA-Z]+(\s[a-zA-Z]+)+$/)]],
      password: [this.user.password, [Validators.required, Validators.minLength(6)]],
      confirmPassword: [this.user.confirmPassword, []],
      phone: [this.user.phone, [Validators.required, Validators.pattern(/^\d{9}$/)]]
    }, { validator: this.checkPasswords });


  }
 // Validador personalizado para comparar password y confirmPassword
 checkPasswords(group: FormGroup) {
  let pass = group.controls['password'].value;
  let confirmPass = group.controls['confirmPassword'].value;

  return pass === confirmPass ? null : { notSame: true };
}


  invalidField(field: string) {

    return this.settingsForm.controls[field].invalid && (this.settingsForm.controls[field].touched || this.settingsForm.controls[field].dirty);

  }

  isConfirmPasswordInvalid() {
    const control = this.settingsForm.controls['confirmPassword'];
    return this.settingsForm.hasError('notSame') && control.touched;
  }


onSubmit() {
  // Actualizar los datos del usuario utilizando los valores del formulario
  this.user.fullName = this.settingsForm.controls['fullName'].value;
  this.user.password = this.settingsForm.controls['password'].value;
  this.user.confirmPassword = this.settingsForm.controls['confirmPassword'].value;
  this.user.phone = this.settingsForm.controls['phone'].value;



  
  this.http.put(`${this.baseURL}users/${this.user.id}`, this.user, {
  }).subscribe(
    data => {
      console.log(data);
      this.updated = true;
      
      // Actualizar el usuario actual 
      localStorage.setItem('currentUser', JSON.stringify(data));

    },
    error => console.log(error)
  );
}

refreshPage(): void {
  this.router.navigateByUrl('/home').then(() => {
    window.location.reload();
  });
}
}







/////////////////// //Objeto user 

   // headers: { 'Authorization': `Bearer ${localStorage.getItem('myToken')}` }









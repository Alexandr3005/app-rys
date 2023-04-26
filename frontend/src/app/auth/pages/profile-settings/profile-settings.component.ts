import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../../interfaces/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-profile-settings',
  templateUrl: './profile-settings.component.html',
  styleUrls: ['./profile-settings.component.css']
})
export class ProfileSettingsComponent implements OnInit {

  user: User;
  settingsForm: FormGroup;
  loginError = false;

  constructor(private http: HttpClient, private formBuilder: FormBuilder, authService: AuthService) {}

  ngOnInit() {
    const currentUserString = localStorage.getItem('currentUser');
    const currentUser = currentUserString ? JSON.parse(currentUserString) : null;
    this.user = currentUser;
    
    this.settingsForm = this.formBuilder.group({
      email: [this.user.email, [Validators.required]],
      password: [this.user.password, [Validators.required]],
    });

  }

  
  

  invalidField(field: string) {

    return this.settingsForm.controls[field].invalid && (this.settingsForm.controls[field].touched || this.settingsForm.controls[field].dirty);

  }


onSubmit() {
  // Actualizar los datos del usuario utilizando los valores del formulario
  this.user.email = this.settingsForm.controls['email'].value;
  this.user.password = this.settingsForm.controls['password'].value;

  // Hacer una solicitud HTTP al backend para actualizar los datos del usuario
  this.http.put(`http://localhost:8080/api/reservation/users/${this.user.id}`, this.user, {
    headers: { 'Authorization': `Bearer ${localStorage.getItem('myToken')}` }
  }).subscribe(
    data => {
      console.log(data);
      // Actualizar el usuario actual en localStorage o sessionStorage
      localStorage.setItem('currentUser', JSON.stringify(data));
      // Recargar la página para actualizar los datos en el frontend
      //window.location.reload();
    },
    error => console.log(error)
  );
}

}







/////////////////// //Objeto user 











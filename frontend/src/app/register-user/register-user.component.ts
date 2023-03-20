import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from '../service/register.service';
import { User } from '../user';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  //Objeto user 
  user:User = new User();
 

  //Router redirigir

  constructor(private registerService: RegisterService, private router:Router) { }


  ngOnInit(): void {
    
  }

  userRegister(){
    this.registerService.registerUser(this.user).subscribe(data=>{
      this.router.navigate(['/home']);
      
    }, error=>alert("Sorry not register"))
  
  }



}

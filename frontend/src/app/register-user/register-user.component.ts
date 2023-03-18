import { Component, OnInit } from '@angular/core';
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

  constructor(private registerService: RegisterService) { }

  

  ngOnInit(): void {
  }

  userRegister(){
    this.registerService.registerUser(this.user).subscribe(data=>{
      alert("Succesfuclly registered")
    }, error=>alert("Sorry not register"))
  
  }

}

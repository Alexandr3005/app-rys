import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-setting-component',
  templateUrl: './setting-component.component.html',
  styleUrls: ['./setting-component.component.css']
})
export class SettingComponentComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {

  }

  volverHome() {

    this.router.navigate(['']);

  }

}

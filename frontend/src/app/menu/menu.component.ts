import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AppComponent, changeLang, getCurrentLang } from '../app.component';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLoggedIn = true;
  router: any;


  logout(){
    this.isLoggedIn = false;
    this.authService.logout();
    } 

  ngOnInit(): void {
  }

  selectedLang = getCurrentLang();
  constructor(private translateService: TranslateService, public authService: AuthService) {
    const currentLang = getCurrentLang();
    this.translateService.setDefaultLang(currentLang);
    this.translateService.use(currentLang);

  }

  public selectLanguage(event: any) {
    const lang = event.target.value;
    this.translateService.use(lang);
    changeLang(lang); 
  }

  

}

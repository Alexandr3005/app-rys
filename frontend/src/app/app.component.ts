import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  title = 'RYS';
  slogan = 'Reserve your seat';

  selectedLang = getCurrentLang();
  constructor(private translateService: TranslateService) {
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
//Guardar idioma en localStorage
export function getCurrentLang(): string {
  return localStorage.getItem('currentLang') || 'en-US'; 
}

export let currentLang = getCurrentLang();

export function changeLang(lang: string): void {
  currentLang = lang;
  localStorage.setItem('currentLang', lang);
}


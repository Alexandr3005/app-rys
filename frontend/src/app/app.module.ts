import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { getCurrentLang } from './app.component';  // importa la función


import { AppComponent } from './app.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { ProfileComponentComponent } from './profile-component/profile-component.component';
import { SettingComponentComponent } from './setting-component/setting-component.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { LanguagesComponent } from './languages/languages.component';
import { RegisterReserveComponent } from './register-reserve/register-reserve.component';
import { ReserveComponent } from './reserve/reserve.component';
import { SharedService } from './service/shared.service';
import { RegisterUserComponent } from './register-user/register-user.component';

const appRoutes:Routes=[
  {path:'', redirectTo: 'login', pathMatch: 'full'},
  {path:'home', component:HomeComponentComponent},
  {path:'profile', component:ProfileComponentComponent},
  {path:'setting', component:SettingComponentComponent},
  {path:'login', component:LoginComponent},
  {path:'logout', component:LogoutComponent},
  {path:'languages', component:LanguagesComponent},
  {path:'newReservation', component:RegisterReserveComponent},
  {path:'newReservation/reserve', component:ReserveComponent},
  {path:'register-user', component:RegisterUserComponent}

];

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponentComponent,
    ProfileComponentComponent,
    SettingComponentComponent,
    LoginComponent,
    LogoutComponent,
    LanguagesComponent,
    RegisterReserveComponent,
    ReserveComponent,
    RegisterUserComponent
    ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    TranslateModule.forRoot({
      defaultLanguage: getCurrentLang(),   // para mantener el idioma
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
      }
  })
  ],
  providers: [SharedService],
  bootstrap: [AppComponent]
})
export class AppModule { }

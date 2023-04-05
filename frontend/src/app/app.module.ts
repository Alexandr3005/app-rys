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
import { HomeComponentComponent } from './bookings/pages/home/home-component.component';
import { LoginComponent } from './auth/pages/login/login.component';
import { ReserveComponent } from './bookings/pages/reserve/reserve.component';
import { SharedService } from './bookings/services/shared.service';
import { RegisterUserComponent } from './auth/pages/register-user/register-user.component';
import { MenuComponent } from './bookings/pages/menu/menu.component';
import { LogoutComponent } from './auth/pages/logout/logout.component';
import { RegisterReserveComponent } from './bookings/pages/register-reserve/register-reserve.component';
import { SettingComponentComponent } from './auth/setting/setting-component.component';


const appRoutes:Routes=[
  {path:'', redirectTo: 'login', pathMatch: 'full'},
  {path:'home', component:HomeComponentComponent},
  {path:'setting', component:SettingComponentComponent},
  {path:'login', component:LoginComponent},
  {path:'logout', component:LogoutComponent},
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
    SettingComponentComponent,
    LoginComponent,
    LogoutComponent,
    RegisterReserveComponent,
    ReserveComponent,
    RegisterUserComponent,
    MenuComponent
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

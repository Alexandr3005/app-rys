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

import { AuthLoginGuard } from './auth/auth-login.guard';
import { ReactiveFormsModule } from '@angular/forms';
import { ProfileSettingsComponent } from './auth/pages/profile-settings/profile-settings.component';


const appRoutes: Routes = [
  { path: '', redirectTo: 'auth/login', pathMatch: 'full' },
  { path: 'home', component: HomeComponentComponent, canActivate: [AuthLoginGuard] },
  { path: 'newReservation', component: RegisterReserveComponent, canActivate: [AuthLoginGuard] },
  { path: 'newReservation/reserve', component: ReserveComponent, canActivate: [AuthLoginGuard] },
  { path: '', loadChildren: () => 
              import('./auth/auth-routing.module').then(m => m.AuthRoutingModule) },
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
    MenuComponent,
    ProfileSettingsComponent
    
    
    ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    TranslateModule.forRoot({
      defaultLanguage: getCurrentLang(), 
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

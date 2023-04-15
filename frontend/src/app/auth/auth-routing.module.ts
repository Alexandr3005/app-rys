import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SettingComponentComponent } from './setting/setting-component.component';
import { LoginComponent } from './pages/login/login.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { AuthLoginGuard } from './auth-login.guard';

const routes: Routes = [
  { path: 'auth/settings', component: SettingComponentComponent, canActivate: [AuthLoginGuard] },
  { path: 'auth/login', component: LoginComponent },
  { path: 'auth/logout', component: LogoutComponent, canActivate: [AuthLoginGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
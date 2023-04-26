import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SettingComponentComponent } from './setting/setting-component.component';
import { LoginComponent } from './pages/login/login.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { AuthLoginGuard } from './auth-login.guard';
import { RegisterUserComponent } from './pages/register-user/register-user.component';
import { ProfileSettingsComponent } from './pages/profile-settings/profile-settings.component';

const routes: Routes = [
  { path: 'auth/settings', component: ProfileSettingsComponent, canActivate: [AuthLoginGuard] },
  { path: 'auth/login', component: LoginComponent },
  { path: 'auth/logout', component: LogoutComponent, canActivate: [AuthLoginGuard] },
  { path: 'auth/register-user', component: RegisterUserComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
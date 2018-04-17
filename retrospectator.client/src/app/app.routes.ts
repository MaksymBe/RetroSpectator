import { Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {CallbackComponent} from './auth/callback/callback.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {SprintComponent} from './dashboard/sprint/sprint.component';
import {RetroComponent} from './dashboard/retro/retro.component';

export const ROUTES: Routes = [
  { path: '', component: AuthComponent },
  { path: 'callback', component: CallbackComponent },
  { path: 'dashboard', component: DashboardComponent, children: [
      { path: '', redirectTo: 'sprint', pathMatch: 'full'},
      { path: 'sprint', component: SprintComponent},
      { path: 'retro', component: RetroComponent}
    ]},
  { path: '**', redirectTo: '' }
];

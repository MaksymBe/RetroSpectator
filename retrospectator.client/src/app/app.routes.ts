import { Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {CallbackComponent} from './auth/callback/callback.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {SprintComponent} from './dashboard/sprint/sprint.component';
import {RetroComponent} from './dashboard/retro/retro.component';
import {RetroHistoryComponent} from './dashboard/retro-history/retro-history.component';

export const ROUTES: Routes = [
  { path: '', component: AuthComponent },
  { path: 'callback', component: CallbackComponent },
  { path: 'dashboard', component: DashboardComponent, children: [
      { path: '', component: SprintComponent},
      { path: 'new-team', component: SprintComponent},
      { path: ':teamKey', component: SprintComponent},
      { path: ':teamKey/retro', component: RetroComponent},
      { path: ':teamKey/retros', component: RetroHistoryComponent},
      { path: ':teamKey/:mode', component: SprintComponent}
    ]},
  { path: '**', redirectTo: '' }
];

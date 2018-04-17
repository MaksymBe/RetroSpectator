import { Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {CallbackComponent} from './auth/callback/callback.component';

export const ROUTES: Routes = [
  { path: '', component: AuthComponent },
  { path: 'callback', component: CallbackComponent },
  { path: '**', redirectTo: '' }
];

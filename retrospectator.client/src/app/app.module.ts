import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthComponent } from './auth/auth.component';
import { RetroComponent } from './dashboard/retro/retro.component';
import { RetroHistoryComponent } from './dashboard/retro-history/retro-history.component';
import { SprintComponent } from './dashboard/sprint/sprint.component';
import {  FormsModule } from '@angular/forms';
import { PointsListComponent } from './dashboard/points-list/points-list.component';
import {Auth0Service} from './data-service/services/auth/auth0.service';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';

import { ROUTES } from './app.routes';
import { CallbackComponent } from './auth/callback/callback.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AuthComponent,
    RetroComponent,
    RetroHistoryComponent,
    SprintComponent,
    PointsListComponent,
    CallbackComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [Auth0Service],
  bootstrap: [AppComponent]
})
export class AppModule { }

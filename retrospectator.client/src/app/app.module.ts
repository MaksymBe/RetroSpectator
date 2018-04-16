import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthComponent } from './auth/auth.component';
import { RetroComponent } from './dashboard/retro/retro.component';
import { RetroHistoryComponent } from './dashboard/retro-history/retro-history.component';
import { SprintComponent } from './dashboard/sprint/sprint.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AuthComponent,
    RetroComponent,
    RetroHistoryComponent,
    SprintComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

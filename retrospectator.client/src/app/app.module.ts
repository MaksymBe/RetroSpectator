import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuthComponent} from './auth/auth.component';
import {RetroComponent} from './dashboard/retro/retro.component';
import {RetroHistoryComponent} from './dashboard/retro-history/retro-history.component';
import {SprintComponent} from './dashboard/sprint/sprint.component';
import {FormsModule} from '@angular/forms';
import {PointsListComponent} from './dashboard/points-list/points-list.component';

import {Auth0Service} from './data-service/services/auth/auth0.service';
import {RouterModule} from '@angular/router';

import {NavBarComponent} from './dashboard/nav-bar/nav-bar.component';


import {ROUTES} from './app.routes';
import {CallbackComponent} from './auth/callback/callback.component';
import {TeamService} from './data-service/services/team/team.service';
import {NewTeamComponent} from './dashboard/nav-bar/new-team/new-team.component';

import {ActionPointsListComponent} from './dashboard/action-points-list/action-points-list.component';


import {HttpClientModule} from '@angular/common/http';
import {JwtModule} from '@auth0/angular-jwt';
import {PointService} from './data-service/services/point/point.service';
import {ActionPointService} from './data-service/services/action-point/action-point.service';
import {UserService} from './data-service/services/user/user.service';
import {RetroService} from './data-service/services/retro/retro.service';
import {environment} from '../environments/environment';
import { PointComponent } from './dashboard/points-list/point/point.component';
import { HeaderBarComponent } from './header-bar/header-bar.component';



@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AuthComponent,
    RetroComponent,
    RetroHistoryComponent,
    SprintComponent,
    PointsListComponent,
    CallbackComponent,
    NavBarComponent,
    NewTeamComponent,
    ActionPointsListComponent,
    PointComponent,
    HeaderBarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: environment.getToken,
        whitelistedDomains: ['localhost:3000', '165.227.146.253:3000']
      }
    }),
    RouterModule.forRoot(ROUTES)
  ],
  providers: [Auth0Service, TeamService, PointService, ActionPointService, UserService, RetroService],
  bootstrap: [AppComponent]
})
export class AppModule {
}



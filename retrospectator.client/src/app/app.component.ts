import {Component} from '@angular/core';
import {Auth0Service} from './data-service/services/auth/auth0.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'RetroSpectator';

  constructor(public auth: Auth0Service) {
    auth.handleAuthentication();
  }
}

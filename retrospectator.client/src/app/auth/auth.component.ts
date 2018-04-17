import {Component, OnInit} from '@angular/core';
import {Auth0Service} from '../data-service/services/auth/auth0.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor(public auth: Auth0Service) {
    auth.handleAuthentication();
  }

  ngOnInit() {
  }

}

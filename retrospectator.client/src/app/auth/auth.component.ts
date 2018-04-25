import {Component, OnInit} from '@angular/core';
import {Auth0Service} from '../data-service/services/auth/auth0.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  public authorized = false;

  constructor(public auth: Auth0Service, private router: Router) {
    auth.handleAuthentication();
    auth.isAuthenticated().subscribe(next => this.authorized = next);
    if (this.authorized) {
      this.router.navigate(['dashboard']);
    }
  }

  ngOnInit() {
  }

}

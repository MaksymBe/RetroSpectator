import {Component, OnInit} from '@angular/core';
import {Auth0Service} from '../data-service/services/auth/auth0.service';
import {TeamService} from '../data-service/services/team/team.service';
import {PointService} from '../data-service/services/point/point.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  constructor(public auth: Auth0Service, private router: Router) {
    auth.handleAuthentication();
    if (this.auth.isAuthenticated()) {
      this.router.navigate(['dashboard']);
    }
  }

  ngOnInit() {
  }

}

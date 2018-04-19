import {Component, OnInit} from '@angular/core';
import {Auth0Service} from '../data-service/services/auth/auth0.service';
import {TeamService} from '../data-service/services/team/team.service';
import {PointService} from '../data-service/services/point/point.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  constructor(public auth: Auth0Service, public testService: PointService) {
    auth.handleAuthentication();
  }

  ngOnInit() {
  }

}

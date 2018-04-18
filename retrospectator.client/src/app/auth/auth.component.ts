import {Component, OnInit} from '@angular/core';
import {Auth0Service} from '../data-service/services/auth/auth0.service';
import {TeamService} from '../data-service/services/team/team.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor(public auth: Auth0Service, private teams: TeamService) {
    auth.handleAuthentication();
  }

  ngOnInit() {
  }

  test() {
    this.teams.getTeams().subscribe(data => console.log(data));
  }

}

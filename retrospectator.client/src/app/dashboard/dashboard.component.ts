import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Auth0Service} from '../data-service/services/auth/auth0.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private userService: Auth0Service,
              private router: Router) { }

  ngOnInit() {
    if (!this.userService.isAuthenticated()) {
      this.router.navigate(['']);
    }

    const teamKey = localStorage.getItem('teamKey');
    if (teamKey !== undefined && teamKey !== null && teamKey !== 'null') {
      this.router.navigate(['dashboard/' + teamKey + '/my']);
    }
  }

}

import { Component, OnInit} from '@angular/core';

import { TeamService } from '../../data-service/services/team/team.service';
import {Team} from '../../data-service/model/Team';
import {Router} from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  private teams = [];
  isCreatingMode = false;

  constructor(private teamService: TeamService, private router: Router) {  }

  ngOnInit() {
    this.teamService.getTeams().subscribe((teams) => {
      this.teams = teams;
      if (teams.length === 0) {
        this.router.navigate(['dashboard', 'new-team']);
      }
    });
    if (this.router.url === '/dashboard/new-team') {
      this.isCreatingMode = true;
    }
  }
}

import { Component, OnInit} from '@angular/core';

import { TeamService } from '../../data-service/services/team/team.service';
import {Team} from '../../data-service/model/Team';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  public teams = [];
  public currentTeam = {title: 'Team'};
  isCreatingMode = false;

  constructor(private teamService: TeamService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {  }

  ngOnInit() {
    this.teamService.getTeams().subscribe((teams) => {
      this.teams = teams;
      if (teams === null || teams.length === 0) {
        this.router.navigate(['dashboard', 'new-team']);
      }

      const teamId = localStorage.getItem('teamKey');
      if (teamId !==  null) {
        this.currentTeam = this.teams.filter(team => team.identifier === teamId)[0];
      }
    });
    if (this.router.url === '/dashboard/new-team') {
      this.currentTeam = {title: 'Team'};
      this.isCreatingMode = true;
    }
  }

  addTeam(team: Team) {
    this.teams.push(team);
    this.currentTeam = team;
    this.router.navigate(['dashboard', team.identifier, 'my']);
  }

  changeTeam(team: Team) {
    this.currentTeam = team;
    this.router.navigate(['dashboard/', team.identifier, '/my']);
  }
}

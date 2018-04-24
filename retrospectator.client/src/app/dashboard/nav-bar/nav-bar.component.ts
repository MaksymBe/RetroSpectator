import {Component, OnInit} from '@angular/core';

import {TeamService} from '../../data-service/services/team/team.service';
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
  isRenamingMode = false;

  constructor(public teamService: TeamService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.teamService.getTeams().subscribe((teams) => {
      this.teams = teams;
    });
    this.teamService.getCurrentTeam().subscribe(team => {
      this.currentTeam = team;
    });
    if (this.router.url === '/dashboard/new-team') {
      this.isCreatingMode = true;
    }
  }

  addTeam(newTeam: Team) {
    this.teamService.createTeam({title: newTeam.title})
      .subscribe(team => {
        this.teams.push(team);
        this.isCreatingMode = !this.isCreatingMode;
        this.router.navigate(['dashboard', team.identifier, 'my']);
      });
  }

  renameTeam(team: Team) {
    this.teamService.updateTeam({title: team.title, identifier: localStorage.getItem('teamKey')})
      .subscribe(resTeam => {
        console.log(resTeam);
        this.isRenamingMode = !this.isRenamingMode;
        this.currentTeam = resTeam;
      });
  }

  changeTeam(team: Team) {
    this.teamService.setCurrentTeam(team);
    this.router.navigate(['dashboard/', team.identifier, '/my']);
  }
}

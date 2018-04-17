import { Component, OnInit} from '@angular/core';

import { TeamService } from '../../data-service/services/team/team.service';
import {Team} from '../../data-service/model/Team';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  private teams = [];
  isCreatingMode = false;

  constructor(private teamService: TeamService) {  }

  ngOnInit() {
    this.teamService.getTeams().subscribe((teams) => {
      this.teams = teams;
    });
  }
}

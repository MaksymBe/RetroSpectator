import {Component, OnInit} from '@angular/core';

import {TeamService} from '../../data-service/services/team/team.service';
import {Team} from '../../data-service/model/Team';
import {ActivatedRoute, Router} from '@angular/router';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  public teams = [];
  public currentTeam: Team;
  public currentActive: string;
  public textToCopy: string;
  isRenamingMode = false;
  public env = environment;

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
      this.currentActive = 'my';
    });

    const link = this.router.url.split('/');
    if (link[link.length - 1] === 'my') {
      this.currentActive = 'all';
    } else {
      this.currentActive = 'my';
    }
  }

  renameTeam(team: Team) {
    this.teamService.updateTeam({title: team.title, identifier: localStorage.getItem('teamKey')})
      .subscribe(resTeam => {
        this.isRenamingMode = !this.isRenamingMode;
        this.currentTeam = resTeam;
      });
  }

  switchPoints(link: string) {
    this.router.navigate(['dashboard', this.currentTeam.identifier, link]);
    this.currentActive = link;
  }

  goToRetro() {
    this.currentActive = 'retro';
    this.router.navigate(['dashboard', this.currentTeam.identifier, 'retro']);
  }

  getLink() {
    let link = document.URL;
    let host = 'http://';
    for (let i = 7; link[i] !== '/'; i++) {
      host += link[i];
    }

    return host + '/' + this.currentTeam.identifier;
  }
}


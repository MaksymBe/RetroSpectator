import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route, Router} from '@angular/router';

@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.css']
})
export class SprintComponent implements OnInit {
  private titleInput: string;

  private pointsMinus = [];
  private pointsPlus = [];
  private isMine = true;
  private teamKey;
  private chooseMode = false;
  private createTeamMode = false;

  constructor(private activatedRouter: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    if (this.router.url === '/dashboard/new-team') {
      this.createTeamMode = true;
      this.chooseMode = false;
      return;
    }

    this.activatedRouter.params.subscribe((params) => {
      if (params.teamKey === undefined) {
        this.chooseMode = true;
      }  else {
        this.chooseMode = false;

        if (params.mode === undefined || params.mode === null) {
          this.router.navigate(['dashboard', params.teamKey, 'my']);
        }

        if (params.mode === 'all') {
          this.getTeamPoints(params.teamKey);
        } else if (params.mode === 'my') {
          this.getMyPoints(params.teamKey);
        } else {
          this.router.navigate(['dashboard', params.teamKey, 'my']);
        }

        this.teamKey = params.teamKey;
        localStorage.setItem('teamKey', this.teamKey);
      }
    });
  }

  getTeamPoints(teamKey) {

  }
  getMyPoints(teamKey) {

  }

  addPoint(type) {
    if (this.titleInput === '' || this.titleInput === undefined || this.titleInput === null) {
      return;
    }

    if (type === 'minus') {
      this.pointsMinus.push({type: 'minus', title: this.titleInput});
    }
    if (type === 'plus') {
      this.pointsPlus.push({type: 'plus', title: this.titleInput});
    }

    this.titleInput = '';
  }

  changePoints() {
    if (this.isMine) {
      this.getTeamPoints(this.teamKey);
      this.router.navigate(['dashboard', this.teamKey, 'all']);
    } else {
      this.getMyPoints(this.teamKey);
      this.router.navigate(['dashboard', this.teamKey, 'my']);
    }

    this.isMine = !this.isMine;
  }
}

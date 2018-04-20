import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {Point} from '../../data-service/model/Point';
import {PointService} from '../../data-service/services/point/point.service';
import {TeamService} from '../../data-service/services/team/team.service';

@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.css']
})
export class SprintComponent implements OnInit {
  private titleInput: string;

  private points: { minus: Point[], plus: Point[] };
  private isMine = true;
  private teamKey;
  private chooseMode = false;
  private createTeamMode = false;
  private editPointMode = false;
  private pointToEdit;

  constructor(private activatedRouter: ActivatedRoute,
              private router: Router,
              private pointService: PointService,
              private teamService: TeamService) {
    this.points = {minus: [], plus: []};
  }

  ngOnInit() {
    // if (this.router.url === '/dashboard/new-team') {
    //   this.createTeamMode = true;
    //   this.chooseMode = false;
    //   return;
    // }

    this.activatedRouter.params.subscribe((params) => {
      this.teamService.getTeam(this.teamKey).subscribe(team => {
        if (team === null) {
          this.router.navigate(['dashboard']);
        }
      } );

      if (params.teamKey === undefined) {
        this.chooseMode = true;
      } else {
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

  changePoints() {
    if (this.isMine) {
      this.getTeamPoints(this.teamKey);
      this.router.navigate(['dashboard', localStorage.getItem('teamKey'), 'all']);
    } else {
      this.getMyPoints(this.teamKey);
      this.router.navigate(['dashboard', localStorage.getItem('teamKey'), 'my']);
    }

    this.isMine = !this.isMine;
  }

  getTeamPoints(teamKey) {
    this.pointService.getTeamPoints(teamKey).subscribe(points => this.points = points);
  }

  getMyPoints(teamKey) {
    this.pointService.getMyPoints(teamKey).subscribe((points) => {
      console.log(points);
      this.points = points;
      console.log(this.points);
    });
  }

  addPoint(type) {
    if (this.titleInput === '' || this.titleInput === undefined || this.titleInput === null) {
      return;
    }

    this.pointService.createPoint(new Point(this.titleInput, type, getDate()), this.teamKey).subscribe(point => {
      if (!point) {
        return;
      }

      this.points[type].push(point);
    });
    this.titleInput = '';
  }

  switchEditMode(point) {
    this.editPointMode = !this.editPointMode;
    (this.editPointMode) ? this.pointToEdit = point : this.pointToEdit = {};
    this.titleInput = this.pointToEdit.title;
  }
  editPoint() {
    this.pointToEdit.title = this.titleInput;
    this.pointService.updatePoint(this.pointToEdit).subscribe(res => {
      this.editPointMode = false;
      this.titleInput = '';
    });
  }

  goToRetro() {
    this.activatedRouter.params.subscribe(params => {
      this.router.navigate(['dashboard/', params.teamKey, '/retro']);
    });
  }
}

function getDate(): string {
  const date = new Date();
  return <string>(date.getFullYear() + '-' +
  (date.getMonth() < 10) ? '0' + date.getMonth() : date.getMonth() + '-' +
  (date.getDate() < 10) ? '0' + date.getDate() : date.getDate());
}

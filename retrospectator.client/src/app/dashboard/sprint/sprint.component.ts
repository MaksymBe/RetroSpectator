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
  public titleInput: string;

  public points: { minus: Point[], plus: Point[] };
  private isMine = true;
  private teamKey;
  private chooseMode = false;
  private createTeamMode = false;
  public editPointMode = false;
  public pointToEdit;
  public pointType = 'plus';

  constructor(private activatedRouter: ActivatedRoute,
              private router: Router,
              private pointService: PointService,
              private teamService: TeamService) {
    this.points = {minus: [], plus: []};
  }

  ngOnInit() {
    if (this.router.url === '/dashboard/new-team') {
      this.createTeamMode = true;
      this.chooseMode = false;
      return;
    }

    this.activatedRouter.params.subscribe((params) => {
      // this.teamService.getTeam(this.teamKey).subscribe(team => {
      //   if (team === null) {
      //     this.router.navigate(['dashboard']);
      //   }
      // } );

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

  pointHandler() {
    if (this.titleInput !== '' && this.titleInput !== undefined && this.titleInput !== null) {
      if (this.pointType === 'edit') {
        this.editPoint();
        this.pointType = 'plus';
      } else if (this.pointType === 'minus' || this.pointType === 'plus') {
        this.addPoint();
      }
    }
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
      this.points = points;
    });
  }

  addPoint() {
    this.pointService.createPoint(new Point(this.titleInput, this.pointType, getDate()), this.teamKey).subscribe(point => {
      if (!point) {
        return;
      }

      this.points[this.pointType].push(point);
    });
    this.titleInput = '';
  }

  switchEditMode(point, type) {
    this.changePointType(type);
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
      this.router.navigate(['dashboard', params.teamKey, 'retro']);
    });
  }

  changePointType(pointType: string) {
    this.pointType = pointType;
  }
}

function getDate(): string {
  const date = new Date();
  return <string>(date.getFullYear() + '-' +
  (date.getMonth() < 10) ? '0' + date.getMonth() : date.getMonth() + '-' +
  (date.getDate() < 10) ? '0' + date.getDate() : date.getDate());
}

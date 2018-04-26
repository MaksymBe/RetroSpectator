import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {Point} from '../../data-service/model/Point';
import {PointService} from '../../data-service/services/point/point.service';
import {TeamService} from '../../data-service/services/team/team.service';
import {Team} from '../../data-service/model/Team';

@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.css']
})
export class SprintComponent implements OnInit {
  public titleInput: string;

  public points: { minus: Point[], plus: Point[] };
  private teamKey;
  public chooseMode = false;
  public createTeamMode = false;
  public pointType = 'plus';
  public currentTeam: Team;

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

    this.teamService.getCurrentTeam().subscribe(team => {
      this.currentTeam = team;

      console.log(team);
      if ( this.currentTeam === null) {
        this.teamService.getTeams().subscribe(teams => {
           if ( teams === null || teams === undefined || teams.length === 0) {
             this.createTeamMode = true;
           } else {
             this.chooseMode = true;
           }
        });
      } else {
        this.chooseMode = false;
        this.createTeamMode = false;
        this.activatedRouter.params.subscribe((params) => {
          if (params.mode === undefined || params.mode === null) {
            this.router.navigate(['dashboard', this.currentTeam.identifier, 'my']);
          }

          if (params.mode === 'all') {
            this.getTeamPoints(params.teamKey);
          } else if (params.mode === 'my') {
            this.getMyPoints(params.teamKey);
          } else {
            if (params.mode !== 'retro') {
              this.router.navigate(['dashboard', this.currentTeam.identifier, 'my']);
            }
          }

          this.teamKey = params.teamKey;
          localStorage.setItem('teamKey', this.teamKey);
        });
      }
    });
  }

  pointHandler(pointType: string) {
    if (this.titleInput !== '' && this.titleInput !== undefined && this.titleInput !== null) {
      if (this.pointType === pointType) {
        this.addPoint();
      } else {
        this.changePointType(pointType);
        this.addPoint();
      }
    }
    this.changePointType(pointType);
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


  changePointType(pointType: string) {
    this.pointType = pointType;
  }

  getOrderedPoints(points: Point[]): Point[] {
    const orderedPoints = points;

    orderedPoints.sort((a, b) => {
      return (a.date < b.date) ? 1 : (a.date === b.date) ? 0 : -1;
    });

    return orderedPoints;
  }
}

function getDate(): string {
  const date = new Date();
  return <string>(date.getFullYear() + '-' +
  (date.getMonth() < 10) ? '0' + date.getMonth() : date.getMonth() + '-' +
  (date.getDate() < 10) ? '0' + date.getDate() : date.getDate());
}

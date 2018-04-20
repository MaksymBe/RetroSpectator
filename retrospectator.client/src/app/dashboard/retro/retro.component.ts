import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Point} from '../../data-service/model/Point';
import {APoint} from '../../data-service/model/APoint';
import {ActionPointService} from '../../data-service/services/action-point/action-point.service';
import {PointService} from '../../data-service/services/point/point.service';

@Component({
  selector: 'app-retro',
  templateUrl: './retro.component.html',
  styleUrls: ['./retro.component.css']
})
export class RetroComponent implements OnInit {

  private points: {minus: Point[], plus: Point[]};
  public actionPoints: APoint[];
  private titleInput: string;

  constructor(private activetedRouter: ActivatedRoute,
              private actionPointService: ActionPointService,
              private pointService: PointService) {
    this.points = {minus: [], plus: []};
  }

  ngOnInit() {
    this.activetedRouter.params.subscribe(params => {
      this.getTeamPoints(params.teamKey);
    });
  }

  getTeamPoints(teamIdentifier) {
    this.pointService.getTeamPoints(teamIdentifier).subscribe(points => {
      this.points = points;
      console.log(points);
    });

    this.actionPointService.getActionPointsByTeam(teamIdentifier).subscribe(actionPoints => {
      this.actionPoints = actionPoints;
      console.log(actionPoints);
    });
  }

  addActionPoint() {
    const newActionPoint = new APoint();
    newActionPoint.status = 0;
    newActionPoint.title = this.titleInput;

    this.activetedRouter.params.subscribe(params => {
      this.actionPointService.createActionPoint(newActionPoint, params.teamKey).subscribe(actionPoint => {
        if (actionPoint !== null) {
          this.actionPoints.push(actionPoint);
        }
      });
    });

    this.titleInput = '';
  }
}

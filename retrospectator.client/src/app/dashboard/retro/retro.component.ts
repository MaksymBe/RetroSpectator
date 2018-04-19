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
  public actionPoints: APoint[] = [{title: 'ap',
    status: true,
    id: 1,
    retroId: 2}];
  private titleInput: string;

  constructor(private activetedRouter: ActivatedRoute,
              private actionPointService: ActionPointService,
              private pointService: PointService) { }

  ngOnInit() {
    this.activetedRouter.params.subscribe(params => {
      this.getTeamPoints(params.teamKey);
    });
  }

  getTeamPoints(teamIdentifier) {
    this.pointService.getTeamPoints(teamIdentifier).subscribe(points => {
      this.points = points;
    });

    this.actionPointService.getActionPointsByTeam(teamIdentifier).subscribe(actionPoints => {
      this.actionPoints = actionPoints;
    });
  }

  addActionPoint() {
    const newActionPoint = new APoint();
    newActionPoint.status = false;
    newActionPoint.title = this.titleInput;

    this.activetedRouter.params.subscribe(params => {
      this.actionPointService.addActionPoint(params.teamKey, newActionPoint).subscribe(actionPoint => {
        if (actionPoint !== null) {
          this.actionPoints.push(actionPoint);
        }
      });
    });

    this.titleInput = '';
  }
}

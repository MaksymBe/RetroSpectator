import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Point} from '../../data-service/model/Point';
import {APoint} from '../../data-service/model/APoint';
import {ActionPointService} from '../../data-service/services/action-point/action-point.service';
import {PointService} from '../../data-service/services/point/point.service';
import {RetroService} from '../../data-service/services/retro/retro.service';
import {log} from 'util';

@Component({
  selector: 'app-retro',
  templateUrl: './retro.component.html',
  styleUrls: ['./retro.component.css']
})
export class RetroComponent implements OnInit {

  public points: {minus: Point[], plus: Point[]};
  public actionPoints: APoint[];
  public titleInput: string;
  public teamKey: string;

  constructor(private activetedRouter: ActivatedRoute,
              private actionPointService: ActionPointService,
              private pointService: PointService,
              private retroService: RetroService) {
    this.points = {minus: [], plus: []};
  }

  ngOnInit() {
    this.activetedRouter.params.subscribe(params => {
      /*if (params.retroId !== undefined && params.retroId !== null) {
        this.retroService.getRetroById(params.retroId).subscribe(retro => {
          this.points = {minus: retro.}
        });
      }*/
      this.getTeamPoints(params.teamKey);
      this.teamKey = params.teamKe;
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

  finishRetro(impression: string) {
    this.retroService.closeRetro(this.teamKey, impression).subscribe(retro => console.log(retro));
  }
}

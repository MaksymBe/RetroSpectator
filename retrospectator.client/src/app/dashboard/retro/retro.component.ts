import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Point} from '../../data-service/model/Point';
import {APoint} from '../../data-service/model/APoint';

@Component({
  selector: 'app-retro',
  templateUrl: './retro.component.html',
  styleUrls: ['./retro.component.css']
})
export class RetroComponent implements OnInit {

  private points: {minus: Point[], plus: Point[]};
  private actionPoints: APoint[] = [{title: 'ap',
    status: true,
    id: 1,
    retroId: 2}];
  private titleInput: string;

  constructor(private activetedRouter: ActivatedRoute) { }

  ngOnInit() {
    this.activetedRouter.params.subscribe(params => {
      this.getTeamPoints(params.teamKey);
    });
  }

  getTeamPoints(teamIdentifier) {
    this.points = {minus: [], plus: []};
  }

  addActionPoint() {
    this.actionPoints.push({title: this.titleInput,
      status: true,
      id: 1,
      retroId: 2});

    this.titleInput = '';
  }
}

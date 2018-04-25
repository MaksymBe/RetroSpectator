import {Component, Input, OnInit} from '@angular/core';
import {APoint} from '../../data-service/model/APoint';
import {Point} from '../../data-service/model/Point';

@Component({
  selector: 'app-action-points-list',
  templateUrl: './action-points-list.component.html',
  styleUrls: ['./action-points-list.component.css']
})
export class ActionPointsListComponent implements OnInit {

  @Input() actionPoints: APoint[];
  @Input() title: string;

  constructor() { }

  ngOnInit() {
  }

  deleteFromArray(point: Point) {
    this.actionPoints.forEach((item: APoint, index: number) => {
      if (item.id === point.id) {
        this.actionPoints.splice(index, 1);
      }
    });
  }
}

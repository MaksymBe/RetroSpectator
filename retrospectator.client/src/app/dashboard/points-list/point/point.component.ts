import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Point} from '../../../data-service/model/Point';
import {PointService} from '../../../data-service/services/point/point.service';

@Component({
  selector: 'app-point',
  templateUrl: './point.component.html',
  styleUrls: ['./point.component.css']
})
export class PointComponent implements OnInit {
  public enableFormToEdit = false;
  private pointService: PointService;

  constructor(pointService: PointService) {
    this.pointService = pointService;
  }

  @Input() point: Point;
  @Output('delete') deleteFromArray: EventEmitter<Point> = new EventEmitter();

  ngOnInit() {
  }

  editPoint(): void {
    this.enableFormToEdit = true;
  }

  cancelEditMode(event): void {
    this.enableFormToEdit = !event;
  }

  updatePoint(newTitle: string) {

    const point: Point = Object.assign({}, this.point);
    point.title = newTitle;
    this.pointService.updatePoint(point).subscribe(res => {
      this.point.title = res.title;
      this.enableFormToEdit = false;
    });

  }

  deletePoint() {
    this.pointService.deletePoint(this.point.id).subscribe((res: Point) => {
      this.deleteFromArray.emit(res);
    });
  }
}

import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {APoint} from '../../../data-service/model/APoint';
import {ActionPointService} from '../../../data-service/services/action-point/action-point.service';
import {Point} from '../../../data-service/model/Point';

@Component({
  selector: 'app-action-point',
  templateUrl: './action-point.component.html',
  styleUrls: ['./action-point.component.css']
})
export class ActionPointComponent implements OnInit {

  public enableFormToEdit = false;
  private actionPointService: ActionPointService;

  constructor(actionPointService: ActionPointService) {
    this.actionPointService = actionPointService;
  }

  @Input() actionPoint: APoint;
  @Output('delete') deletePointFromArray: EventEmitter<APoint> = new EventEmitter();

  ngOnInit() {
  }


  cancelEditMode(event): void {
    this.enableFormToEdit = !event;
  }

  updatePoint(newTitle: string) {

    const actionPoint: APoint = Object.assign({}, this.actionPoint);
    actionPoint.title = newTitle;
    this.actionPointService.updatePoint(actionPoint).subscribe(res => {
      this.actionPoint.title = res.title;
      this.enableFormToEdit = false;
    });

  }

  editActionPoint(): void {
    this.enableFormToEdit = true;
  }

  deleteActionPoint() {
    this.actionPointService.deletePoint(this.actionPoint.id).subscribe((res: APoint) => {
      this.deletePointFromArray.emit(res);
    });
  }

}

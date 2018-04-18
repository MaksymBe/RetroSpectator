import {Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import {Point} from '../../data-service/model/Point';

@Component({
  selector: 'app-points-list',
  templateUrl: './points-list.component.html',
  styleUrls: ['./points-list.component.css']
})
export class PointsListComponent implements OnInit {

  @Input() type: string;
  @Input() points: Point[];
  @Input() noButton: boolean;
  @Input() title: string;

  @Output('add') add: EventEmitter<string> = new EventEmitter<string>();

  constructor() {
    this.noButton = false;
  }

  ngOnInit() {}

  addPoint() {
    this.add.emit(this.type);
  }

  getButtonLabel(): string{
    if (this.type === 'minus') {
      return '-';
    } else if (this.type === 'plus') {
      return '+';
    }
    return 'add';
  }
}

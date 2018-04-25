import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-button-group',
  templateUrl: './button-group.component.html',
  styleUrls: ['./button-group.component.css']
})
export class ButtonGroupComponent implements OnInit {
  @Output('delete') onDeleteElement: EventEmitter<string> = new EventEmitter();
  @Output('edit') onEditElement: EventEmitter<string> = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  deletePoint(type: string) {
    this.onDeleteElement.emit(type);
  }

  editPoint(type: string) {
    this.onEditElement.emit(type);
  }
}

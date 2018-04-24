import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PointService} from '../../data-service/services/point/point.service';

@Component({
  selector: 'app-edit-point-form',
  templateUrl: './edit-point-form.component.html',
  styleUrls: ['./edit-point-form.component.css']
})
export class EditPointFormComponent implements OnInit {
  @Input() pointTitle: string;
  @Output('cancel') cancel: EventEmitter<boolean> = new EventEmitter();
  @Output('changeTitle') changeTitle: EventEmitter<string> = new EventEmitter();
  public newTitle: string;
  private pointService: PointService;

  constructor( ) {

  }

  ngOnInit() {
  }

  cancelForm(closeForm: boolean) {
    this.cancel.emit(closeForm);
  }

  changePoint(newTitle: string) {
    this.changeTitle.emit(newTitle);
  }

}

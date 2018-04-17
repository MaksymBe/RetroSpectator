import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TeamService} from '../../../data-service/services/team/team.service';

@Component({
  selector: 'app-new-team',
  templateUrl: './new-team.component.html',
  styleUrls: ['./new-team.component.css']
})
export class NewTeamComponent implements OnInit {
  @Output('changeMode') changeModeEmitter: EventEmitter = new EventEmitter();

  private teamTitle: string;

  constructor(private teamService: TeamService) {
  }

  ngOnInit() {
  }

  createTeam() {
    if (this.teamTitle === '' || this.teamTitle === undefined || this.teamTitle === null) {
      return;
    }

    this.teamService.createTeam({title: this.teamTitle, key: null, id: null});
    this.changeModeEmitter.emit();
  }

  changeMode() {
    this.changeModeEmitter.emit();
  }
}

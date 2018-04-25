import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TeamService} from '../../data-service/services/team/team.service';
import {MatInputModule} from '@angular/material/input';
import {Team} from '../../data-service/model/Team';


@Component({
  selector: 'app-new-team',
  templateUrl: './new-team.component.html',
  styleUrls: ['./new-team.component.css']
})
export class NewTeamComponent implements OnInit {
  @Input('title') title: string;

  @Output('changeMode') changeModeEmitter: EventEmitter<null> = new EventEmitter<null>();
  @Output('newTeamTitle') newTeamTitle: EventEmitter<Team> = new EventEmitter<Team>();

  public teamTitle: string;
  public inputN = 'Title';

  constructor() {
  }

  ngOnInit() {
    if (this.title === 'Join') {
      this.inputN = 'Team identifier';
    }
  }

  createTeam() {
    if (this.teamTitle === '' || this.teamTitle === undefined || this.teamTitle === null) {
      return;
    }

    const team = new Team();

    team.title = this.teamTitle;
    this.newTeamTitle.emit(team);
    this.changeMode();
  }

  changeMode() {
    this.changeModeEmitter.emit();
  }
}

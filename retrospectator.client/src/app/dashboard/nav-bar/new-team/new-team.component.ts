import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TeamService} from '../../../data-service/services/team/team.service';
import {MatInputModule} from '@angular/material/input';
import {Team} from '../../../data-service/model/Team';


@Component({
  selector: 'app-new-team',
  templateUrl: './new-team.component.html',
  styleUrls: ['./new-team.component.css']
})
export class NewTeamComponent implements OnInit {
  @Output('changeMode') changeModeEmitter: EventEmitter<null> = new EventEmitter<null>();
  @Output('teamCreated') teamCreated: EventEmitter<Team> = new EventEmitter<Team>();

  private teamTitle: string;

  constructor(private teamService: TeamService) {
  }

  ngOnInit() {
  }

  createTeam() {
    if (this.teamTitle === '' || this.teamTitle === undefined || this.teamTitle === null) {
      return;
    }

    this.teamService.createTeam({title: this.teamTitle})
      .subscribe(team => {
        this.teamCreated.emit(team);
      });
    this.changeModeEmitter.emit();
  }

  changeMode() {
    this.changeModeEmitter.emit();
  }
}

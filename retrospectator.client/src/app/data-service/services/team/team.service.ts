import {Injectable} from '@angular/core';
import {Team} from '../../model/Team';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';

@Injectable()
export class TeamService {

  private teams: Team[];
  private currentTeam: Team;

  constructor() {
  }

  getTeams(): Observable<Team[]> {
    return of(this.teams);
  }

  getTeam(id: number): Observable<Team> {
    return of(this.findById(id));
  }

  chooseTeam(id: number): Observable<Team> {
    this.currentTeam = this.findById(id);
    return of(this.currentTeam);
  }

  createTeam(team: Team): Observable<any> {
    return of(this.teams.push(team));
  }

  private findById(id): any {
    return this.teams.filter((team) => team.id === id)[0];
  }
}

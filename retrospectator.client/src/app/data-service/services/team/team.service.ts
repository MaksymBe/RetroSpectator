import {Injectable} from '@angular/core';
import {Team} from '../../model/Team';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Subject} from 'rxjs/Subject';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class TeamService {

  private urlModifier = 'team';
  private teamsArr: Team[] = null;
  public teams: Subject<Team[]> = new Subject<Team[]>();
  public currentTeam: BehaviorSubject<Team> = new BehaviorSubject<Team>(null);
  public currentTeamObj: Team = null;

  constructor(private http: HttpClient) {

  }

  getTeams(): Observable<any> {
    this.http.get(environment.apiHost + `${this.urlModifier}/my`).map(teams => <Team[]>teams).subscribe(teams => {
      this.teamsArr = teams;
      this.teams.next(teams);
      if (this.currentTeamObj == null) {
        const teamId = localStorage.getItem('teamKey');
        if (teamId !== null) {
          this.currentTeamObj = this.teamsArr.filter(team => team.identifier === teamId)[0];
          this.currentTeam.next(this.currentTeamObj);
        }
      }
    });
    return this.teams;
  }

  getTeam(id: string): Observable<Team> {
    this.http.get(environment.apiHost + `${this.urlModifier}/${id}`).map(teams => <Team>teams).subscribe(teams => {
      if (this.teamsArr.find(team => team.identifier === teams.identifier)) {
        this.teamsArr.push(teams);
        this.teams.next(this.teamsArr);
      }
      this.currentTeam.next(teams);
    });
    return this.currentTeam;
  }

  getCurrentTeam(): Observable<Team> {
    return this.currentTeam;
  }

  setCurrentTeam(team: Team) {
    this.currentTeam.next(team);
  }

  createTeam(team: any): Observable<any> {
    this.http.post(environment.apiHost + `${this.urlModifier}/`, team).map(teams => <Team>teams).subscribe(teams => {
      if (this.teamsArr.find(team => team.identifier === teams.identifier)) {
        this.teamsArr.push(teams);
        this.teams.next(this.teamsArr);
      }
      this.currentTeam.next(teams);
    });
    return this.currentTeam;
  }

  deleteTeam(id: string) {
    this.http.delete(environment.apiHost + `${this.urlModifier}/${id}`).map(teams => <Team>teams).subscribe(team => {
      this.teamsArr = this.teamsArr.filter(item => item.identifier !== team.identifier);
      this.teams.next(this.teamsArr);
    });
  }

  updateTeam(team: Team): Observable<any> {
    return this.http.patch(environment.apiHost + `${this.urlModifier}` + `/${team.identifier}`, team);
  }
}

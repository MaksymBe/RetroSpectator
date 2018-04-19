import {Injectable} from '@angular/core';
import {Team} from '../../model/Team';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';

@Injectable()
export class TeamService {


  constructor(private http: HttpClient) {

  }

  getTeams(): Observable<any> {
    return this.http.get(environment.apiHost + 'team/my');
  }

  getTeam(id: number): Observable<Team> {
    return <Observable<Team>>this.http.get(environment.apiHost + `team/${id}`);
  }

  createTeam(team: any): Observable<any> {
    return this.http.post(environment.apiHost + `team/`, team);
  }
}

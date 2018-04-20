import {Injectable} from '@angular/core';
import {Team} from '../../model/Team';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';

@Injectable()
export class TeamService {

  private urlModifier = 'team';

  constructor(private http: HttpClient) {

  }

  getTeams(): Observable<any> {
    return this.http.get(environment.apiHost + `${this.urlModifier}/my`);
  }

  getTeam(id: string): Observable<Team> {
    return <Observable<Team>>this.http.get(environment.apiHost + `${this.urlModifier}/${id}`);
  }

  createTeam(team: any): Observable<any> {
    return this.http.post(environment.apiHost + `${this.urlModifier}/`, team);
  }
}

import {Injectable} from '@angular/core';
import {of} from 'rxjs/observable/of';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../../../environments/environment';
import {Point} from '../../model/Point';
import {HttpClient} from '@angular/common/http';
import {Retro} from '../../model/Retro';

@Injectable()
export class RetroService {

  constructor(private http: HttpClient) {

  }

  getCurrentRetro(teamId: string): Observable<any> {
    return this.getRetro(`retro/${teamId}/current`);
  }

  getRetrosByTeam(teamId: string): Observable<any> {
    return this.getRetro(`retro/${teamId}/all`);
  }

  updateRetro(retro: Retro): Observable<Retro> {
    return <Observable<Retro>>this.http.patch(environment.apiHost + `retro/${retro.id}`, retro);
  }

  closeRetro(teamId: string, impression: string) {
    return this.http.patch(environment.apiHost + `retro/`, impression);
  }

  private getRetro(route: string) {
    return this.http.get(environment.apiHost + route);
  }
}

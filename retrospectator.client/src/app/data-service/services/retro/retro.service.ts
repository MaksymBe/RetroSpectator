import {Injectable} from '@angular/core';
import {of} from 'rxjs/observable/of';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../../../environments/environment';
import {Point} from '../../model/Point';
import {HttpClient} from '@angular/common/http';
import {Retro} from '../../model/Retro';

@Injectable()
export class RetroService {

  private urlModifier = 'retro';

  constructor(private http: HttpClient) {

  }

  getCurrentRetro(teamId: string): Observable<any> {
    return this.getRetro(`${this.urlModifier}/${teamId}/current`);
  }

  getRetrosByTeam(teamId: string): Observable<any> {
    return this.getRetro(`${this.urlModifier}/${teamId}/all`);
  }

  updateRetro(retro: Retro): Observable<Retro> {
    return <Observable<Retro>>this.http.patch(environment.apiHost + `${this.urlModifier}/${retro.id}`, retro);
  }

  closeRetro(teamId: string, impression: string) {
    return this.http.patch(environment.apiHost + `${this.urlModifier}/${teamId}/finish`, impression);
  }

  private getRetro(route: string) {
    return this.http.get(environment.apiHost + route);
  }
}

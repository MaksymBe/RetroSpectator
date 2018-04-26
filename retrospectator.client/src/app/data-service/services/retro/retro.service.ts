import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../../../environments/environment';
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
    return this.http.patch(environment.apiHost + `${this.urlModifier}/${teamId}/finish`, {impression});
  }

  private getRetro(route: string): Observable<any> {
    return this.http.get(environment.apiHost + route);
  }

  getRetroById(teamIdentifier: string, id: number): Observable<any> {
    return this.http.get(environment.apiHost + `retro/${teamIdentifier}/${id}`);
  }
}

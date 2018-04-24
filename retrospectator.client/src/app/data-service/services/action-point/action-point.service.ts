import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {APoint} from '../../model/APoint';
import {environment} from '../../../../environments/environment';
import {Retro} from '../../model/Retro';

@Injectable()
export class ActionPointService {

  private urlModifier = 'action-point';

  constructor(private http: HttpClient) {

  }

  getActionPointsByTeam(teamId: string): Observable<any> {
    return this.http.get(environment.apiHost + `${this.urlModifier}/${teamId}`);
  }

  createActionPoint(point: APoint, teamId: string): Observable<APoint> {
    return <Observable<APoint>>this.http.post(environment.apiHost + `${this.urlModifier}/${teamId}`, point);
  }

  updatePoint(point: APoint): Observable<APoint> {
    return <Observable<APoint>>this.http.patch(environment.apiHost + `${this.urlModifier}/${point.id}`, point);
  }

  deletePoint(id: number): Observable<any> {
    return this.http.delete(environment.apiHost + `${this.urlModifier}/${id}`);
  }

  getActionPointsByRetro(retro: Retro): Observable<any> {
    return this.http.get(environment.apiHost + `${this.urlModifier}/retro/${retro.id}`);
  }
}

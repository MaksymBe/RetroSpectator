import {Injectable} from '@angular/core';
import {Team} from '../../model/Team';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Point} from '../../model/Point';
import {environment} from '../../../../environments/environment';
import 'rxjs/add/operator/map';
import {APoint} from '../../model/APoint';
import {Retro} from '../../model/Retro';

@Injectable()
export class PointService {

  private urlModifier = 'point';

  constructor(private http: HttpClient) {

  }

  getMyPoints(teamId: string): Observable<any> {
    return this.getPoints(`${this.urlModifier}/${teamId}/my`);
  }

  getTeamPoints(teamId: string): Observable<any> {
    return this.getPoints(`${this.urlModifier}/${teamId}/all`);
  }

  createPoint(point: Point, teamId: string): Observable<Point> {
    return <Observable<Point>>this.http.post(environment.apiHost + `${this.urlModifier}/${teamId}`, point);
  }

  updatePoint(point: Point): Observable<Point> {
    return <Observable<Point>>this.http.patch(environment.apiHost + `${this.urlModifier}/${point.id}`, point);
  }

  deletePoint(id: number): Observable<any>  {
    return this.http.delete(environment.apiHost + `${this.urlModifier}/${id}`);
  }

  private getPoints(route: string): Observable<any> {
    return this.http.get(environment.apiHost + route)
      .map(points => splitPointsByType(points));
  }

  public getPointsByRetro(retro: Retro): Observable<any> {
    return this.http.get(environment.apiHost + `point/retro/${retro.id}`);
  }
}

function hasType(type) {
  return (point) => point.type === type;
}

function splitPointsByType(points) {
  return {
    plus: points.filter(hasType('plus')),
    minus: points.filter(hasType('minus'))
  };
}

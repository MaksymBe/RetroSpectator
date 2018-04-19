import {Injectable} from '@angular/core';
import {Team} from '../../model/Team';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Point} from '../../model/Point';
import {environment} from '../../../../environments/environment';
import 'rxjs/add/operator/map';

@Injectable()
export class PointService {

  constructor(private http: HttpClient) {

  }

  getMyPoints(teamId: string): Observable<any> {
    return this.getPoints(`/point/${teamId}/my`);
  }

  getTeamPoints(teamId: string): Observable<any> {
    return this.getPoints(`/point/${teamId}/all`);
  }

  createPoint(point: Point): Observable<Point> {
    return null;
  }

  updatePoint(team: Team): Observable<any> {
    return null;
  }

  private getPoints(route: string) {
    return this.http.get(environment.apiHost + route)
      .map(points => splitPointsByType(points));
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

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {APoint} from '../../model/APoint';

@Injectable()
export class ActionPointService {

  constructor(private http: HttpClient) {}

  getActionPointsByTeam(teamKey: number): Observable<APoint[]> {
    return <Observable<APoint[]>>this.http.get('http://localhost:3000/' + teamKey + 'action-points');
  }

  addActionPoint(teamKey: number, actionPoint: APoint): Observable<APoint> {
    return <Observable<APont>>this.http.post('http://localhost:3000/' + teamKey + 'action-points', actionPoint);
  }
}

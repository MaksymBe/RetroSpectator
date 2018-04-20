import {Injectable} from '@angular/core';
import {User} from '../../model/User';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';

@Injectable()
export class UserService {

  private urlModifier = 'user';

  constructor(private http: HttpClient) {
  }

  getUserInfo(): Observable<User> {
    return <Observable<User>>this.http.get(environment.apiHost + `${this.urlModifier}/me`);
  }

  getUsersByTeam(teamId: string): Observable<User[]> {
    return <Observable<User[]>>this.http.get(environment.apiHost + `${this.urlModifier}/${teamId}/all`);
  }

}

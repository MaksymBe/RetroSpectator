import {Injectable} from '@angular/core';
import {User} from '../../model/User';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  getUserInfo(): Observable<User> {
    return <Observable<User>>this.http.get(environment.apiHost + 'user/me');
  }

  getUsersByTeam(teamId: string): Observable<User[]> {
    return <Observable<User[]>>this.http.get(environment.apiHost + `user/${teamId}/all`);
  }

}

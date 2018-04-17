import {Injectable} from '@angular/core';
import {User} from '../../model/User';

@Injectable()
export class UserService {

  private currentUser: User;

  constructor() {
  }

  setUser(user: User): User {
    this.currentUser = user;
    return user;
  }
}

import {Component, OnInit} from '@angular/core';
import {Auth0Service} from '../data-service/services/auth/auth0.service';
import {UserService} from '../data-service/services/user/user.service';
import {User} from '../data-service/model/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header-bar',
  templateUrl: './header-bar.component.html',
  styleUrls: ['./header-bar.component.css']
})
export class HeaderBarComponent implements OnInit {

  public user: User;
  public authButtonHowered = false;

  constructor(public auth: Auth0Service, public userService: UserService, public router: Router) {
    this.userService.getUserInfo().subscribe(user => this.user = user);
  }

  ngOnInit() {
  }

}

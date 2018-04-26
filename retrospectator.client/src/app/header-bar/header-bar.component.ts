import {Component, OnInit} from '@angular/core';
import {Auth0Service} from '../data-service/services/auth/auth0.service';
import {UserService} from '../data-service/services/user/user.service';
import {User} from '../data-service/model/User';
import {Router} from '@angular/router';
import {Team} from '../data-service/model/Team';
import {TeamService} from '../data-service/services/team/team.service';

@Component({
  selector: 'app-header-bar',
  templateUrl: './header-bar.component.html',
  styleUrls: ['./header-bar.component.css']
})
export class HeaderBarComponent implements OnInit {

  public user: User;
  public authButtonHovered = false;
  public isCreatingMode = false;
  public isJoinMode = false;
  public teams = [];
  public authorized = false;
  private currentTeam: Team;

  constructor(public auth: Auth0Service,
              public userService: UserService,
              public router: Router,
              public teamService: TeamService) {
  }

  ngOnInit() {
    this.userService.getUserInfo().subscribe(user => this.user = user, error1 => {
      setTimeout(() => this.userService.getUserInfo().subscribe(user => this.user = user));
    });
    this.auth.isAuthenticated().subscribe(next => {
      this.authorized = next;
      if (next === false) {
        this.router.navigate([]);
      }
    });
    this.teamService.teams.subscribe(teams => this.teams = teams);
    this.teamService.getCurrentTeam().subscribe(team => this.currentTeam = team);
  }

  addTeam(newTeam: Team) {
    this.teamService.createTeam({title: newTeam.title})
      .subscribe(team => {
        if (team == null) {
          return;
        }
        this.isCreatingMode = false;
        this.router.navigate(['dashboard', team.identifier, 'my']);
      });
  }

  joinTeam(teamWithId: Team) {
    this.teamService.getTeam(teamWithId.title).subscribe((team) => {
      if (team == null) {
        return;
      }
      this.isJoinMode = false;
      this.router.navigate(['dashboard', team.identifier, 'my']);
    });
  }

  changeTeam(team: Team) {
    if (this.currentTeam === null || team.identifier !== this.currentTeam.identifier) {
      this.teamService.setCurrentTeam(team);
      this.router.navigate(['dashboard/', team.identifier, '/my']);
    }
  }

  deleteTeam(team: Team) {
    this.teamService.deleteTeam(team.identifier);
  }

}

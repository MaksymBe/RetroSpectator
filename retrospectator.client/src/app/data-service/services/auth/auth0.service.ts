import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import * as auth0 from 'auth0-js';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {environment} from '../../../../environments/environment';

@Injectable()
export class Auth0Service {

  auth0 = new auth0.WebAuth({
    clientID: environment.auth.clientID,
    domain: environment.auth.domain,
    responseType: 'token id_token',
    audience: environment.auth.audience,
    redirectUri: environment.auth.callbackURL,
    scope: 'openid profile'
  });

  userProfile: Observable<any> = null;
  accessToken: string = null;
  authenticated: Observable<boolean> = of(false);

  constructor(public router: Router) {
    this.accessToken = localStorage.getItem('access_token');
  }

  public login(): void {
    this.auth0.authorize();
  }

  public showProfileInfo(): void {
    if (!this.accessToken) {
      console.log('Access Token must exist to fetch profile');
    }
    if (!this.userProfile) {
      this.auth0.client.userInfo(this.accessToken, (err, profile) => {
        if (profile) {
          this.userProfile = profile;
          console.log(this.accessToken);
          console.log(this.userProfile);
        }
      });
    } else {
      console.log(this.accessToken);
      console.log(this.userProfile);
    }

  }


  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        this.setSession(authResult);
        this.router.navigate(['/']);
      } else if (err) {
        this.router.navigate(['/']);
        console.log(err);
      }
    });
  }

  private setSession(authResult): void {
    const expiresAt = JSON.stringify((authResult.expiresIn * 1000) + new Date().getTime());
    this.accessToken = authResult.accessToken;
    this.userProfile = authResult.userProfile;
    this.authenticated = of(true);
    localStorage.setItem('access_token', authResult.accessToken);
    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem('expires_at', expiresAt);
  }

  public logout(): void {
    this.accessToken = null;
    this.userProfile = null;
    this.authenticated = of(false);
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    this.router.navigate(['/']);
  }

  public isAuthenticated(): boolean {
    const expiresAt = JSON.parse(localStorage.getItem('expires_at'));
    return new Date().getTime() < expiresAt;
  }

}

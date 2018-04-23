export const environment = {
  production: true,
  auth: {
    clientID: '2d3p9j186oMI9xC1Rihg4fkb3PWHM5He',
    domain: 'internin6k.eu.auth0.com',
    callbackURL: 'http://localhost:4200/callback',
    audience: 'https://internin6k.eu.auth0.com/api/v2/'
  },
  getToken: getTokenF,
  apiHost: 'http://localhost:3000/'
};

export function getTokenF () {
  return localStorage.getItem('access_token');
}

// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  auth: {
    clientID: '2d3p9j186oMI9xC1Rihg4fkb3PWHM5He',
    domain: 'internin6k.eu.auth0.com',
    callbackURL: 'http://localhost:4200/callback',
    audience: 'http://localhost:3000'
  }
};

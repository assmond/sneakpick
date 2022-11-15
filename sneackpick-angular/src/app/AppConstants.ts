
  export let API_BASE_URL = 'http://localhost:8080/';
  let OAUTH2_URL = API_BASE_URL + 'oauth2/authorization/';
  let REDIRECT_URL = '?redirect_uri=http://localhost:4200/login';
  let API_URL = API_BASE_URL + 'api/';
  let AUTH_API = API_URL + 'auth/';
  //let GOOGLE_AUTH_URL = OAUTH2_URL + 'google' + REDIRECT_URL;
  let google_url =  'http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:4200/login'
  export default google_url

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Subject} from 'rxjs';
import baseUrl from './helper';
import {User} from "../models/user.model";

const httpOptions = {
  headers: new HttpHeaders({
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),

};

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  urlApi = baseUrl + '/api/v1/auth'
  userUrlApi= baseUrl+'/api/v1/users'
  public loginStatusSubjec = new Subject<boolean>();

  constructor(private http: HttpClient) {
  }

  public generateToken(loginData: any) {
    return this.http.post(`${this.urlApi}/signin`, loginData);
  }

  public getCurrentUser(token:any) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ` + token
      }),
    };
    return this.http.get<User>(`${this.userUrlApi}/load-user`, options);
  }


  public loginUser(token: any) {
    localStorage.setItem('accessToken', token);
    return true;
  }

  public isLoggedIn() {
    let tokenStr = localStorage.getItem('accessToken');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }

 public isAdmin() {
    if(this.getUserRole()===null){
      return false
    }else{
      return this.getUserRole() === 'ROLE_ADMIN'
    }

  }

  public logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('user');
    return true;
  }


  public getToken() {
    return localStorage.getItem('accessToken');
  }

  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

  public getUserRole() {
    if(this.getUser() != null){
      let user = this.getUser();
      return user.roles[0];
    }else{

      return null;
    }
  }
}

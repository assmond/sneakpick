import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import baseUrl from './helper';
import {Observable} from "rxjs";
import {User} from "../models/user.model";
const httpOptions = {
  headers: new HttpHeaders({
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),
};
@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrlApi= baseUrl+'/api/v1/users'
  autUrlApi= baseUrl+'/api/v1/auth'
  constructor(private httpClient: HttpClient) { }

  public registerUser(user:any){
    return this.httpClient.post(`${this.autUrlApi}/signup`,user,httpOptions);
  }

  public getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.userUrlApi}`,httpOptions);
  }

  public addUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.userUrlApi}`, user,httpOptions);
  }

  public updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.userUrlApi}`, user,httpOptions);
  }

  public deleteUser(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.userUrlApi}/${id}`,httpOptions);
  }
  public getUser(id: number): Observable<void> {
    return this.httpClient.get<void>(`${this.userUrlApi}/${id}`,httpOptions);
  }
}

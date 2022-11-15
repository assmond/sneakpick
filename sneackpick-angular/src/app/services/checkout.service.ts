import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import baseUrl from "./helper";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),
};

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  userUrlApi = baseUrl + '/api/v1/checkout'

  constructor(private httpClient: HttpClient) {
  }

  public checkOut(token: any) {
    let options = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ` + token
      }),
    };
    return this.httpClient.post(`${this.userUrlApi}/checkout`,null, options);
  }

}

import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import baseUrl from "./helper";
import {Observable} from "rxjs";
import {Order} from "../models/order.model";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),

};
@Injectable({
  providedIn: 'root'
})
export class OrderService {

  userUrlApi= baseUrl+'/api/v1/orders'

  constructor(private httpClient:HttpClient) {
  }
  public getOrders(): Observable<Order[]>{
    return this.httpClient.get<Order[]>(`${this.userUrlApi}`,httpOptions);
  }
  delete(id: any): Observable<any> {
    return this.httpClient.delete(`${this.userUrlApi}/${id}`,httpOptions);
  }

  get(id: any): Observable<Order> {
    return this.httpClient.get<Order>(`${this.userUrlApi}/${id}`,httpOptions);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import baseUrl from "./helper";
import {BehaviorSubject, Observable} from "rxjs";
import {ShoppingCart} from "../models/shopping-cart.model";
const httpOptions = {
  headers: new HttpHeaders({
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),

};
@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  urlApi = baseUrl + '/api/v1/shopping-cart'
  private itemsSubject = new BehaviorSubject<ShoppingCart[]>([]);
  items$ = this.itemsSubject.asObservable();

  constructor(private http: HttpClient) {

  }

  public getCart(): Observable<ShoppingCart>{
    return this.http.get<ShoppingCart>(`${this.urlApi}/cart`,httpOptions);
  }

  addToCart(data: any): Observable<any> {
    return this.http.post(`${this.urlApi}/add-item`, data,httpOptions);
  }

  removeFromCart(id: any): Observable<any> {
    return this.http.delete(`${this.urlApi}/remove-item/${id}`,httpOptions);
  }


}

import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import baseUrl from "./helper";
import { Observable } from 'rxjs';
import {Product} from "../models/product.model";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),

};
@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  urlApi = baseUrl + '/api/v1/products'
  constructor(private http: HttpClient) { }

  public getProducts(){
    return this.http.get(`${this.urlApi}`);
  }
  public getProductByUser():Observable<Product[]>{
    return this.http.get<Product[]>(`${this.urlApi}/my-products`,httpOptions);
  }
  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.urlApi}`);
  }

  get(id: any): Observable<Product> {
    return this.http.get<Product>(`${this.urlApi}/${id}`);
  }

  create(data: any): Observable<any> {
    const options = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ` + localStorage.getItem('accessToken')
      }),
    };
    return this.http.post(`${this.urlApi}`, data,options);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${this.urlApi}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${this.urlApi}/${id}`);
  }

  getImage(id: any): Observable<any> {
    return this.http.get(`${this.urlApi}/image/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(title: any): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.urlApi}?title=${title}`);
  }

  getFiles(id:any): Observable<any> {
    return this.http.get(`${this.urlApi}/products/${id}/files`);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import baseUrl from "./helper";
import {Observable} from "rxjs";
import {Brand} from "../models/brand.model";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),

};
@Injectable({
  providedIn: 'root'
})
export class BrandsService {
  urlApi = baseUrl + '/api/v1/brands'

  constructor(private http: HttpClient) { }

  public getBrands(){
    return this.http.get(`${this.urlApi}`,httpOptions);
  }
  getAll(): Observable<Brand[]> {
    return this.http.get<Brand[]>(`${this.urlApi}`,httpOptions);
  }

  get(id: any): Observable<Brand> {
    return this.http.get<Brand>(`${this.urlApi}/${id}`,httpOptions);
  }

}

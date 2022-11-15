import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import baseUrl from "./helper";
import {Observable} from "rxjs";
import {Size} from "../models/size.model";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),

};

@Injectable({
  providedIn: 'root'
})
export class SizesService {
  urlApi = baseUrl + '/api/v1/sizes'
  constructor(private http: HttpClient) {
  }

  public getSizes() {
    return this.http.get(`${this.urlApi}`, httpOptions);
  }

  getAll(): Observable<Size[]> {
    return this.http.get<Size[]>(`${this.urlApi}`, httpOptions);
  }

  get(id: any): Observable<Size> {
    return this.http.get<Size>(`${this.urlApi}/${id}`, httpOptions);
  }

}

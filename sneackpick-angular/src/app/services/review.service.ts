import {Injectable} from '@angular/core';
import baseUrl from "./helper";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Review} from "../models/review.model";

const httpOptions = {
  headers: new HttpHeaders({
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),
};

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  urlApi = baseUrl + '/api/v1/reviews'

  constructor(private httpClient: HttpClient) {
  }

  public getReview(id: number): Observable<Review> {
    return this.httpClient.get<Review>(`${this.urlApi}/${id}`);
  }

  public addReview(data: any): Observable<Review> {
    return this.httpClient.post<Review>(`${this.urlApi}`, data, httpOptions);
  }

}

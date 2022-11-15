import {Injectable} from '@angular/core';
import baseUrl from "./helper";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/comment.model";

const httpOptions = {
  headers: new HttpHeaders({
    'Authorization': `Bearer ` + localStorage.getItem('accessToken')
  }),
};

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  userUrlApi = baseUrl + '/api/v1/comments'

  constructor(private httpClient: HttpClient) {
  }

  public getComents(id: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(`${this.userUrlApi}/${id}`);
  }

  public addComment(data: any): Observable<Comment> {
    return this.httpClient.post<Comment>(`${this.userUrlApi}`, data, httpOptions);
  }

  deleteComment(id: any): Observable<any> {
    return this.httpClient.delete(`${this.userUrlApi}/${id}`);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Search} from "../model/search.model";
import {Movie} from "../model/movie.model";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http: HttpClient) { }

  public searchMovies(title: String, page: number): Observable<Search> {
    return this.http.get<Search>("/api/search/" + title, {
      params: new HttpParams().set('page', page)
    });
  }

  public getFavorites(): Observable<Movie[]> {
    return this.http.get<Movie[]>("/api/get");
  }

  public saveMovie(movie: Movie): Observable<any> {
    return this.http.post("/api/save", movie);
  }

  public deleteMovie(imdbId: string): Observable<any> {
    return this.http.delete("/api/delete/" + imdbId);
  }
}

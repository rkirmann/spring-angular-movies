import { Component, OnInit } from '@angular/core';
import {Movie} from "../../model/movie.model";
import {MovieService} from "../../service/movie.service";
import {UiMessageService} from "../../service/ui-message.service";

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  movies: Movie[] = []

  constructor(private movieService: MovieService, private uiMessageService: UiMessageService) { }

  ngOnInit(): void {
    this.getMovies();
  }

  getMovies() {
    this.movieService.getFavorites().subscribe({
      next: result => {this.movies = result},
      error: err => {this.uiMessageService.error("Error receiving favorites!")}
    })
  }

  delete(imdbID: string) {
    this.uiMessageService.clear();
    this.movieService.deleteMovie(imdbID).subscribe({
      next: value => {this.getMovies()},
      error: err => {this.uiMessageService.error("Error deleting movie")}
    })
  }
}

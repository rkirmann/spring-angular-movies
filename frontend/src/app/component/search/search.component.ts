import { Component, OnInit } from '@angular/core';
import {Movie} from "../../model/movie.model";
import {MovieService} from "../../service/movie.service";
import {UiMessageService} from "../../service/ui-message.service";
import {Search} from "../../model/search.model";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  result: Search | undefined;
  favorites: Movie[] = [];

  constructor(private movieService: MovieService, private uiMessageService: UiMessageService) { }

  ngOnInit(): void {
    this.setFavorites();
  }

  setFavorites() {
    this.movieService.getFavorites().subscribe({
      next: favorites => this.favorites = favorites
    })
  }

  search(title: string, page: number) {
    this.uiMessageService.clear();
    this.movieService.searchMovies(title, page).subscribe({
      next: search => {
        if (search.Response) {
          this.result = search;
        } else {
          this.uiMessageService.error(search.Error)
        }
      },
      error: err => {this.uiMessageService.error("Error. Please try again.")}
    })
  }

  save(movie: Movie) {
    this.movieService.saveMovie(movie).subscribe({
      next: value => {this.setFavorites()},
      error: err => this.uiMessageService.error("Error saving movie!")
    })
  }

  isInFavorites(movie: Movie): boolean {
    return !!this.favorites.find(i => i.imdbID === movie.imdbID);
  }
}

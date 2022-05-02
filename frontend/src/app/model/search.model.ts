import {Movie} from "./movie.model";

export interface Search {
  totalResults: number;
  page: number;
  Search: Movie[];
  Response: boolean;
  Error: string;
}

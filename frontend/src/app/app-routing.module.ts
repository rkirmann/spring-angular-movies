import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SearchComponent} from "./component/search/search.component";
import {FavoritesComponent} from "./component/favorites/favorites.component";

const routes: Routes = [
  {path: '', component: SearchComponent},
  {path: 'favorites', component: FavoritesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

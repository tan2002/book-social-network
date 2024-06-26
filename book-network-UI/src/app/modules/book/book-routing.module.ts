import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {BookListComponent} from "./pages/book-list/book-list.component";
import {MyBooksComponent} from "./pages/my-books/my-books.component";
import {ManageBooksComponent} from "./pages/manage-books/manage-books.component";
import {BorrowedBookListComponent} from "./pages/borrowed-book-list/borrowed-book-list.component";
import {ReturnedBookComponent} from "./pages/returned-book/returned-book.component";
import {authGuard} from "../../services/guard/auth.guard";

const routes: Routes = [
  {
    path:'books',
    component:MainComponent,
    canActivate:[authGuard],
    children:[
      {
        path:'',
        component:BookListComponent,
        canActivate:[authGuard]
      },
      {
        path:'my-books',
        component:MyBooksComponent,
        canActivate:[authGuard]
      },
      {
        path:'manage',
        component:ManageBooksComponent,
        canActivate:[authGuard]
      },
      {
        path:'manage/:bookId',
        component:ManageBooksComponent,
        canActivate:[authGuard]
      },
      {
        path:'my-borrowed-books',
        component:BorrowedBookListComponent,
        canActivate:[authGuard]
      },
      {
        path:'my-returned-books',
        component: ReturnedBookComponent,
        canActivate:[authGuard]
      }

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }

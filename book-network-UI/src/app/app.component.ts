import {Component, NgModule} from '@angular/core';
import {RouterModule, RouterOutlet} from '@angular/router';
import {CodeInputModule} from "angular-code-input";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {httpTokenInterceptor} from "./services/interceptor/http-token.interceptor";
import {CommonModule} from "@angular/common";
import {LoginComponent} from "./pages/login/login.component";
import {routes} from "./app.routes";



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    FormsModule,
    HttpClientModule,
    LoginComponent,
    CodeInputModule,
    RouterModule
  ],
  providers:[
    HttpClient,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useFactory:httpTokenInterceptor
    }
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'book-network-UI';
}

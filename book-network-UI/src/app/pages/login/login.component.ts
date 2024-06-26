import { Component } from '@angular/core';
import {AuthenticateRequest} from "../../services/models/authenticate-request";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {AuthenticationResponse} from "../../services/models/authentication-response";
import {TokenService} from "../../services/token/token.service";
import {NgForOf, NgIf} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";



@Component({
  selector: 'app-login',
  standalone: true,
  providers:[AuthenticationService],
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    HttpClientModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
    authRequest: AuthenticateRequest = {email:'',password:''};
    errorMsg:Array<string> = [];

    constructor(
      private router:Router,
      private authService: AuthenticationService,
      private tokenService: TokenService
    ) {

    }

  login() {
      this.errorMsg = [];
      this.authService.authenticate({
        body:this.authRequest
      }).subscribe({
        next:(res)=>{
          this.tokenService.token = res.token as string;
          this.router.navigate(['books']);
        },
        error:(err)=>{
          console.log(err);
          if(err.error.validationErrors){
            this.errorMsg = err.error.ValidationErrors();
          }else{
            this.errorMsg.push(err.error.errorMsg);
          }
        }
      })
  }

  register() {
    this.router.navigate(['register']);
  }
}

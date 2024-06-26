import { Component } from '@angular/core';
import {AuthenticateRequest} from "../../services/models/authenticate-request";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {AuthenticationResponse} from "../../services/models/authentication-response";
import {TokenService} from "../../services/token/token.service";



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
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
        next:(res:AuthenticationResponse)=>{
          this.tokenService.token = res.token as string;
          this.router.navigate(['books'])
        },
        error:(err)=>{
          console.log(err);
          if(err.error.formatValidationErrors){
            this.errorMsg = err.formatValidationErrors
          }else{
            this.errorMsg.push(err.error.error)
          }
        }
      })
  }

  register() {
    this.router.navigate(['register']);
  }
}

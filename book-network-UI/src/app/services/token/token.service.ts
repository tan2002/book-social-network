import { Injectable } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  set token(token:string){
    localStorage.setItem('token',token);
  }

  get token(){
    return localStorage.getItem('token') as string;
  }

  constructor() { }

  isTokenNotValid() {
    return !this.isTokenValid();
  }

  private isTokenValid() {
    const token:string = this.token;
    if(!token){
      return false;
    }
    // Decode the token
    const jwtHelper:JwtHelperService = new JwtHelperService();
    const isTokenExpired:boolean = jwtHelper.isTokenExpired(token);
    if(isTokenExpired){
      localStorage.clear();
      return false;
    }
    return true;
  }
}

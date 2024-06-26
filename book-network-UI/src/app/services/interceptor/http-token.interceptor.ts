import { HttpInterceptorFn } from '@angular/common/http';
import { TokenService } from "../token/token.service";
import { Observable } from 'rxjs';

export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenService = new TokenService(); // Instantiate TokenService
  const token = tokenService.token; // Get token from TokenService
  if (token) {
    // If token exists, add Authorization header
    const authReq = req.clone({
      setHeaders : {
        Authorization: 'Bearer ' + token
      }
    });
    return next(authReq); // Pass the modified request to the next interceptor or handler
  }
  return next(req); // If no token exists, pass the original request
};

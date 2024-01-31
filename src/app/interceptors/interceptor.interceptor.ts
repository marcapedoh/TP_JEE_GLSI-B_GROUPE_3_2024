import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';

import { LoaderServiceService } from '../component/loader/loader-service/loader-service.service';
import { Router } from '@angular/router';

@Injectable()
export class InterceptorInterceptor implements HttpInterceptor {

  PUBLIC_URL={
    login:`localhost:8080/EgaWebService/v1/auth/authenticate`,
    register:`localhost:8080/EgaWebService/v1/auth/register`
  }
  constructor(private loaderService:LoaderServiceService, private router:Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.loaderService.showloader();
    console.log('I intercept it');
    if(!(request.url.includes(this.PUBLIC_URL.login) || request.url.includes(this.PUBLIC_URL.register))){
      const authToken= JSON.parse(localStorage.getItem('token')!);
      console.log(`Bearer ${authToken}`);
      const authReq= request.clone({
        headers: request.headers.set('Authorization', `Bearer ${authToken}`)
      });
      this.handlerRequest(authReq, next)
    }
    return this.handlerRequest(request,next);
  }

  handlerRequest(req:HttpRequest<unknown>,next: HttpHandler):Observable<HttpEvent<unknown>>{
    return next.handle(req).pipe(tap((event:HttpEvent<unknown>)=>{
      if(event instanceof HttpResponse){
        this.loaderService.hideloader();
      }

    },(error:any)=>{
      this.loaderService.hideloader();

      if(error instanceof HttpErrorResponse && error.status===401 || error.status===403){
        this.router.navigate(['/login']);
      }
    }))
  }
}

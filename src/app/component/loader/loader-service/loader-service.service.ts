import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { LoaderState } from '../loader.model';

@Injectable({
  providedIn: 'root'
})
export class LoaderServiceService {
  private loaderSubject= new Subject<LoaderState>();

  loaderState= this.loaderSubject.asObservable();
  constructor() { }

  showloader(){
    this.loaderSubject.next({show:true});
  }
  hideloader(){
    this.loaderSubject.next({show:false});
  }
}

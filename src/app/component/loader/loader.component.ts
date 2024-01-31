import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoaderServiceService } from './loader-service/loader-service.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss']
})
export class LoaderComponent  implements OnInit,OnDestroy{
  show=false;
  subcription:Subscription | undefined;
  constructor(private loaderService:LoaderServiceService){

  }
  ngOnInit(): void {
      this.subcription=this.loaderService.loaderState.subscribe(state=>{
        this.show=state.show;
      });
  }

  ngOnDestroy():void{
    this.subcription?.unsubscribe();
  }

}

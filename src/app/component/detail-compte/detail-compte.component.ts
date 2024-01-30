import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-detail-compte',
  templateUrl: './detail-compte.component.html',
  styleUrls: ['./detail-compte.component.scss']
})
export class DetailCompteComponent  implements OnInit{
  constructor(private router:Router){ }

  @Input()
  detailCompte:any={};
  ngOnInit(): void {
      
  }

  @Output()
  suppressionEmitter= new EventEmitter;
  selectedIdArticleForDeleting?=-1;

  confirmSuppressionCompte():void{
    if(this.detailCompte.id){
      
    }
  }

}

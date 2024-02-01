import { Component, OnInit } from '@angular/core';
import { CompteControllerService, CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-retrait',
  templateUrl: './page-retrait.component.html',
  styleUrls: ['./page-retrait.component.scss']
})
export class PageRetraitComponent implements OnInit{

  errorMsg:Array<string>=[];
  compte:CompteDAO={}
  ibanForRetrait:string='';
  montant:string=''
  ngOnInit(): void {
      this.doRetrait()
  }

  constructor(private  compteService:CompteControllerService){}
 doRetrait():void{
    this.compteService.faireRetrait(+this.montant, this.ibanForRetrait).subscribe(result=>{
      this.errorMsg.push("retrait effectué");
    })
 }

}

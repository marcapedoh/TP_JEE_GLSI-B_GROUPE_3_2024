import { Component, OnInit } from '@angular/core';
import { throwError } from 'rxjs';
import { CompteControllerService, CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-depot',
  templateUrl: './page-depot.component.html',
  styleUrls: ['./page-depot.component.scss']
})
export class PageDepotComponent implements OnInit{

  compte:CompteDAO={}
  ibanAccountNumber:string='';
  message='';
  montantRechargement:number=0;
  errorMsg:Array<string>=[]
  ngOnInit(): void {
    this.faireDepot();
  }
  constructor(private compteService:CompteControllerService){}

  faireDepot():void{
    this.compteService.rechargerCompte(+this.montantRechargement, this.ibanAccountNumber).subscribe(res=>{
      this.message="Transaction Effectué";
      this.errorMsg.push(this.message);
    })
  }

}

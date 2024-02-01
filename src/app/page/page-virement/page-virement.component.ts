import { Component, OnInit } from '@angular/core';
import { CompteControllerService, CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-virement',
  templateUrl: './page-virement.component.html',
  styleUrls: ['./page-virement.component.scss']
})
export class PageVirementComponent implements OnInit {

  ibanForRetrait:string='';
  IbanForvirement:string='';
  montant:string='';
  errorMsg:Array<string>=[]
  compteDao:CompteDAO={}
  ngOnInit(): void {
      this.doVirement();
  }
  constructor(private compte:CompteControllerService){}


  doVirement():void{
    this.compte.faireVirement(this.ibanForRetrait, this.IbanForvirement, +this.montant).subscribe(result=>{
      this.errorMsg.push("virement effectué avec succès")
    })
  }


}

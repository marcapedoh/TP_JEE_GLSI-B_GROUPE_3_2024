import { Component, OnInit } from '@angular/core';
import { CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-virement',
  templateUrl: './page-virement.component.html',
  styleUrls: ['./page-virement.component.scss']
})
export class PageVirementComponent implements OnInit {


  compteDao:CompteDAO={}
  ngOnInit(): void {
      
  }
  constructor(){}



}

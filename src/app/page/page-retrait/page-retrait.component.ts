import { Component, OnInit } from '@angular/core';
import { CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-retrait',
  templateUrl: './page-retrait.component.html',
  styleUrls: ['./page-retrait.component.scss']
})
export class PageRetraitComponent implements OnInit{

  compte:CompteDAO={}
  ngOnInit(): void {
      
  }

}

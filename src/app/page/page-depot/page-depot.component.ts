import { Component, OnInit } from '@angular/core';
import { CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-depot',
  templateUrl: './page-depot.component.html',
  styleUrls: ['./page-depot.component.scss']
})
export class PageDepotComponent implements OnInit{

  compte:CompteDAO={}
  ngOnInit(): void {
  }
}

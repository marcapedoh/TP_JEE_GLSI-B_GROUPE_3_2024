import { Component, OnInit } from '@angular/core';
import { Menu } from './menu';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  public menuProprieties:Array<Menu>=[
    {
      id:'1',
      titre:'Operation',
      icon:'bx bx-task',
      url:'',
      role:'',
      sousMenu:[
        {
          id:'11',
          titre:'dépôt',
          icon:'bx bx-package',
          url:'',
        },
        {
          id:'12',
          titre:'retrait',
          icon:'bx bx-money-withdraw',
          url:'',
        },
        {
          id:'13',
          titre:'virement',
          icon:'bx bx-mail-send',
          url:'',
        },
        {
          id:'14',
          titre:'compte',
          icon:'bx bxs-user-account',
          url:'',
        }
      ]
    }
  ];

  private lastSelectedMenu:Menu| undefined;

  constructor(private router:Router){};

  ngOnInit(): void {
      
  }
}


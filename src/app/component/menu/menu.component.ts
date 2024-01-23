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
      role:'USER',
      sousMenu:[
        {
          id:'11',
          titre:'dépôt',
          icon:'bx bx-package',
          url:'depot',
        },
        {
          id:'12',
          titre:'retrait',
          icon:'bx bx-money-withdraw',
          url:'retrait',
        },
        {
          id:'13',
          titre:'virement',
          icon:'bx bx-mail-send',
          url:'virement',
        },
        {
          id:'14',
          titre:'compte',
          icon:'bx bxs-user-account',
          url:'compte',
        }
      ]
    },
    {
      id:'2',
      titre:'Transaction',
      icon:'bx bxs-credit-card',
      url:'',
      role:'USER',
      sousMenu:[
        {
          id:'21',
          titre:'consultation & tâches',
          icon:'bx bx-task',
          url:'consultationPage',
        }
      ]
    },
    {
      id:'3',
      titre:'Stats',
      icon:'bx bx-scatter-chart',
      url:'',
      role:'USER',
      sousMenu:[
        {
          id:'21',
          titre:'Etat statistiques',
          icon:'bx bx-bar-chart',
          url:'statistique',
        }
      ]
    }
  ];

  private lastSelectedMenu:Menu| undefined;

  constructor(private router:Router){};

  ngOnInit(): void {
      
  }
  navigate(menu:Menu):void{
    if(this.lastSelectedMenu){
      this.lastSelectedMenu.active=false;
    }
    menu.active=true;
    this.router.navigate([menu.url]);
    this.lastSelectedMenu=menu
  }
}


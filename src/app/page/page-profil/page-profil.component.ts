import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';
import { ClientControllerService, ClientDAO, CompteControllerService, CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-profil',
  templateUrl: './page-profil.component.html',
  styleUrls: ['./page-profil.component.scss']
})
export class PageProfilComponent implements OnInit {

  utilisateur:ClientDAO={}

  compte:CompteDAO={}

  ngOnInit(): void {
      this.findConnectedUser()
  }
  constructor(private router:Router, private userService:UserService, private compteService:CompteControllerService){}

  findConnectedUser():void{
    this.utilisateur=this.userService.getConnectedUser();
  }
  findAllCountAgainstToConnectedUser():void{

  }
}

import { HttpClient } from '@angular/common/http';
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

  listCompte:any;
  ngOnInit(): void {
      this.findConnectedUser()
      this.findAllCountAgainstToConnectedUser();
  }
  constructor(private router:Router, private userService:UserService, private compteService:CompteControllerService,private http:HttpClient){}

  findConnectedUser():void{
    this.utilisateur=this.userService.getConnectedUser();
  }
  findAllCountAgainstToConnectedUser():void{
    const client= this.userService.getConnectedUser();
    this.http.get('http://localhost:8080/EgaWebService/v1/comptes/findAllByClientId/'+client.id).subscribe(result=>{
        this.listCompte=result;
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';
import { ClientControllerService, ClientDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-profil',
  templateUrl: './page-profil.component.html',
  styleUrls: ['./page-profil.component.scss']
})
export class PageProfilComponent implements OnInit {

  utilisateur:ClientDAO={}

  ngOnInit(): void {
      
  }
  constructor(private router:Router, private userService:UserService){}
}

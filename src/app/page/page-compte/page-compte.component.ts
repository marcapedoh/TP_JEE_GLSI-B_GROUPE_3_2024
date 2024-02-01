import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { UserService } from 'src/app/services/user-service/user.service';
import { ClientControllerService, CompteControllerService, CompteDAO } from 'src/gs-api/src';

@Component({
  selector: 'app-page-compte',
  templateUrl: './page-compte.component.html',
  styleUrls: ['./page-compte.component.scss']
})
export class PageCompteComponent implements OnInit{

  compte:CompteDAO={}
  message:string='';
  errorMsg:Array<string>=[]
  ngOnInit(): void {
      this.enregistrerSonCompte();
  }

  constructor(private userService:UserService, private compteService:CompteControllerService,private clientService:ClientControllerService,private router:Router){}

  enregistrerSonCompte(){
    this.compte.client=this.userService.getConnectedUser();
    console.log(this.compte);
    this.compteService.save(this.compte).subscribe(res=>{
      this.message="compte créé avec succes";
      this.router.navigate(['/pageProfil'])

    },error=>{
      this.errorMsg=error.message;
      throwError(this.errorMsg);
    })
  }
}

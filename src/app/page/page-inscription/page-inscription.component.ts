import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { UserService } from 'src/app/services/user-service/user.service';
import { AuthenticationControllerService, AuthenticationRequest, ClientControllerService, ClientDAO, RegisterRequest } from 'src/gs-api/src';

@Component({
  selector: 'app-page-inscription',
  templateUrl: './page-inscription.component.html',
  styleUrls: ['./page-inscription.component.scss']
})
export class PageInscriptionComponent implements OnInit{
  ngOnInit(): void {
      
  }

  register:RegisterRequest={}
  utilisateurDAO:ClientDAO={}

  errorMsg:Array<string>=[];
  confirmationMotDePasse:string='';

  constructor(private utilisateurController:AuthenticationControllerService, private router:Router,private userService:UserService){}

  inscrire():void{
    if(this.confirmationMotDePasse !== this.register.motDePasse){
      this.errorMsg.push("les deux champs de mot de passe doivent être pareil");
      return ;
    }
    this.utilisateurController.register(this.register).subscribe(monUser=>{
      this.connectUser();
      this.router.navigate(['/login']);
    },error=>{
      this.errorMsg=error.message;
      throwError(this.errorMsg);
    });
  }

  connectUser():void{

    const authenticationRequest:AuthenticationRequest={
      username: this.utilisateurDAO.username,
      motDePasse: "djf"
    };
    this.userService.login(authenticationRequest).subscribe(response=>{
      this.userService.setAccessToken(response);
      this.getUserByUsername(authenticationRequest.username);
      this.router.navigate(['/profile']);
    });

  }

  getUserByUsername(username?: string):void{
    this.userService.getUserByUsername(username).subscribe(user=>{
      this.userService.setConnectedUser(user);
    });
  }
}

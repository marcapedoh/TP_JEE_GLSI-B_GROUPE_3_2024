import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';
import { AuthenticationRequest, AuthenticationResponse } from 'src/gs-api/src';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.scss']
})
export class PageLoginComponent implements OnInit{

  authRequest:AuthenticationRequest={

  }
  errorMsg:Array<string>=[];
  ngOnInit(): void {
      
  }
  constructor(private router:Router, private userService:UserService){}

  login(){
    const observable={
      next: (data: AuthenticationResponse) =>{
        this.userService.setAccessToken(data);
        this.userService.getUserByUsername(this.authRequest.username).subscribe(user=>{
          this.userService.setConnectedUser(user);
          this.router.navigate(['/depot']);
        })
      },
      error: (error:any)=>{
        this.errorMsg= error.message;
        console.log(this.errorMsg.toString())
      }
    };
    this.userService.login(this.authRequest).subscribe(observable);
  }

}

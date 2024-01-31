import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ClientControllerService, ClientDAO } from 'src/gs-api/src';
import { AutocompleteService } from 'src/app/services/autocomplete/autocomplete.service'
import { UserService } from 'src/app/services/user-service/user.service';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent  implements OnInit{

  client:ClientDAO={}
  articleNotYet=false;
  showSuggestions=false;
  searchControl= new FormControl();
  constructor(private userService:UserService, private autocomplete:AutocompleteService){}
  ngOnInit(): void {
      this.client.nom= this.userService.getConnectedUser().nom;
      this.client.prenom= this.userService.getConnectedUser().prenom
  }
  autoCompleteResult:any=[];

  setupSearch():void{
    this.searchControl.valueChanges
    .pipe(
      debounceTime(1),
      distinctUntilChanged(),
      switchMap(libelle=> this.autocomplete.AutoComplete(libelle))
    )
    .subscribe(results=>{
      this.autoCompleteResult= results;
    });

  }
  showSuggestionList(){
    this.showSuggestions=true;
  }

  hideSuggestionList(){
    this.showSuggestions=false;
  }
}

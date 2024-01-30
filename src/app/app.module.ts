import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageLoginComponent } from './page/page-login/page-login.component';
import { PageInscriptionComponent } from './page/page-inscription/page-inscription.component';
import { PageAceuilleComponent } from './page/page-aceuille/page-aceuille.component';
import { MenuComponent } from './component/menu/menu.component';
import { HeaderComponent } from './component/header/header.component';
import { LoaderComponent } from './component/loader/loader.component';
import { PageDepotComponent } from './page/page-depot/page-depot.component';
import { PageRetraitComponent } from './page/page-retrait/page-retrait.component';
import { PageVirementComponent } from './page/page-virement/page-virement.component';
import { PageCompteComponent } from './page/page-compte/page-compte.component';
import { ConsultationTacheComponent } from './page/consultation-tache/consultation-tache.component';
import { PageStatistiqueComponent } from './page/page-statistique/page-statistique.component';
import { PageProfilComponent } from './page/page-profil/page-profil.component';
import { DetailCompteComponent } from './component/detail-compte/detail-compte.component';

@NgModule({
  declarations: [
    AppComponent,
    PageLoginComponent,
    PageInscriptionComponent,
    PageAceuilleComponent,
    MenuComponent,
    HeaderComponent,
    LoaderComponent,
    PageDepotComponent,
    PageRetraitComponent,
    PageVirementComponent,
    PageCompteComponent,
    ConsultationTacheComponent,
    PageStatistiqueComponent,
    PageProfilComponent,
    DetailCompteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

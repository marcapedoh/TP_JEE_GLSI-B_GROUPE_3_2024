import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageLoginComponent } from './page/page-login/page-login.component';
import { PageInscriptionComponent } from './page/page-inscription/page-inscription.component';
import { PageAceuilleComponent } from './page/page-aceuille/page-aceuille.component';
import { PageDepotComponent } from './page/page-depot/page-depot.component';
import { PageRetraitComponent } from './page/page-retrait/page-retrait.component';
import { PageVirementComponent } from './page/page-virement/page-virement.component';
import { PageCompteComponent } from './page/page-compte/page-compte.component';
import { ConsultationTacheComponent } from './page/consultation-tache/consultation-tache.component';
import { PageStatistiqueComponent } from './page/page-statistique/page-statistique.component';
import { PageProfilComponent } from './page/page-profil/page-profil.component';


const routes: Routes = [

  {
    path:'login',
    component: PageLoginComponent
  },
  {
    path:'inscription',
    component: PageInscriptionComponent
  },
  {
    path:'',
    component:PageAceuilleComponent,
    children:[
      {
        path: 'depot',
        component: PageDepotComponent 
      },
      {
        path: 'retrait',
        component: PageRetraitComponent 
      },
      {
        path: 'virement',
        component: PageVirementComponent 
      },
      {
        path: 'compte',
        component: PageCompteComponent 
      },
      {
        path: 'consultationPage',
        component: ConsultationTacheComponent 
      },
      {
        path: 'statistique',
        component: PageStatistiqueComponent 
      },
      {
        path: 'pageProfil',
        component: PageProfilComponent 
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

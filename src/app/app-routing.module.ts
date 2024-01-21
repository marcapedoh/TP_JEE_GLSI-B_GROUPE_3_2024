import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageLoginComponent } from './page/page-login/page-login.component';
import { PageInscriptionComponent } from './page/page-inscription/page-inscription.component';
import { PageAceuilleComponent } from './page/page-aceuille/page-aceuille.component';
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
    path:'acceuil',
    component:PageAceuilleComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

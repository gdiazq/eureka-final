import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { ResultComponent } from './pages/result/result.component';
import { AboutComponent } from './pages/about/about.component';



@NgModule({
  declarations: [
    HomeComponent,
    RegisterComponent,
    ResultComponent,
    AboutComponent
  ],
  imports: [
    CommonModule,
  ]
})
export class PruebaModule { }

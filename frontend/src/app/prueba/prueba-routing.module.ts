import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { MainLayoutComponent } from "../shared/components/main-layout/main-layout.component";
import { HomeComponent } from "./pages/home/home.component";
import { RegisterComponent } from "./pages/register/register.component";
import { ResultComponent } from "./pages/result/result.component";
import { AboutComponent } from "./pages/about/about.component";

const routes: Routes = [
    {
        path: '',
        component: MainLayoutComponent,
        children: [
            {
                path: '',
                component: HomeComponent
            },
            {
                path: 'register',
                component: RegisterComponent
            },
            {
                path: 'result',
                component: ResultComponent
            },
            {
                path: 'about',
                component: AboutComponent
            }
        ]
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class PruebaRoutingModule { }
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { MainLayoutComponent } from "../shared/components/main-layout/main-layout.component";
import { HomeComponent } from "./pages/home/home.component";
import { RegisterComponent } from "./pages/register/register.component";
import { ResultComponent } from "./pages/result/result.component";
import { AboutComponent } from "./pages/about/about.component";
import { TableComponent } from "./pages/table/table.component";

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
                path: 'registro',
                component: RegisterComponent
            },
            {
                path: 'tabla',
                component: TableComponent
            },
            {
                path: 'resultado',
                component: ResultComponent
            },
            {
                path: 'acercade',
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
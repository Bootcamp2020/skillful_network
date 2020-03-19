/**
 * Module principal de l'application
 * Pour rappel, un module permet de représenter un lot de fonctionnalités. Il va référencer les composants de votre applications
 * il aura également pour responsabilité d'appeler le composant racine (bootstrap component) qui va être à l'origine de tous les composants
 * Ici, on parle de "AppComponent"
 */

import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from './shared/modules/material/material.module';
import { MatFormFieldModule} from '@angular/material/form-field';
import {FlexLayoutModule} from '@angular/flex-layout';
import {DashboardComponent} from './home/dashboard/dashboard.component';
import {UserComponent} from './home/user/user.component';
import {UsersListComponent} from './home/users-list/users-list.component';
import {TokenHttpInterceptorService} from './shared/interceptors/token-http-interceptor.service';
import { PasswordConfirmationComponent } from './password-confirmation/password-confirmation.component';
import { SimulationComponent } from './home/dashboard/simulation/simulation.component';
import { FooterComponent } from './home/dashboard/footer/footer.component';
import { ChatComponent } from './home/dashboard/chat/chat.component';
import { FilActualitesComponent } from './home/dashboard/fil-actualites/fil-actualites.component';
import { HeaderComponent } from './home/dashboard/header/header.component';

import { CandidatureComponent } from './home/dashboard/candidature/candidature.component';

import { ProfileComponent } from './home/dashboard/profile/profile.component';
import { MenuprofileComponent } from './menuprofile/menuprofile.component';
import {MatTooltipModule} from "@angular/material/tooltip";


@NgModule({
    declarations: [ // Chaque composant que vous créez doit être déclaré ici
        AppComponent,
        HomeComponent,
        LoginComponent,
        DashboardComponent,
        UserComponent,
        UsersListComponent,
        PasswordConfirmationComponent,
        SimulationComponent,
        FooterComponent,
        HeaderComponent,
        CandidatureComponent,
        ChatComponent,
        FooterComponent,
        FilActualitesComponent,
        HeaderComponent,
        ProfileComponent,
        MenuprofileComponent,

    ],
    imports: [
        BrowserModule,
        AppRoutingModule, // Toutes nos routes sont définies dans ce module
        BrowserAnimationsModule, // Nécessaire pour les animations Angular Material
        HttpClientModule, // Requit pour injecter la D.I. HttpClient qui nous permettra de requêter un serveur distant
        FormsModule, // Permet d'appliquer [(ngModel)] aux inputs
        ReactiveFormsModule, // Va nous permettre de créer des Model Driven Forms
        MaterialModule, // Ce module que nous avons créé contient l'ensemble des modules graphiques material à utiliser dans le projet
        FlexLayoutModule, // Permet de positionner à l'aide des fxFlex, fxLayout, fxLayoutAlign etc.

        MatFormFieldModule,
        MatTooltipModule,
    ],
    providers: [
        // Mise en place d'un intercepteur qui permettra d'appliquer le token automatiquement
        // à chaque requête sortante de notre application Angular
        // Le token correspond à notre "badge d'identité", il permet d'indiquer au serveur qui nous sommes
        // L'intercepteur va d'abord chercher à déterminer si le token est bien enregistré en localStorage ou non
        // Si oui, il l'envoie, sinon, il ne fait rien
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenHttpInterceptorService,
            multi: true
        },
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

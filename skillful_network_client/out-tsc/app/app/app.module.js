import { __decorate } from "tslib";
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './shared/modules/material/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { DashboardComponent } from './home/dashboard/dashboard.component';
import { UserComponent } from './home/user/user.component';
import { UsersListComponent } from './home/users-list/users-list.component';
import { TokenHttpInterceptorService } from './shared/interceptors/token-http-interceptor.service';
let AppModule = class AppModule {
};
AppModule = __decorate([
    NgModule({
        declarations: [
            AppComponent,
            HomeComponent,
            LoginComponent,
            DashboardComponent,
            UserComponent,
            UsersListComponent
        ],
        imports: [
            BrowserModule,
            AppRoutingModule,
            BrowserAnimationsModule,
            HttpClientModule,
            FormsModule,
            ReactiveFormsModule,
            MaterialModule,
            FlexLayoutModule,
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
], AppModule);
export { AppModule };
//# sourceMappingURL=app.module.js.map
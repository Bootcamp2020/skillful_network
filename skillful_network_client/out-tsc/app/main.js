/**
 * Ce fichier, c'est la "porte d'entrée" d'une application Angular
 * Inutile de le modifier.
 * Il permet d'indiquer sur quelle plateforme il va être executé (platformBrowserDynamic -> navigateur)
 * Puis il appelle simplement le module principal (donc AppModule)
 */
import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
if (environment.production) {
    enableProdMode();
}
platformBrowserDynamic().bootstrapModule(AppModule)
    .catch(err => console.error(err));
//# sourceMappingURL=main.js.map
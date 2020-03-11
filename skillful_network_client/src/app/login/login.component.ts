import {Component, OnInit} from '@angular/core';
import {ApiHelperService} from '../shared/services/api-helper.service';
import {UserService} from '../shared/services/user.service';
import {User} from '../shared/models/user';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    public username: string;
    public email: string;
    public error: boolean;

    // Variable de type Regex pour la validation d'un email (def email)
    private _emailRegex = '^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$';

    // Définitions des FormControl et FormGroup pour les 2 formulaires : Inscription et Login
    emailControlInscription: FormControl;
    emailControlLogin: FormControl;
    passwordControlLogin: FormControl;
    inscriptionFormGroup: FormGroup;
    loginFormGroup: FormGroup;

    constructor(private api: ApiHelperService, private userService: UserService, private router: Router) {
    }

    ngOnInit() {
        // Initialisation à vide des 2 formulaires
        this.buildFormInscription();
        this.buildFormLogin();
    }

    login() {
        // Permet de vider le local storage
        localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
        this.api.post({endpoint: '/login', data: this.username})
            .then((id: number) => {
                console.log(id);
                if (id === -1) {
                    this.error = true;
                } else {
                    localStorage.setItem('token', 'X'); // TODO Gérer le token
                    // this.userService.actualUser = new User({id});
                    this.router.navigate(['/home']);
                }
            });
    }

    register() {
        // Permet de vider le local storage
        localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
        this.api.post({endpoint: '/register', data: this.email})
            .then((id: number) => {
                console.log(id);
                if (id === -1) {
                    this.error = true;
                } else {
                    localStorage.setItem('token', 'X'); // TODO Gérer le token
                    // this.userService.actualUser = new User({id});
                    this.router.navigate(['/home']);
                }
            });
        /* Reset form. */
        this.buildFormInscription();
    }
    onSubmitLogin() {
        // Test affichage recuperation des donnees du formulaires entre
        console.log(this.loginFormGroup.value);

        /* Reset form. */
        this.buildFormLogin();
    }

    // Création du formulaire inscription avec un seul champ email
    buildFormInscription() {
        this.emailControlInscription = new FormControl(null, Validators.compose([Validators.pattern(this._emailRegex), Validators.required]));

        this.inscriptionFormGroup = new FormGroup({
            emailInscription: this.emailControlInscription
        });
    }

    // Création du formulaire Login avec un champ email et password
    buildFormLogin() {
        this.emailControlLogin = new FormControl(null, Validators.compose([Validators.pattern(this._emailRegex), Validators.required]));
        this.passwordControlLogin = new FormControl(null, Validators.compose([Validators.required, Validators.minLength(8)]));

        this.loginFormGroup = new FormGroup({
            emailLogin: this.emailControlLogin,
            password : this.passwordControlLogin
        });
    }
}

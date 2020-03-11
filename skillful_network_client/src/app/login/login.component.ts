import { Component, OnInit } from '@angular/core';
import { ApiHelperService } from '../shared/services/api-helper.service';
import { UserService } from '../shared/services/user.service';
import { User } from '../shared/models/user';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';
// Création d'une interface qui servira à une simulation simple d'un bdd
export interface TabEmail {
    email: string;
    password: string;
}
// Création d'un tableau d'objet qui nous servira de bdd dans un premier temps
const DATA_TAB: TabEmail[] = [
    { email: 'test@gmail.com', password: '123456' },
    { email: 'wofwof06@gmail.com', password: '234556' },
    { email: 'testtest@gmail.com', password: '345656' }
];
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    public username: string;
    public password: string;
    public email: string;
    public error: boolean;
    constructor(private api: ApiHelperService, private userService: UserService, private router: Router, private fb: FormBuilder) {
    }
    ngOnInit() {
        this.loginFormBuild();
    }
    login() {
        console.log(this.loginForm.value);
            if (this.loginForm.invalid) {
        return;
    }        
    //Verification que l'email corresponde au bon password
        let verifCode = false;
    // On parcourt notre "base²"
        DATA_TAB.forEach((email) => {
            if (email.password === this.loginForm.value.password && this.loginForm.value.email===email.email ) {
                verifCode = true;
            }
        });
    
        if (verifCode === false) {
            console.log('Code non trouvé dans la base');
            this.loginFormBuild();
        } else {
            console.log('Code conforme à celui de la base');
            this.loginFormBuild();
            localStorage.setItem('token', 'X'); // TODO Gérer le token
            this.router.navigate(['../home']);
        }
      //  Code initial du component 
                // Permet de vider le local storage
                // localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
                // this.api.post({endpoint: '/login', data: this.username})
                //     .then((id: number) => {
                //         console.log(id);
                //         if (id === -1) {
                //             this.error = true;
                //         } else {
                //             localStorage.setItem('token', 'X'); // TODO Gérer le token
                //             this.userService.actualUser = new User({id});//lien a modifie
                //             this.router.navigate(['/home']);
                //         }
                //     });
    }
//Méthode pour initialize le formulair
    loginFormBuild(){
        this.loginForm = this.fb.group({
            email: ['', [Validators.required, Validators.pattern('[a-z0-9.@]*')]],
            password: ['', [Validators.required, Validators.minLength(6)]]
          });
    }
}
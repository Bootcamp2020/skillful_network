import { Component, OnInit } from '@angular/core';
import { ApiHelperService } from '../shared/services/api-helper.service';
import { UserService } from '../shared/services/user.service';
import { User } from '../shared/models/user';
import { Router } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

// Création d'une interface qui servira à une simulation simple d'un bdd
export interface TabEmail {
  email: string;
  password: string;
  code: string;
}
// Création d'un tableau d'objet qui nous servira de bdd dans un premier temps
const DATA_TAB: TabEmail[] = [
  { email: 'test@gmail.com', password: '123456', code: '1234' },
  { email: 'wofwof06@gmail.com', password: '23455666', code: '1234567890' },
  { email: 'testtest@gmail.com', password: '345656', code: '3456' }
];

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  public username: string;
  public email: string;
  public error: boolean;
  public password: string;
  public position: number;

  // Variable de type Regex pour la validation d'un email (def email)
  private _emailRegex = '^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$';

  // Définitions des FormControl et FormGroup pour les 2 formulaires : Inscription et Login
  emailControlInscription: FormControl;
  emailControlLogin: FormControl;
  passwordControlLogin: FormControl;
  inscriptionFormGroup: FormGroup;
  loginFormGroup: FormGroup;

  // variable qui servira à afficher le formulaire approprié en fonction du context
  public doDisplayCodeVerif = false;
  // formGroup pour le code
  public codeForm: FormGroup;

  constructor(private api: ApiHelperService, private userService: UserService, private router: Router, private formBuilder: FormBuilder) {
  }
  ngOnInit() {
    // Initialisation à vide des 2 formulaires
    this.buildFormInscription();
    this.buildFormLogin();
    this.codeFormBuild();
    
  }
  login() {
    console.log(this.loginFormGroup.value);
    //if (this.loginFormGroup.invalid) {
    //  return;
    //}        
    //Verification que l'email corresponde au bon password
    let verifCode = false;
    // On parcourt notre "base²"
    DATA_TAB.forEach((email) => {
      if (email.password === this.loginFormGroup.value.password && this.loginFormGroup.value.emailLogin === email.email) {
        verifCode = true;
      }
    });

    if (verifCode === false) {
      console.log('Code non trouvé dans la base');
      this.buildFormLogin();
    } else {
      console.log('Code conforme à celui de la base');
      this.buildFormLogin();
      localStorage.setItem('token', 'X'); // TODO Gérer le token
      this.router.navigate(['../home']);
    }
    //  Code initial du component 
    // Permet de vider le local storage
    // localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
    // this.api.post({endpoint: '/login', data: this.email, this.password})
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

  register() {
    // Permet de vider le local storage
    localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
    console.log(this.inscriptionFormGroup.value.emailInscription);
    // variable qui va permettre la vérification de l'email
    let verifEmail = false;

    // Commenté en attendant la liaison avec le back
    this.api.post({ endpoint: '/register', data: { email: this.inscriptionFormGroup.value.emailInscription } })
      .then(() => {
        // SI on rentre là, ça veut dire que l'user a déjà un compte, faut le rediriger vers l'autre onglet
      }).catch((error) => {
        // Si on est là, ça veut dire que l'email n'existe pas en bdd, on doit donc afficher l'input du code
        this.doDisplayCodeVerif = true;
      })
    /* Reset form. */
    // this.buildFormInscription();
  }

  // Methode appelée lorsque l'on submit le formulaire du code temporaire
  codeValidation() {
    console.log(this.codeForm.value.code);
    console.log(this.inscriptionFormGroup.value.emailInscription);
    // variable qui va permettre la vérification du code
    let verifCode = false;

    // Commenté en attendant la liaison avec le back
    this.api.post({ endpoint: '/login', data: { code: this.codeForm.value.code, email: this.inscriptionFormGroup.value.emailInscription } })
      .then(() => {
        this.router.navigate(['./home']);
        // SI on rentre là, ça veut dire que l'user a déjà un compte, faut le rediriger vers l'autre onglet
      }).catch((error) => {
        // Si on est là, ça veut dire que l'email n'existe pas en bdd, on doit donc afficher l'input du code
        this.doDisplayCodeVerif = true;
      })
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
      password: this.passwordControlLogin
    });
  }
  // Création du formulaire code avec un champ code
  codeFormBuild() {
    this.codeForm = this.formBuilder.group({
      code: ['', [Validators.required, Validators.minLength(10)]],
    });
  }
// creation d'un nouveau code de confirmation
  newCode() {

  }
}

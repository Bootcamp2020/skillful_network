import { Component, OnInit } from '@angular/core';
import { ApiHelperService } from '../shared/services/api-helper.service';
import { UserService } from '../shared/services/user.service';
import { User } from '../shared/models/user';
import { Router } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-password-forgotten',
  templateUrl: './password-forgotten.component.html',
  styleUrls: ['./password-forgotten.component.scss']
})
export class PasswordForgottenComponent implements OnInit {
  
  loginForm: FormGroup;
  public username: string;
  public email: string;
  public error: boolean;
  public password: string;
 
  emailControl: FormControl;
  FormGroup: FormGroup;
  passwordControlLogin: FormControl;
  // formGroup pour le code
  public codeForm: FormGroup;

  // variable qui servira à afficher le formulaire approprié en fonction du context
  public doDisplayCodeVerif = false;

 // Variable de type Regex pour la validation d'un email (def email)
 private _emailRegex = '^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$';
 
 constructor(private api: ApiHelperService, private userService: UserService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    // Initialisation à vide des 2 formulaires
    this.buildForm();
    this.codeFormBuild();
  }
  register() {
    // Permet de vider le local storage
    localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
    console.log(this.FormGroup.value.email);
    // variable qui va permettre la vérification de l'email??ASMA
    let verifEmail = false;

    // Commenté en attendant la liaison avec le back
    this.api.post({ endpoint: '/passwordForgotten', data: { email: this.FormGroup.value.email } })
      .then(() => {
        // SI on rentre là, ça veut dire que l'user a déjà un compte, faut le rediriger vers l'autre onglet
      }).catch((error) => {
        // Si on est là, ça veut dire que l'email n'existe pas en bdd, on doit donc afficher l'input du code
        this.doDisplayCodeVerif = true;
      })
   
  }
  // Methode appelée lorsque l'on submit le formulaire du code temporaire
  codeValidation() {
    console.log(this.codeForm.value.code);
    console.log(this.FormGroup.value.email);
    // variable qui va permettre la vérification du code
    let verifCode = false;

    // Commenté en attendant la liaison avec le back
    this.api.post({ endpoint: '/login', data: { password: this.codeForm.value.code, email: this.FormGroup.value.email } })
      .then(() => {
        this.router.navigate(['./home']);
        // SI on rentre là, ça veut dire que l'user a déjà un compte, faut le rediriger vers l'autre onglet
      }).catch((error) => {
        // Si on est là, ça veut dire que l'email n'existe pas en bdd, on doit donc afficher l'input du code
        this.doDisplayCodeVerif = true;
      })
  }
   // Création du formulaire mot de passe oublié avec un seul champ email
   buildForm() {
    this.emailControl= new FormControl(null, Validators.compose([Validators.pattern(this._emailRegex), Validators.required]));

    this.FormGroup = new FormGroup({
      email: this.emailControl
    });
  }
  // Création du formulaire code avec un champ code
  codeFormBuild() {
    this.codeForm = this.formBuilder.group({
      code: ['', [Validators.required, Validators.minLength(10)]],
    });
  }
};

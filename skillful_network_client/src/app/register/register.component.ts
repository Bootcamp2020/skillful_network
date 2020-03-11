import { Component, OnInit } from '@angular/core';
import {ApiHelperService} from '../shared/services/api-helper.service';
import {UserService} from '../shared/services/user.service';
import {Router} from '@angular/router';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';

// Création d'une interface qui servira à une simulation simple d'une bdd
export interface TabEmail {
  email: string;
  code: string;
}
// Création d'un tableau d'objet qui nous servira de bdd dans un premier temps
const DATA_TAB: TabEmail[] = [
  {email: 'test@gmail.com', code: '1234'},
  {email: 'wofwof06@gmail.com', code : '2345'},
  {email: 'testtest@gmail.com', code: '3456'}
];
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {
  // variables que nous allons manipuler

  public error: boolean;
  // variable qui servira à afficher le formulaire approprié en fonction du context
  public doDisplayCodeVerif = false;
  // formGroup pour l'email
  public emailForm: FormGroup;
  // formGroup pour le code
  public codeForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private api: ApiHelperService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    // Initialisation des formulaires
   this.emailFormBuild();
   this.codeFormBuild();

  }

  // Methode appelée lorsque l'on submit le formulaire d'inscription
  register() {
    // Permet de vider le local storage
    localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
    console.log(this.emailForm.value.email);
    // variable qui va permettre la vérification de l'email
    let verifEmail = false;

    /* Commenté en attendant la liaison avec le back
    this.api.post({endpoint: '/register', data: this.emailForm.value.email})
        .then((id: number) => {
            console.log(id);
            if (id === -1) {
                this.error = true;
            } */

    // On parcourt notre "base"
    DATA_TAB.forEach((email) => {
      if (email.email === this.emailForm.value.email) {
        // S'il y un match on passe le boolean à true
        verifEmail = true;
        console.log('Code à rentrer : ' + email.code);
      }
    });
    if (!verifEmail) {
      console.log('Mail non trouvé dans la base');
    // TODO : Erreur à afficher
    } else {
        // Si la vérification est bonne on affiche le formulaire suivant
        console.log('Mail trouvé dans la base');
        this.doDisplayCodeVerif = true;
      }
  }

  // Methode appelée lorsque l'on submit le formulaire du code temporaire
  codeValidation() {
    console.log(this.codeForm.value.code);
    // variable qui va permettre la vérification du code
    let verifCode = false;
    // On parcourt notre "base"
    DATA_TAB.forEach((email) => {
      // Vérification que le code est bien dans la base et qu'il correpond bien à l'adresse mail de l'user
      if ((email.code === this.codeForm.value.code) && (this.emailForm.value.email === email.email)) {
         // S'il y un match on passe le boolean à true
        verifCode = true;
      }
    });
    // TODO : Faire vraie requete sur serveur pour vérifier que le code est le meme que dans la bdd
    if (!verifCode) {
      console.log('Code non trouvé dans la base');
    // TODO: Afficher erreur
    // On réinitialise notre formulaire
      this.codeFormBuild();
    } else {
        // Si la vérification est bonne on affiche le dashboard utilisateur
        console.log('Code conforme à celui de la base');
        localStorage.setItem('token', 'X'); // TODO Gérer le token
        this.router.navigate(['../home']);
        this.codeFormBuild();
      }
  }

  // Methodes pour initialiser les formulaires

  emailFormBuild() {
      this.emailForm = this.formBuilder.group({
        email: ['', [Validators.required, Validators.email]],
      });
  }
  
  codeFormBuild() {
      this.codeForm = this.formBuilder.group({
        code: ['', [Validators.required, Validators.maxLength(4)]],
      });
  }

}

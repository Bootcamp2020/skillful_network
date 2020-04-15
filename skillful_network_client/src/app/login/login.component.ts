import { Component, OnInit } from '@angular/core';
import { ApiHelperService } from '../shared/services/api-helper.service';
import { UserService } from '../shared/services/user.service';
import { AuthService } from '../shared/services/auth.service';
import { TokenStorageService } from '../shared/services/token-storage.service';
import { User } from '../shared/models/user';
import { Router } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogModule, MatDialog} from '@angular/material/dialog';
import {MyDialogComponent} from '../my-dialog/my-dialog.component';

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
  role: string[];
  isLoggedIn = 'false';
  isLoginFailed = false;
  public rememberMe: FormControl = new FormControl(false);
  isChecked: boolean;



  // tslint:disable-next-line: max-line-length
  private _emailRegex = '^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$';

  // Définitions des FormControl et FormGroup pour les 2 formulaires : Inscription et Login
  emailControlInscription: FormControl;
  emailControlLogin: FormControl;
  passwordControlLogin: FormControl;
  inscriptionFormGroup: FormGroup;
  loginFormGroup: FormGroup;
  codeForm: FormGroup;

  // variable qui servira à afficher le formulaire approprié en fonction du context
  public doDisplayCodeVerif = false;


  dialogResult = "";

  constructor(private api: ApiHelperService, private userService: UserService, private router: Router, private formBuilder: FormBuilder, 
              private authService: AuthService, private tokenStorage: TokenStorageService, public dialog:MatDialog) {
  }
  ngOnInit() {
    // Initialisation à vide des 2 formulaires
    this.buildFormInscription();
    this.buildFormLogin();
    this.codeFormBuild();

  }
  login() {
    // Permet de vider le local storage
    // tslint:disable-next-line: max-line-length
    localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
    this.authService.login({ emailLogin: this.loginFormGroup.value.emailLogin, password: this.loginFormGroup.value.password })
        .then((data) => {
            console.log('token' + data.accessToken);
            console.log('user email : ' + data.username);
            if (data.username == null) {
                this.error = true;
            } else if (this.isChecked) {
              this.tokenStorage.saveTokenAndCurrentUsername(data.accessToken, data.username, data.authorities , 'local');          
            } else {
              this.tokenStorage.saveTokenAndCurrentUsername(data.accessToken, data.username, data.authorities, ''  );
            }
            this.isLoggedIn = 'true';
            this.router.navigate(['/home']);
          })
        .catch((error) => {
          // Si on est là, ça veut dire que l'email n'existe pas en bdd, on doit donc afficher l'input du code
          this.isLoginFailed = true;
        });
    }

  openDialog(message:string) {
    let dialogRef = this.dialog.open(MyDialogComponent, {
      width: '700px',
      data: message
    });
    dialogRef.afterClosed().subscribe (result => {
      console.log('Fermeture fenêtre dialogue ');
      this.dialogResult = result ; 
    })
  }
  register() {
    // Permet de vider le local storage
    // tslint:disable-next-line:max-line-length
    localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
    console.log(this.inscriptionFormGroup.value.emailInscription);
    this.role = ['user'];

    // Commenté en attendant la liaison avec le back
    this.authService.register( {email: this.inscriptionFormGroup.value.emailInscription, role: this.role})
      .then(() => {
      // SI on rentre là, ça veut dire que l'user a déjà un compte, faut le rediriger vers l'autre onglet
      this.router.navigate(['/login']);
      console.log(this.inscriptionFormGroup.value.emailInscription +  ' existe déjà ! ');
    this.openDialog('L\' adresse email  ' + this.inscriptionFormGroup.value.emailInscription +'   que vous avez insérée existe déjà.');
      }).catch((error) => {
       
        // Si on est là, ça veut dire que l'email n'existe pas en bdd, on doit donc afficher l'input du code
        this.doDisplayCodeVerif = true;
      });
    /* Reset form. */
    // this.buildFormInscription();
    console.log(this.role);
  }

  // Methode appelée lorsque l'on submit le formulaire du code temporaire
  codeValidation() {
    console.log(this.codeForm.value.code);
    console.log(this.inscriptionFormGroup.value.emailInscription);

    localStorage.clear();
    // Commenté en attendant la liaison avec le back
    this.authService.login({ emailLogin: this.inscriptionFormGroup.value.emailInscription, password: this.codeForm.value.code  })
      .then((data) => {
        console.log('token' + data.accessToken);
        console.log('user email : ' + data.username);
        if (data.username === null) {
          this.error = true;
        } else {
            this.tokenStorage.saveTokenAndCurrentUsername(data.accessToken, JSON.stringify(data.username), data.authorities , 'local');
            //  this.userService.actualUser = new User({id});//lien a modifie
            this.isLoggedIn = 'true';
            this.router.navigate(['/home']);
          }
        // SI on rentre là, ça veut dire que l'user a déjà un compte, faut le rediriger vers l'autre onglet
      }).catch((error) => {
        // Si on est là, ça veut dire que l'email n'existe pas en bdd, on doit donc afficher l'input du code
        this.doDisplayCodeVerif = true;
      });
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
  onChange(event) {
    this.isChecked=event;
    // can't event.preventDefault();
    console.log('onChange event.checked '+event.checked);
  }
}

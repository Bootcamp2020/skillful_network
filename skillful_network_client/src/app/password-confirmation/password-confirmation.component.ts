import { Router } from '@angular/router';
import { UserService } from './../shared/services/user.service';
import { ApiHelperService } from './../shared/services/api-helper.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../shared/models/user';

@Component({
  selector: 'app-password-confirmation',
  templateUrl: './password-confirmation.component.html',
  styleUrls: ['./password-confirmation.component.scss']
})
export class PasswordConfirmationComponent implements OnInit {
  public formPost: FormGroup;
  // Définition du Regex pour l'input password
  private _passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
  public hidePassword = true;
  public hideConfirmPassword = true;
  public error: boolean;
  password: string;

  constructor(private fb: FormBuilder, private api: ApiHelperService, private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this._buildForm();
    this.password = null;
  }

  public onSubmit() {
    this.password = this.formPost.get('password').value;
    console.log( 'password récupéré à update : ' + this.password);
    this.userService.updateUserPassword(this.password)
    .then((data) => {
      console.log('Mot de passe de l\'utilisateur est maintenant : ' + data.password);
      console.log('Le mot de passe a bien été mis à jours');
      this.router.navigate(['/home']);
    })
    .catch((error: any) => {
      console.log('Une erreur s\'est produite : ' +  error);
    });
  }

  private _buildForm() {
    this.formPost = this.fb.group({
      password: ["", [Validators.pattern(this._passwordRegex), Validators.required]],
      confirmPassword: ["", [Validators.pattern(this._passwordRegex), Validators.required]]
    }, { validator: this.checkPasswords });

  }

  //  On vérifie que les deux mots de passe sont identiques
  checkPasswords(group: FormGroup) { // here we have the 'passwords' group
    let pass = group.get('password').value;
    let confirmPass = group.get('confirmPassword').value;
    if (confirmPass == "") {
      return null;
    }

    return pass === confirmPass ? null : { notSame: true }
  }
}

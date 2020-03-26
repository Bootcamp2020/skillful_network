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
  user: User;

  constructor(private fb: FormBuilder, private api: ApiHelperService, private UserService: UserService, private router: Router) {
    const random = Math.floor(Math.random() * (100));
    this.user = new User( random );
  }

  ngOnInit(): void {
    this._buildForm();
  }

  public onSubmit() {
    let id = this.user.id;
    let pass = this.formPost.get('password').value;
    console.log("id: " + id + " password: " + pass);
    // On vide le localStorage
    // localStorage.removeItem('token'); // test purpose : TODO Retirer
    // this.api.put({ endpoint: '/user/' + id, data: pass })
    //   .then((id: number) => {
    //     if (id === -1) {
    //       this.error = true;
    //     } else {
    //       localStorage.setItem('token', 'X'); // TODO Gérer le token
    //       // this.userService.actualUser = new User({id});
    //       this.router.navigate(['/home']);
    //     }
    //   });
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

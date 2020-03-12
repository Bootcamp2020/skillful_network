import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterpasswordeService } from '../shared/services/registerpassworde.service';

@Component({
  selector: 'app-password-confirmation',
  templateUrl: './password-confirmation.component.html',
  styleUrls: ['./password-confirmation.component.scss']
})
export class PasswordConfirmationComponent implements OnInit {
  public formPost: FormGroup;
  private _passwordRegex ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
  // hide = true;
  public hide1 = true;
  public hide2 = true;

  constructor(private fb: FormBuilder, private registerpassword:RegisterpasswordeService ) { }

  ngOnInit(): void {
    this._buildForm();
  }
  public onSubmit() {
    console.log("Radhwa is wonderfull");
    this.registerpassword.registerpassword(this.formPost.value); 
  }


  private _buildForm() {
    this.formPost = this.fb.group({
   
      password: ["", [Validators.pattern(this._passwordRegex), Validators.required]],

     
      confirmpassword: ["", [Validators.pattern(this._passwordRegex),Validators.required]]
    }, {validator: this.checkPasswords });

  }


  checkPasswords(group: FormGroup) { // here we have the 'passwords' group
  let pass = group.get('password').value;
  let confirmPass = group.get('confirmpassword').value;

  return pass === confirmPass ? null : { notSame: true }     
}
}

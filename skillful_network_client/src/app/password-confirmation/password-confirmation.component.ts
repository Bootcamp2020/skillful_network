import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-password-confirmation',
  templateUrl: './password-confirmation.component.html',
  styleUrls: ['./password-confirmation.component.scss']
})
export class PasswordConfirmationComponent implements OnInit {
  public formPost: FormGroup;
  private _passwordRegex ="[a-zA-Z0-9]*";
  hide = true;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this._buildForm();
  }
  public onSubmit() {
  }


  private _buildForm() {
    this.formPost = this.fb.group({
   
      password: ["", Validators.pattern(this._passwordRegex)],

     
      confirmpassword: ["", Validators.pattern(this._passwordRegex)]
    });

  }

}

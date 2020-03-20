import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-conf',
  templateUrl: './user-conf.component.html',
  styleUrls: ['./user-conf.component.scss']
})

export class UserConfComponent {

  
  formGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      'lastname': [null, Validators.required],
      'firstname': [null, Validators.required],
      'birthdate': [null, Validators.required],
      'email': [null, Validators.required],
      'phone': [null, Validators.required]
    });
  }
}
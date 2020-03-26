import { Component, OnInit, OnDestroy  } from '@angular/core';
import { stringToKeyValue } from '@angular/flex-layout/extended/typings/style/style-transforms';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from "../../shared/services/user.service"
import { User } from 'src/app/shared/models/user';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-profile-conf',
  templateUrl: './profile-conf.component.html',
  styleUrls: ['./profile-conf.component.scss']
})
export class ProfileConfComponent {

  userLogged: User;
  userLSubscription: Subscription;
  parentGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private userService: UserService) { }

  ngOnInit() {
    this.userLSubscription = this.userService.userLoggedSubject.subscribe(
      (userTemp: User) => {
        this.userLogged = userTemp;
      });
    this.userService.emitUsers();
    this.createForm();
  }

  createForm() {
    this.parentGroup = this.formBuilder.group({
      formUserInfos: this.formBuilder.group({
        'lastName': [this.userLogged.lastName, [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
        'firstName': [this.userLogged.firstName, [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
        'birthDate': [this.userLogged.birthDate, Validators.required],
        'email': [this.userLogged.email, [Validators.required, Validators.email]],
        'mobileNumber': [this.userLogged.mobileNumber, [Validators.required, Validators.minLength(10)]],
        'careergoal': [null, Validators.required]
      }),
      
      formSkillInfos: this.formBuilder.group({
        'skillSet': this.userLogged.skillSet,
        'skillUnit': ''
      }),

      formQualInfos: this.formBuilder.group({
        'qualificationSet': this.userLogged.qualificationSet,
        'qualifUnit': ''
      }),

      formSubscriptInfos: this.formBuilder.group({
        'subscriptionSet': this.userLogged.subscriptionSet,
        'subscriptUnit': ''
      })
    });
  }

  onUpdateForm() {
    // partie User
    const formValueU = this.parentGroup.get('formUserInfos').value;
    this.userLogged.firstName = formValueU['firstName'];
    this.userLogged.lastName = formValueU['lastName'];
    this.userLogged.birthDate = formValueU['birthDate'];
    this.userLogged.email = formValueU['email'];
    this.userLogged.mobileNumber = formValueU['mobileNumber'];
    
    // partie Skill
    const formValueS = this.parentGroup.get('formSkillInfos').value;
    this.userLogged.skillSet = formValueS['skillSet'];

    // partie Qualif
    const formValueQ = this.parentGroup.get('formQualInfos').value;
    this.userLogged.qualificationSet = formValueQ['qualificationSet'];

    // partie Skill
    const formValueSu = this.parentGroup.get('formSubscriptInfos').value;
    this.userLogged.subscriptionSet = formValueSu['subscriptionSet'];

    // Boom !
    this.userService.updateUser(this.userLogged);

  }

  onResetForm() {
    window.location.reload();
  }

  ngOnDestroy() {
    this.userLSubscription.unsubscribe();
  }

}

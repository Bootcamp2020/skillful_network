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
  monI: number;
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
        'careerGoal': [this.userLogged.careerGoal, [Validators.required, Validators.minLength(3)]]
      }),
      
      formSkillInfos: this.formBuilder.group({
        // 'skillSet': this.userLogged.skillSet,
        'skillSet': '',
        'skillUnit': [null, [Validators.minLength(2), Validators.maxLength(20)]]
      }),

      formQualInfos: this.formBuilder.group({
        'qualificationSet': this.userLogged.qualificationSet,
        'qualifUnit': [null, [Validators.minLength(2), Validators.maxLength(20)]]
      }),

      formSubscriptInfos: this.formBuilder.group({
        'subscriptionSet': this.userLogged.subscriptionSet,
        'subscriptUnit': [null, [Validators.minLength(2), Validators.maxLength(20)]]
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
    this.userLogged.careerGoal = formValueU['careerGoal'];
    
    // partie Skill /!\ cette partie du formulaire est vide si rien n'est touché
    const formValueS = this.parentGroup.get('formSkillInfos').value;
    this.monI = formValueS['skillSet'].length;
    if (this.monI > 0) { 
      this.userLogged.skillSet = formValueS['skillSet'];
    }
    
    // partie Qualif /!\ cette partie du formulaire est vide si rien n'est touché
    const formValueQ = this.parentGroup.get('formQualInfos').value;
    this.monI = formValueQ['qualificationSet'].length;
    if (this.monI > 0) { 
      this.userLogged.qualificationSet = formValueQ['qualificationSet'];
    }

    // partie Skill /!\ cette partie du formulaire est vide si rien n'est touché
    const formValueSu = this.parentGroup.get('formSubscriptInfos').value;
    this.monI = formValueSu['subscriptionSet'].length;
    if (this.monI > 0) { 
      this.userLogged.subscriptionSet = formValueSu['subscriptionSet'];
    }

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

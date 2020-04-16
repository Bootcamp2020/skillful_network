import { TokenStorageService } from 'src/app/shared/services/token-storage.service';
import { Component, OnInit, OnDestroy  } from '@angular/core';
import { stringToKeyValue } from '@angular/flex-layout/extended/typings/style/style-transforms';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from "../../shared/services/user.service"
import { User } from 'src/app/shared/models/user';
import { Subscription } from 'rxjs';
import {Candidature} from "../../shared/models/candidature";
import {ApiHelperService} from "../../shared/services/api-helper.service";
import {ActivatedRoute} from "@angular/router";

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
  public listCandidature: Candidature[];
  constructor(private formBuilder: FormBuilder, private userService: UserService,private api: ApiHelperService,private route: ActivatedRoute, private ts: TokenStorageService) { }

  ngOnInit() {
    this.api.get({endpoint: `/authentication/user`})
                  .then(data => {
                  console.log(data);
                  this.userLogged = data;
                  this.createForm();
                  this.api.get({endpoint: `/applications/jobs/user/` + data.id})
                  .then(candidatures => {
                    this.listCandidature = candidatures;
                  })
                  })
                  .catch((error) => {
                      console.log(error);
      });
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
        'chipValues': this.userLogged.skillSet,
        'chipValue': [null, [Validators.minLength(2), Validators.maxLength(20)]]
      }),

      formQualInfos: this.formBuilder.group({
        'chipValues': this.userLogged.qualificationSet,
        'chipValue': [null, [Validators.minLength(2), Validators.maxLength(20)]]
      }),

      formSubscriptInfos: this.formBuilder.group({
        'chipValues': this.userLogged.subscriptionSet,
        'chipValue': [null, [Validators.minLength(2), Validators.maxLength(20)]]
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

    // partie Subscr /!\ cette partie du formulaire est vide si rien n'est touché
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

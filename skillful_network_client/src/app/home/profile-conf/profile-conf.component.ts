import { Component} from '@angular/core';
import { stringToKeyValue } from '@angular/flex-layout/extended/typings/style/style-transforms';

@Component({
  selector: 'app-profile-conf',
  templateUrl: './profile-conf.component.html',
  styleUrls: ['./profile-conf.component.scss']
})
export class ProfileConfComponent {

  constructor() { }

  updateUser(){
    console.log('Mise à jour User');
  }

  reGetUser(){
    console.log('Remise à létat initial de User');
  }

}

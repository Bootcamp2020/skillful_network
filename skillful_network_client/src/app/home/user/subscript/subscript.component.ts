import { Component, OnInit, Input } from '@angular/core';
import { ChipValue } from 'src/app/shared/models/chip-value';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-subscript',
  templateUrl: './subscript.component.html',
  styleUrls: ['./subscript.component.scss']
})
export class SubscriptComponent implements OnInit {
<<<<<<< HEAD
  public listChip: ChipValue[];
=======
  @Input() public listSubscript: Subscript[];
>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14
  constructor( private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {

   /* const {id} = this.route.snapshot.params;
    console.log(id);
    // @ts-ignore
    this.api.get({endpoint: `/users/${id}/Subscription`})
        .then(data => this.listChip = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });*/
  }
}

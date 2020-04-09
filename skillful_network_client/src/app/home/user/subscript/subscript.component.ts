import { Component, OnInit, Input } from '@angular/core';
import { Subscript } from 'src/app/shared/models/subscript';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-subscript',
  templateUrl: './subscript.component.html',
  styleUrls: ['./subscript.component.scss']
})
export class SubscriptComponent implements OnInit {
  @Input() public listSubscript: Subscript[];
  constructor( private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {

   /* const {id} = this.route.snapshot.params;
    console.log(id);
    // @ts-ignore
    this.api.get({endpoint: `/users/${id}/Subscription`})
        .then(data => this.listSubscript = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });*/
  }
}

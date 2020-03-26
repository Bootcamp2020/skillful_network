import { Component, OnInit, Input } from '@angular/core';
import { MOCK_SUBSCRIPT, IPost } from 'src/app/shared/models/mock.subscript';
import { Subscript } from 'src/app/shared/models/subscript';
import {  FormBuilder, FormGroup } from '@angular/forms';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-subscript',
  templateUrl: './subscript.component.html',
  styleUrls: ['./subscript.component.scss']
})
export class SubscriptComponent implements OnInit {
  public listSubscript: Subscript[];
  @Input() public subscript: string;

  constructor( private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    /*this.listSubscript = [];
    MOCK_SUBSCRIPT.forEach((subscriptu: IPost) => {
      this.listSubscript.push(new Subscript(subscriptu));
    });*/
    const {id} = this.route.snapshot.params;
    console.log(id);
    // @ts-ignore
    this.api.get({endpoint: `/users/${id}/Subscription`})
        .then(data => this.listSubscript = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });
    console.log(this.listSubscript);
  }
}

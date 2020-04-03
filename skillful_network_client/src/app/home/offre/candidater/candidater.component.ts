import {Component, Input, OnInit} from '@angular/core';
import { Post } from '../offre';



@Component({
  selector: 'app-candidater',
  templateUrl: './candidater.component.html',
  styleUrls: ['./candidater.component.scss']
})
export class CandidaterComponent implements OnInit {
  @Input() public post : Post;
  agree: any;
  constructor() { }

  ngOnInit(): void {
  }

}

import {Component, Input, OnInit} from '@angular/core';
import {Post} from '../offre';

@Component({
  selector: 'app-exigences',
  templateUrl: './exigences.component.html',
  styleUrls: ['./exigences.component.scss']
})
export class ExigencesComponent implements OnInit {
  @Input() public post : Post;

  constructor() { }

  ngOnInit(): void {

  }
}

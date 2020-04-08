import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../shared/models/user';

@Component({
    selector: 'app-details-user',
    templateUrl: './details-user.component.html',
    styleUrls: ['./details-user.component.scss']
})
export class DetailsUserComponent implements OnInit {
    @Input()public user: User;
    @Input() public statusUser: string;
    @Input() public image_user: string;

    constructor() {

    }

    ngOnInit(): void {

    }
}

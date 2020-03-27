import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../../../shared/services/user.service';
import {User} from '../../../shared/models/user';
import {ActivatedRoute} from '@angular/router';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {formatDate} from '@angular/common';
import { DatePipe } from '@angular/common';


@Component({
    selector: 'app-details-user',
    templateUrl: './details-user.component.html',
    styleUrls: ['./details-user.component.scss']
})
export class DetailsUserComponent implements OnInit {
    public user: User;
    public statusUser: string;
    public loading:boolean;
    @Input() public urlImage: string;
    // @ts-ignore
    constructor(private api: ApiHelperService, private route: ActivatedRoute) {

    }

    ngOnInit(): void {
        // this.user = this.userService.findById(this.route.snapshot.params.id - 1);
        const {id} = this.route.snapshot.params;
        console.log(id);
        this.loading = true;
        // @ts-ignore
        this.api.get({endpoint: `/usersbyId/${id}`})
            .then(data => {
                this.user = data;
                this.loading = false;
                if (this.user.status === '1') {
                this.statusUser = 'Chercheur d\'emploi';
                console.log(this.user.status);
                console.log(this.statusUser);
              } else {
                this.statusUser = 'Etudiant';
              }
                // tslint:disable-next-line:max-line-length
                this.urlImage = 'https://previews.123rf.com/images/alexutemov/alexutemov1702/alexutemov170200423/71260683-gar%C3%A7on-portrait-visage-ic%C3%B4ne-web-avatar-style-plat-m%C3%A2le-vecteur-bloqu%C3%A9-ou-inconnu-anonyme-silhouette-illustra.jpg';
            })
            .catch((error) => {
                console.log('cet utilisateur n\'existe pas');
            });
        // @ts-ignore
    }
}

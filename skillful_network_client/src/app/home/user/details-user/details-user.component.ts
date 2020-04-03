import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../shared/models/user';
import {ActivatedRoute} from '@angular/router';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
@Component({
    selector: 'app-details-user',
    templateUrl: './details-user.component.html',
    styleUrls: ['./details-user.component.scss']
})
export class DetailsUserComponent implements OnInit {

    public user: User;
    public statusUser: string;
    public loading: boolean;
     public image_user:any[];
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
                console.log(this.user.photo);
                if (this.user.status === '1') {
                this.statusUser = 'Chercheur d\'emploi';
                console.log(this.user.status);
                console.log(this.statusUser);
              } else {
                this.statusUser = 'Etudiant';
              }
            })
            .catch((error) => {
                console.log('cet utilisateur n\'existe pas');
            });

        this.api.get({endpoint: `/users/image/${id}`})
            .then(data => {this.image_user = data;
                console.log(typeof data);
                })
            .catch((error) => {
                console.log('cet utilisateur n\'existe pas');
            });
    }
}

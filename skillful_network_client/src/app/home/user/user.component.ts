import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../shared/models/user';
import {UserService} from '../../shared/services/user.service';
import {ApiHelperService} from '../../shared/services/api-helper.service';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';

import {Qualif} from '../../shared/models/qualif';
import {Skill} from '../../shared/models/skill';
import {Subscript} from '../../shared/models/subscript';
import {DomSanitizer} from '@angular/platform-browser';
import {Candidature} from "../../shared/models/candidature";

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

    public user: User;
    public listQualif: Qualif[];
    public listSkill: Skill[];
    public listSubscript: Subscript[];
    public listCandidature: Candidature[];
    public statusUser: string;
    public loading: boolean;
    public image_user: any;

    constructor(private api: ApiHelperService, private userService: UserService, private route: ActivatedRoute, private http: HttpClient, private sanitizer: DomSanitizer) {
    }

    ngOnInit() {
        // this.user = this.userService.findById(this.route.snapshot.params.id - 1);
        const {id} = this.route.snapshot.params;
        console.log(id);
        this.loading = true;
        const listPromises = [];

        listPromises.push(this.api.get({endpoint: `/usersbyId/${id}`}));

        listPromises.push(this.api.get({endpoint: `/users/${id}/Qualifications`}));

        listPromises.push(this.api.get({endpoint: `/users/${id}/skills`}));

        listPromises.push(this.api.get({endpoint: `/users/${id}/Subscription`}));

        listPromises.push(this.api.get({endpoint: `/applications/jobs/user/${id}`}));

        Promise.all(listPromises)
            .then(([userData, listQualif, listSkill, listSubscript, listCandidature]) => {
                this.user = new User(userData);

                if (this.user.status === '1') {
                    this.statusUser = 'Chercheur d\'emploi';
                    console.log(this.user.status);
                    console.log(this.statusUser);
                } else {
                    this.statusUser = 'Etudiant';
                }
                console.log(this.user.photo);
                if (this.user.photo) {
                    this.http.get(environment.base_url + `/image/${id}`, {responseType: 'blob'})
                        .subscribe(dataBlob => {
                            const objectURL = URL.createObjectURL(dataBlob);
                            this.image_user = this.sanitizer.bypassSecurityTrustUrl(objectURL);
                        });
                } else {
                    this.image_user = '../../../../assets/pictures/profile_defaut.jpg';
                }

                this.listQualif = listQualif;
                this.listSkill = listSkill;
                this.listSubscript = listSubscript;
                this.listCandidature = listCandidature;
                console.log(this.listCandidature);
                this.loading = false;
            })
            .catch((error) => {
                console.log('Une erreur est survenue !');
            });
    }
}

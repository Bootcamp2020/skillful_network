import {DashboardComponent} from './dashboard/dashboard.component';
import {UsersListComponent} from './users-list/users-list.component';
import {UserComponent} from './user/user.component';
import {ProfileConfComponent} from './profile-conf/profile-conf.component';
import {OffreComponent} from './offre/offre.component';
import { FormationListComponent } from './formation-list/formation-list.component';
import {JobOfferListComponent} from './job-offer-list/job-offer-list.component';
import {SimulationContainerComponent} from './simulation-container/simulation-container.component';

export const HOME_ROUTES = [
  {path: '', component: DashboardComponent},
  {path: 'profile', component: ProfileConfComponent},
  {path: 'users-list', component: UsersListComponent},
  {path: 'user/:id', component: UserComponent},
  {path: 'offre/:id', component: OffreComponent},
  {path: 'formation-list', component: FormationListComponent},
  {path: 'job-offer-list', component: JobOfferListComponent},
  {path: 'simulation/:simId', component: SimulationContainerComponent},
];

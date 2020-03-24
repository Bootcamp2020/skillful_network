import {DashboardComponent} from './dashboard/dashboard.component';
import {UsersListComponent} from './users-list/users-list.component';
import {UserComponent} from './user/user.component';
import {ProfileConfComponent} from './profile-conf/profile-conf.component';
import { UserProfilComponent } from './user-profil/user-profil.component';

export const HOME_ROUTES = [
  {path: '', component: DashboardComponent},
  {path: 'profile', component: ProfileConfComponent},
  {path: 'users-list', component: UsersListComponent},
  {path: 'user/:id', component: UserComponent},
  {path: 'users/:id', component: UserProfilComponent},
];

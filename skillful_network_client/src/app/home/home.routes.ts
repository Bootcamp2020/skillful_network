import {DashboardComponent} from './dashboard/dashboard.component';
import {UsersListComponent} from './users-list/users-list.component';
import {UserComponent} from './user/user.component';

export const HOME_ROUTES = [
  {path: '', component: DashboardComponent},
  {path: 'users-list', component: UsersListComponent},
  {path: 'user/:id', component: UserComponent},
];

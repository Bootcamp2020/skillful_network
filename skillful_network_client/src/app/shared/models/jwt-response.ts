import { User } from './user';

export class JwtResponse {
    user: User;
    authorities: string[];
    tokenType: string;
    accessToken: string;
}

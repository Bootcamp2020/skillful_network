import { User } from './user';

export class JwtResponse {
    username: String;
    authorities: string[];
    tokenType: string;
    accessToken: string;
}

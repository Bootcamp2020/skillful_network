import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../../../shared/services/token-storage.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {

  }
  onSearch() {
    console.log("works");
  }

  logout() {
    this.tokenStorage.signOut();
  }


}

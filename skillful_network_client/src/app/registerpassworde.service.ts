import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RegisterpasswordeService {

  constructor(private http: HttpClient, private router: Router) { }



  registerpassword(formdata){
    this.http
      .post("http://localhost:5000/registerpassword", formdata) 
      .toPromise() 
      .then(data => {
        console.log(data);
        console.log(data['success']); 
        if(data['success']){
          console.log(data); 
          this.router.navigate(['/home']);
        } 
      })
      .finally(() => {
      
      });
  }
}

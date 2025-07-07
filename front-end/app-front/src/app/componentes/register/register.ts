import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';


@Component({
  selector: 'app-register',
  standalone:true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls:['./register.css']
})
export class Register {
  username='';
  password='';
  confirmPassword='';
  error='';
  success='';

  constructor(private http: HttpClient, private router: Router) {}

  register() {
    if(this.password != this.confirmPassword){
      this.error='Las contraseñas no coinciden';
      this.success='';
      return;
    }

    const body={username:this.username.trim(), password:this.password};
    console.log('Registrando usuario:', this.username);
    this.http.post(`${environment.apiUrl}/auth/register`, body, {
      observe: 'response',
    responseType: 'text'})
      .subscribe({
        next: (res) => {
          console.log('STATUS:', res.status);
          console.log('BODY:', res.body);

          if (res.status === 200) {
            this.success = res.body as string;
            this.error = '';
            setTimeout(() => this.router.navigate(['/login']), 1500);
          }
        },
        error: (err) => {
          console.error('ERROR:', err);
          if (err.status === 409) {
            this.error = err.error;
          } else {
            this.error = 'Error en el registro. Intenta más tarde.';
          }
          this.success = '';

      }
    });
  }

}

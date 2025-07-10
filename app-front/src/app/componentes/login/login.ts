import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../auth/AuthService';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone:true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  username='';
  password='';
  error='';

  constructor(private auth:AuthService, private router: Router ) {}

  login() {
    this.auth.login({username: this.username, password: this.password}).subscribe({
      next: res => {
        this.auth.saveToken(res.token);
        this.router.navigate(['/products']);
      },
      error: () => this.error = 'Usuario o contraseña incorrecto'
    });
  }
}

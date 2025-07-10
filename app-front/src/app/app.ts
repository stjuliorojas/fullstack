import { Component } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import {AuthService} from './auth/AuthService';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [CommonModule,RouterOutlet, RouterModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected title = 'app-front';
  constructor(public auth:AuthService){}
  logout(){
    this.auth.logout();
  }
}

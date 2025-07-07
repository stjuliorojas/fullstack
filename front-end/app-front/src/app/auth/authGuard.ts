import {CanActivateFn} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from './AuthService';

export const authGuard:CanActivateFn=()=>{
  const auth=inject(AuthService);
  return auth.isAuthenticated();
}

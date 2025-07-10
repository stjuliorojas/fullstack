import { Routes } from '@angular/router';
import {Login} from './componentes/login/login';
import {Products} from './componentes/products/products';
import {Admin} from './componentes/admin/admin';
import {Register} from './componentes/register/register';
import {authGuard} from './auth/authGuard';
import {adminGuard} from './auth/adminGuard';
import {ProductForm} from './componentes/product-form/product-form';

export const routes: Routes = [
  {path:'', redirectTo:'/login', pathMatch:'full'},
  {path: 'login', component: Login},
  {path: 'register', component: Register},
  {path: 'admin', component: Admin, canActivate:[adminGuard]},
  {path: 'products', component: Products, canActivate:[authGuard]},
  {path:'admin/create', component: ProductForm, canActivate:[adminGuard]},
  {path: '**', redirectTo: 'login'}

];

import { Component, OnInit } from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {AuthService} from '../../auth/AuthService';
import {Router, RouterModule} from '@angular/router';

@Component({
  selector: 'app-products',
  standalone:true,
  imports: [CommonModule, RouterModule],
  templateUrl: './products.html',
  styleUrls: ['./products.css']
})
export class Products implements OnInit {
  products: any[]=[];
  constructor(private http: HttpClient, public auth:AuthService, private router: Router) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.http.get<any[]>(`${environment.apiUrl}/api/products`).subscribe(data => {
      this.products = data;
    });
  }

  deleteProduct(id: number) {
    if (confirm('¿Estás seguro de eliminar este producto?')) {
      this.http.delete(`${environment.apiUrl}/api/products/${id}`).subscribe(() => {
        this.loadProducts();
      });
    }
  }

  editProduct(product: any) {
    this.router.navigate(['/admin/create'], { state: { product } });
  }

}

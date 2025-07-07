import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';
import {Producto} from '../../model/Producto';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-form.html',
  styleUrls: ['./product-form.css']
})
export class ProductForm {
  product: Producto = {
    name: '',
    price: 0
  };
  constructor(private http: HttpClient, private router: Router) {
    const state = history.state;
    if (state && state.product) {
      this.product = { ...state.product }; // modo edición
    }
  }

  save(){
    if (this.product.id) {
      // Modo edición
      this.http.put(`${environment.apiUrl}/api/products/${this.product.id}`, this.product)
        .subscribe(() => this.router.navigate(['/products']));
    } else {
      // Modo creación
      this.http.post(`${environment.apiUrl}/api/products`, this.product)
        .subscribe(() => this.router.navigate(['/products']));
    }
  }
  delete() {
    if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
      this.http.delete(`${environment.apiUrl}/api/products/${this.product.id}`).subscribe(() => {
        this.router.navigate(['/products']);
      });
    }
  }
}

import { Component, OnInit } from '@angular/core';
import {Product} from "../../../models/product.model";
import {ProductsService} from "../../../services/products.service";
import { Router} from "@angular/router";


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products?: Product[];

  constructor(private productService: ProductsService,private router: Router) {
  }

  ngOnInit(): void {
    this.loadProducts()
  }

  loadProducts() {
    this.productService.getProducts().subscribe(
      (data: any) => {
        this.products = data;
      }, error => {
        console.log("error " + error);
      })
  }
  productDetails(id: number){
    this.router.navigate(['product-details', id]);
  }
  AddProduct(){
    this.router.navigate(['create-product']);
  }
  updateProduct(id: number){
    this.router.navigate(['update-product', id]);
  }

  deleteProduct(id: number){
    this.productService.delete(id).subscribe( data => {
      this.loadProducts();
    },error => {
      console.log(error)
    })
  }
}

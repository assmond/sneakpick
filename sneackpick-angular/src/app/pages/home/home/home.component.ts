import {Component, OnInit} from '@angular/core';
import {ProductsService} from "../../../services/products.service";
import {Product} from "../../../models/product.model";
import {Router} from "@angular/router";
import {DomSanitizer} from "@angular/platform-browser";
import baseUrl from "../../../services/helper"
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  products: Product[];
  baseUrl=baseUrl;
  searchText:string

  constructor(private productService: ProductsService,private router:Router,private _sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.loadProducts()
  }
  showImages(id:any){
    console.log("show image"+ id);
    this.productService.getImage(id).subscribe((data)=>{
      const reader = new FileReader();
      reader.readAsDataURL(new Blob([data]));
      console.log(reader)
    })
  }
  /*getImage(id:any){
    this.productService.getImage(id).subscribe((data)=>{
      this.image=data;
    })
  }*/
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

}

import { Component, OnInit } from '@angular/core';
import {Product} from "../../../models/product.model";
import {ProductsService} from "../../../services/products.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id?: number;
  product: Product = new Product();
  constructor(private productsService: ProductsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.productsService.get(this.id).subscribe(data => {
      this.product = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.productsService.update(this.id, this.product).subscribe( data =>{
        this.goToEmployeeList();
      }
      , error => console.log(error));
  }

  goToEmployeeList(){
    this.router.navigate(['/products']);
  }

}

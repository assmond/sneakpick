import { Component, OnInit } from '@angular/core';
import {Order} from "../../../../models/order.model";
import {User} from "../../../../models/user.model";
import {OrderService} from "../../../../services/order.service";
import {LoginService} from "../../../../services/login.service";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import Swal from "sweetalert2";
import {Product} from "../../../../models/product.model";
import {ProductsService} from "../../../../services/products.service";
import baseUrl from "../../../../services/helper";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  products?: Product[];
  user: User;
  myDate: string;
  baseUrl = baseUrl;

  constructor(private productService: ProductsService, private loginService: LoginService, private router: Router, private datePipe: DatePipe) {
  }

  ngOnInit(): void {
    this.myDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd  hh:mm') || '';
    this.user = this.loginService.getUser()
    this.loadProducts()
  }

  loadProducts() {
    this.productService.getProductByUser().subscribe(
      (data: any) => {
        this.products = data;
      }, error => {
        console.log("error " + error);
      })
  }
  productDetails(id: number){
    this.router.navigate(['product-details', id]);
  }


  deleteProduct(id: number) {
    this.productService.delete(id).subscribe(data => {
      Swal.fire('Product deleted successfully ', '', 'success').then((result) => {
      });
      this.loadProducts();
    }, error => {
      console.log(error)
    })
  }

  orderDetails(id: number) {
    this.router.navigate(['order-details', id]);
  }


}

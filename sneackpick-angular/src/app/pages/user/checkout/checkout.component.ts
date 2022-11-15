import {Component, OnInit} from '@angular/core';
import {CartItem} from "../../../models/cart-item.model";
import baseUrl from "../../../services/helper";
import {ShoppingCartService} from "../../../services/shopping-cart.service";
import {SizesService} from "../../../services/sizes.service";
import {Router} from "@angular/router";
import {CheckoutService} from "../../../services/checkout.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  items: CartItem []
  baseUrl = baseUrl;
  size: any
  totalItems: number = 0;
  numberOfProduct = 0;

  constructor(private shoppingCartService: ShoppingCartService,
              private sizeService: SizesService,
              private checkoutService: CheckoutService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getItems();
  }

  getItems() {
    this.shoppingCartService.getCart().subscribe(
      (data) => {
        this.items = data.cartItems
        for (let item of this.items) {
          this.totalItems += Number(item.product.price);
          this.numberOfProduct++;
        }
        console.log("Total panier" + this.totalItems)

        console.log(this.items)
      }, error => {
        console.log(error)
      });
  }

  placeOrder() {
    const token=localStorage.getItem('accessToken')
    this.checkoutService.checkOut(token).subscribe((data) => {
      console.log(data)
      this.router.navigate(['/orders'])
      Swal.fire('Order has been created successfully', '', 'success').then((result) => {
        console.log(result);
      });
    }, (error) => {
      console.log(error)
    })
  }

}

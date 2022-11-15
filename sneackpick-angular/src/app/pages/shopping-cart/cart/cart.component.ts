import {Component, OnInit} from '@angular/core';
import {ShoppingCartService} from "../../../services/shopping-cart.service";
import {CartItem} from "../../../models/cart-item.model";
import baseUrl from "../../../services/helper";
import {SizesService} from "../../../services/sizes.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  items: CartItem []
  baseUrl = baseUrl;
  size: any
  totalItems:number =0;
  numberOfProduct=0;

  constructor(private shoppingCartService: ShoppingCartService, private sizeService: SizesService, private router :Router) {
  }

  ngOnInit(): void {
    this.getItems();
  }
  checkout(){
    this.router.navigate(['/checkout'])
  }
  /*getSize(id:any){
    this.sizeService.get(id).subscribe((data)=>{
      console.log(data)
    },(error) => {
      console.log(error)
    })
  }*/

  removeItem(id: any) {
    this.shoppingCartService.removeFromCart(id).subscribe(data => {
      this.numberOfProduct --;
      this.getItems();
      console.log("TOTAL APRES"+this.totalItems)
    }, error => {
      console.log(error)
    })
  }

  getItems() {
    this.shoppingCartService.getCart().subscribe(
      (data) => {
        this.items = data.cartItems
        for(let item of this.items){
          this.totalItems+=Number(item.product.price);
          this.numberOfProduct ++;
        }
        console.log("Total panier"+this.totalItems)

        console.log(this.items)
      }, error => {
        console.log(error)
      });
  }


}

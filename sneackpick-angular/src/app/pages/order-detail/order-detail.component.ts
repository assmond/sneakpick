import {Component, OnInit} from '@angular/core';
import {Order} from "../../models/order.model";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../services/order.service";
import {Product} from "../../models/product.model";
import baseUrl from "../../services/helper";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  id:number
  order: Order
  baseUrl=baseUrl

  constructor(private route:ActivatedRoute,private router: Router, private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.order = new Order();
    this.orderService.get(this.id).subscribe(data => {
      this.order = data;
      console.log(this.order)
    });
  }
}

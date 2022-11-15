import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../../services/order.service";
import {Order} from "../../../models/order.model";
import {Router} from "@angular/router";
import {User} from "../../../models/user.model";
import {DatePipe} from "@angular/common";
import {LoginService} from "../../../services/login.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-renting',
  templateUrl: './renting.component.html',
  styleUrls: ['./renting.component.css']
})
export class RentingComponent implements OnInit {

  orders?: Order[];
  user: User;
  myDate: string;

  constructor(private orderService: OrderService, private loginService: LoginService, private router: Router, private datePipe: DatePipe) {
  }

  ngOnInit(): void {
    this.myDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd  hh:mm') || '';
    this.user = this.loginService.getUser()
    this.loadOrders()
  }

  loadOrders() {
    this.orderService.getOrders().subscribe(
      (data: any) => {
        this.orders = data;
      }, error => {
        console.log("error " + error);
      })
  }


  deleteOrder(id: number) {
    this.orderService.delete(id).subscribe(data => {
      Swal.fire('Order deleted successfully ', '', 'success').then((result) => {
      });
      this.loadOrders();
    }, error => {
      console.log(error)
    })
  }

  orderDetails(id: number) {
    this.router.navigate(['order-details', id]);
  }

}

import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {LoginService} from './../../services/login.service';
import {ShoppingCartService} from "../../services/shopping-cart.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit,OnChanges {

  isLoggedIn = false;
  user: any = null;
  forAdmin: boolean;
  numberOfProduct: number;
  constructor(public login: LoginService, private shoppingCartService: ShoppingCartService) {

  }
  ngOnChanges(changes: SimpleChanges): void{
    if (this.isLoggedIn) {
      console.log("INSIDE NAV gt items cart")
      this.getNumberOfProduct();
    }
  }
  ngOnInit(): void {
    this.isLoggedIn = this.login.isLoggedIn();
    console.log("IS LOGEDIN nav bar "+this.isLoggedIn)
    if (this.isLoggedIn) {
      console.log("INSIDE NAV gt items cart")
       this.getNumberOfProduct();
    }
    this.forAdmin = this.login.isAdmin();
    this.user = this.login.getUser();
    this.login.loginStatusSubjec.asObservable().subscribe(
      data => {
        this.isLoggedIn = this.login.isLoggedIn();
        this.user = this.login.getUser();
      }
    )
  }

  public logout() {
    this.login.logout();
    window.location.reload();
  }

  getNumberOfProduct() {
    this.shoppingCartService.getCart().subscribe(
      (data) => {
        this.numberOfProduct=data.cartItems.length
    }, error => {
    });
  }

}

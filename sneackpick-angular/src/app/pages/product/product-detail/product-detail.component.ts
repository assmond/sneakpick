import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProductsService} from "../../../services/products.service";
import {Product} from "../../../models/product.model";
import {SizesService} from "../../../services/sizes.service";
import {BrandsService} from "../../../services/brands.service";
import {Brand} from "../../../models/brand.model";
import {Size} from "../../../models/size.model";
import baseUrl from "../../../services/helper";
import {ShoppingCartService} from "../../../services/shopping-cart.service";
import {HttpHeaders} from "@angular/common/http";
import {LoginService} from "../../../services/login.service";
import Swal from "sweetalert2";
import {ReviewService} from "../../../services/review.service";
import {Review} from "../../../models/review.model";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {CommentService} from "../../../services/comment.service";
import {Comment} from "../../../models/comment.model";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  id: number
  product: Product = new Product()
  brands?: Brand[]
  sizes?: Size[]
  //comments:Comment[]
  selectedSize: string
  baseUrl = baseUrl;
  isLoggedIn = false;
  from: Date
  to: Date
  rate: number
  rates = [1, 2, 3, 4, 5]

  size: string;
  qty: string;
  comment:string

  constructor(private route: ActivatedRoute, private productsService: ProductsService,
              private sizeService: SizesService, private brandService: BrandsService,
              private cartService: ShoppingCartService,
              private loginService: LoginService, private router: Router,
              private reviewService: ReviewService,
              private commentService:CommentService) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.product = new Product();
    this.productsService.get(this.id).subscribe(data => {
      this.product = data;
      console.log(this.product)
    });
    this.brandService.getAll().subscribe(data => {
      this.brands = data;
    });
    this.sizeService.getAll().subscribe(data => {
      this.sizes = data;
    });
    //this.loadComments()
  }

  onSelectSize(event: Event) {
    this.selectedSize = (event.target as HTMLInputElement).value;
  }

  rateProduct() {
    if (this.loginService.getUser() != null) {
      if (!this.rate) {
        Swal.fire('Please select a number from the list of rating ', '', 'warning').then((result) => {
          console.log(result);
        });
        return
      } else {
        const formData = new FormData();
        formData.append("product", this.id?.toString() || '')
        formData.append('stars', this.rate.toString() || '');
        this.reviewService.addReview(formData).subscribe(data => {
          console.log(data)
          Swal.fire('Think you for your review ', 'Your review will be taken next update', 'success').then((result) => {
            console.log(result);
          });

        }, error => {
          console.log(error)
        });
      }
    }else{
      Swal.fire('Login', 'To rate a product  create your account, or login', 'warning').then((result) => {
        console.log(result);
        if (result) {
          this.router.navigate(['/login']);
        } else {
          this.router.navigate(['/login']);
        }
      });
    }


  }

  AddToCart() {
    if (!this.size || !this.from || !this.to) {
      Swal.fire('Please set size, date start, and date to  ', '', 'warning').then((result) => {
        console.log(result);
      });
      return;
    }
    if (this.loginService.getUser() != null) {
      const formData = new FormData();
      formData.append("product", this.id?.toString() || '')
      formData.append('size', this.size);
      formData.append('qty', this.qty);
      formData.append('from', this.from.toString());
      formData.append('to', this.to.toString());
      console.log("Id " + this.id)
      console.log("Qte " + this.qty)
      console.log("Size " + this.size)
      this.cartService.addToCart(formData).subscribe((data) => {
        this.router.navigate(['/cart'])
      }, (error => {

      }));
    } else {
      Swal.fire('Login', 'To conitue shopping create your account, or login', 'warning').then((result) => {
        console.log(result);
        if (result) {
          this.router.navigate(['/login']);
        } else {
          this.router.navigate(['/login']);
        }
      });
    }
  }
  addComment() {
    if (this.loginService.getUser() != null) {
      if (!this.comment) {
        Swal.fire('Please create a comment ', '', 'warning').then((result) => {
          console.log(result);
        });
        return
      } else {
        const formData = new FormData();
        formData.append("product", this.id?.toString() || '')
        formData.append('comment', this.comment.toString() || '');
        this.commentService.addComment(formData).subscribe(data => {
          console.log(data)
          Swal.fire('Think you for your comment ', 'Your comment will be taken next update', 'success').then((result) => {
            console.log(result);
          });
        }, error => {
          console.log(error)
        });
      }
    }else{
      Swal.fire('Login', 'To comment  a product  create your account, or login', 'warning').then((result) => {
        console.log(result);
        if (result) {
          this.router.navigate(['/login']);
        } else {
          this.router.navigate(['/login']);
        }
      });
    }


  }
  /*loadComments(){
    this.commentService.getComents(this.id).subscribe(data=>{
      this.comments=data
    }, error=>{
      console.log(error)
    })
  }*/
}

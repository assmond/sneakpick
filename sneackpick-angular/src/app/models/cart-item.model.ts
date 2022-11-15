import {Product} from "./product.model";
import {User} from "./user.model";
import {Size} from "./size.model";

export class CartItem {
  id:any;
  qty:number;
  size:number;
  product:Product;
  user :User;
  order:any;
  sizes:Size[]
}

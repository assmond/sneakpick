import {Product} from "./product.model";
import {User} from "./user.model";

export class Review {
  id:number
  product:Product
  user:User
  stars:number
}

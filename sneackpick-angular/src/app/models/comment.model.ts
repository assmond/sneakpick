import {User} from "./user.model";
import {Product} from "./product.model";

export class Comment {
  id:number
  user:User
  product:Product
  commentText:string
}

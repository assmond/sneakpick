import {CartItem} from "./cart-item.model";
import {User} from "./user.model";

export class Order {
  id:number;
  orderDate:Date;
  shippingDate :Date;
  orderStatus :string;
  orderTotal:number;
  cartItems: CartItem[];
  user:User;
  dateStart:Date
  dateEnd:Date

}

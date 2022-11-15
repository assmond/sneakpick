import {Brand} from "./brand.model";
import {Size} from "./size.model";

export class Product {

  id: any;
  name: string;
  description: string;
  published: boolean;
  brand:Brand ;
  picture?:string;
  avialable?:boolean;
  promoDateEnd?: Date;
  promoDateStart?:Date;
  size?:Size;
  image:File;
  image2 :File
  image3: File
  sizes?:Size [];
  pubDate?: Date;
  stock :any;
  price?:string;
  file?: FileList;

}

import { Pipe, PipeTransform } from '@angular/core';
import {Product} from "../models/product.model";


@Pipe({ name: 'sneakFilter' })
export class FilterPipe implements PipeTransform {

  transform(items: Product[], searchText: string): Product[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter(it => {
      return it.name?.toLocaleLowerCase().includes(searchText) ;
    });
  }
}

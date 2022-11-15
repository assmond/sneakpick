import {Pipe, PipeTransform} from '@angular/core';
import {User} from "../models/user.model";


@Pipe({name: 'userFilter'})
export class UserFilterPipe implements PipeTransform {
  transform(items: User[], searchText: string): User[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter(it => {
      return (it.firstName.toLocaleLowerCase().includes(searchText) || it.lastName.toLocaleLowerCase().includes(searchText));
    });
  }
}

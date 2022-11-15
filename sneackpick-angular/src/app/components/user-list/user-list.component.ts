import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product.model";
import {User} from "../../models/user.model";
import {ProductsService} from "../../services/products.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];
  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'email','actions'];
  dataSource : User[];

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadUsers()
  }

  loadUsers() {
    this.userService.getUsers().subscribe(
      (data: User[]) => {
        console.log(data)
        this.users = data;
        this.dataSource=data;
      }, error => {
        console.log("error " + error);
      })
  }

  deleteProduct(row: any) {
    this.userService.deleteUser(row.id).subscribe(data => {
      this.loadUsers();
    }, error => {
      console.log(error)
    })
  }

}

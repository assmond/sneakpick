import {Component, OnInit} from '@angular/core';
import {Product} from "../../../models/product.model";
import {User} from "../../../models/user.model";
import baseUrl from "../../../services/helper";
import {Router} from "@angular/router";
import {DomSanitizer} from "@angular/platform-browser";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  users: User[];
  baseUrl = baseUrl;
  searchText: string

  constructor(private userService: UserService, private router: Router, private _sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.loadUsers()
  }


  loadUsers() {
    this.userService.getUsers().subscribe(
      (data: any) => {
        this.users = data;
      }, error => {
        console.log("error " + error);
      })
  }

  userDetails(id: number) {
    this.router.navigate(['user-details', id]);
  }

}

import { Component, OnInit } from '@angular/core';
import {User} from "../../../models/user.model";
import {UserService} from "../../../services/user.service";
import {LoginService} from "../../../services/login.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user:User;
  myDate :string;

  constructor(private userService:UserService, private loginService:LoginService,private datePipe :DatePipe) { }

  ngOnInit(): void {
    this.myDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd  hh:mm') || '' ;
    this.user=this.loginService.getUser()
  }

}

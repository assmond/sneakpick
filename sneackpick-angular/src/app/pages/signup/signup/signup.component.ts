import {Component, OnInit} from '@angular/core';
import Swal from 'sweetalert2';
import {MatSnackBar} from '@angular/material/snack-bar';
import {UserService} from './../../../services/user.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public user = {
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    providerUserId: 'LOCAL',
  }
  form: any = {};
  errorMessage = '';
  isSuccessful = false;
  isSignUpFailed = false;

  constructor(private userService: UserService, private snack: MatSnackBar, private router: Router) {
  }

  ngOnInit(): void {
  }

  formSubmit() {
    console.log(this.user);
    if (this.user.email == '' || this.user.email == null) {
      this.snack.open('Email is required !!', 'Accept', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
      return;
    }

    this.userService.registerUser(this.user).subscribe(
      (data) => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        console.log(data);
        Swal.fire('saved user', 'User successfully registered, an email was sending to your mail box', 'success').then((result) => {
            this.router.navigate(['/login']);
        });

      }, (error) => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
        this.snack.open('A system error has occurred !!', 'Accept', {
          duration: 3000
        });
      }
    )
  }
}

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LoginService} from '../../../services/login.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import google_url from "../../../AppConstants";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginData = {
    "email": '',
    "password": '',
  }
  googleURL = google_url;
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;


  constructor(private snack: MatSnackBar, private loginService: LoginService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const token: string = this.route.snapshot.queryParamMap.get('token') || '';
    const error: string = this.route.snapshot.queryParamMap.get('error') || '';
    if (this.loginService.getToken()) {
      console.log("TOKEN exist " +this.loginService.getToken())
      this.isLoggedIn = true;
      this.currentUser = this.loginService.getUser();
    } else if (token) {
      console.log("TOKEN exist in URL : " +token)
      this.loginService.loginUser(token);
      this.isLoggedIn = true;
      this.loginService.getCurrentUser(token).subscribe(
        data => {
          this.loginService.setUser(data);
          console.log("ISIDE LOAD USER "+data)
          if (this.loginService.getUserRole() == 'ROLE_ADMIN') {
            window.location.href = '/admin';
            this.loginService.loginStatusSubjec.next(true);
          } else if (this.loginService.getUserRole() == 'ROLE_USER') {
            window.location.href = '/';
            this.loginService.loginStatusSubjec.next(true);
          }
          console.log("DATA INSIDE NGINIT LOGIN : " + data)
        },
        err => {
          console.log("INSIDE ERROR"+err)
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      );
    } else if (error) {
      this.errorMessage = error;
      this.isLoginFailed = true;
    }
  }

  formSubmit() {
    if (this.loginData.email.trim() == '' || this.loginData.email.trim() == null) {
      this.snack.open('Username is required!!', 'Accept', {
        duration: 3000
      })
      return;
    }

    if (this.loginData.password.trim() == '' || this.loginData.password.trim() == null) {
      this.snack.open('Password is required !!', 'Accept', {
        duration: 3000
      })
      return;
    }

    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);
        this.loginService.loginUser(data.accessToken);
        this.loginService.setUser(data.user);
        if (this.loginService.getUserRole() == 'ROLE_ADMIN') {
          //dashboard admin
          window.location.href = '/admin';
          //this.router.navigate(['/admin']);
          this.loginService.loginStatusSubjec.next(true);
        } else if (this.loginService.getUserRole() == 'ROLE_USER') {
          //user dashboard
          window.location.href = '/';
          //this.router.navigate(['/']);
          this.loginService.loginStatusSubjec.next(true);
        }
      }
      , (error) => {
        console.log(error);
        this.snack.open('Invalid details, please try again !!', 'Accept', {
          duration: 3000
        });
      }
    )
  }
}

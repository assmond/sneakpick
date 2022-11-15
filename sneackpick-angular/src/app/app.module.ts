import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './pages/login/login/login.component';
import { SignupComponent } from './pages/signup/signup/signup.component';
import { HomeComponent } from './pages/home/home/home.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard/dashboard.component';
import {MatCardModule} from '@angular/material/card';
import {MatToolbar, MatToolbarModule, MatToolbarRow} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard/user-dashboard.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProductDetailComponent } from './pages/product/product-detail/product-detail.component';
import { ProductListComponent } from './pages/product/product-list/product-list.component';
import { CreateProductComponent } from './pages/product/create-product/create-product.component';
import { UpdateProductComponent } from './pages/product/update-product/update-product.component';
import { ProfileComponent } from './pages/user/profile/profile.component';
import { CartComponent } from './pages/shopping-cart/cart/cart.component';

import { FilterPipe } from './filters/filter.pipe';
import { UserListComponent } from './components/user-list/user-list.component';
import { ReviewComponent } from './components/review/review.component';
import {MatTableModule} from "@angular/material/table";
import { MatSidenavModule} from "@angular/material/sidenav";
import {MatStepperModule} from "@angular/material/stepper";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatTabsModule} from "@angular/material/tabs";
import {MatSortModule} from "@angular/material/sort";
import {MatSliderModule} from "@angular/material/slider";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatSelectModule} from "@angular/material/select";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {MatRadioModule} from "@angular/material/radio";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatChipsModule} from "@angular/material/chips";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatListModule} from "@angular/material/list";
import {DatePipe} from "@angular/common";
import { RentingComponent } from './pages/user/renting/renting.component';
import { CheckoutComponent } from './pages/user/checkout/checkout.component';
import { OrderDetailComponent } from './pages/order-detail/order-detail.component';
import { ListUsersComponent } from './pages/user/list-users/list-users.component';
import {UserFilterPipe} from "./filters/filter.user.pipe";
import { CommentsComponent } from './components/comments/comments.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    DashboardComponent,
    UserDashboardComponent,
    FooterComponent,
    ProductDetailComponent,
    ProductListComponent,
    CreateProductComponent,
    UpdateProductComponent,
    ProfileComponent,
    CartComponent,
    FilterPipe,
    UserFilterPipe,
    UserListComponent,
    ReviewComponent,
    RentingComponent,
    CheckoutComponent,
    OrderDetailComponent,
    ListUsersComponent,
    CommentsComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatTableModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatStepperModule,
    MatListModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NormalGuard} from './services/normal.guard';
import {AdminGuard} from './services/admin.guard';
//import { UserDashboardComponent } from 'pages/user/user-dashboard/user-dashboard.component';
import {DashboardComponent} from './pages/admin/dashboard/dashboard/dashboard.component';
import {LoginComponent} from './pages/login/login/login.component';
import {SignupComponent} from './pages/signup/signup/signup.component';
import {HomeComponent} from './pages/home/home/home.component';
import {UserDashboardComponent} from "./pages/user/user-dashboard/user-dashboard/user-dashboard.component";
import {ProductsService} from "./services/products.service";
import {CreateProductComponent} from "./pages/product/create-product/create-product.component";
import {ProductListComponent} from "./pages/product/product-list/product-list.component";
import {UpdateProductComponent} from "./pages/product/update-product/update-product.component";
import {ProductDetailComponent} from "./pages/product/product-detail/product-detail.component";
import {ProfileComponent} from "./pages/user/profile/profile.component";
import {CartComponent} from "./pages/shopping-cart/cart/cart.component";
import {AllUsersGuard} from "./services/all-users.guard";
import {CheckoutComponent} from "./pages/user/checkout/checkout.component";
import {RentingComponent} from "./pages/user/renting/renting.component";
import {OrderDetailComponent} from "./pages/order-detail/order-detail.component";
import {UserListComponent} from "./components/user-list/user-list.component";
import {ListUsersComponent} from "./pages/user/list-users/list-users.component";

const routes: Routes = [
  {path: 'users', component: ListUsersComponent,canActivate: [AllUsersGuard]},
  {path: 'order-details/:id', component: OrderDetailComponent,canActivate: [AllUsersGuard]},
  {path: 'orders', component: RentingComponent,canActivate: [AllUsersGuard]},
  {path: 'checkout', component: CheckoutComponent,canActivate: [AllUsersGuard]},
  {path: 'profile', component: ProfileComponent,canActivate: [AllUsersGuard]},
  {path: 'cart', component: CartComponent},
  {path: 'products', component: ProductListComponent,canActivate: [AdminGuard]},
  {path: 'create-product', component: CreateProductComponent,canActivate: [AllUsersGuard]},
  {path: 'update-product/:id', component: UpdateProductComponent,canActivate: [AdminGuard]},
  {path: 'product-details/:id', component: ProductDetailComponent},
  {path: '',component: HomeComponent, pathMatch: 'full'},
  {path: 'signup', component: SignupComponent, pathMatch: 'full'},
  {path: 'login', component: LoginComponent,pathMatch: 'full'},
  {path: 'admin', component: DashboardComponent, pathMatch: 'full', canActivate: [AdminGuard]},
  {path: 'my-sneakers', component: UserDashboardComponent, pathMatch: 'full', canActivate: [AllUsersGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

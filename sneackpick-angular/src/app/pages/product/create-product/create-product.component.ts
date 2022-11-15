import {Component, OnInit} from '@angular/core';
import {Product} from "../../../models/product.model";
import {ProductsService} from "../../../services/products.service";
import {Router} from "@angular/router";
import {Brand} from "../../../models/brand.model";
import {BrandsService} from "../../../services/brands.service";
import {SizesService} from "../../../services/sizes.service";
import {Size} from "../../../models/size.model";
import Swal from "sweetalert2";

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  uploadedImage: File;
  uploadedImage2: File;
  uploadedImage3: File;
  postResponse: any;
  successResponse: string;
  brands: Brand[];
  sizes: Size[];
  selectedBrand?: string
  selectedSize?: string
  product: Product = new Product();

  constructor(private productService: ProductsService,
              private router: Router, private brandService: BrandsService, private sizeService: SizesService) {
  }


  public onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    console.log("image 0" + this.uploadedImage.name)
  }

  public onImageUpload2(event: any) {
    this.uploadedImage2 = event.target.files[0];
    console.log("image 1" + this.uploadedImage2.name)
  }

  public onImageUpload3(event: any) {

    this.uploadedImage3 = event.target.files[0];
    console.log("image 3" + this.uploadedImage3.name)
  }

  imageUploadAction() {
    if(!this.product.name){
      Swal.fire('Please set name', '', 'warning').then((result) => {
      });
      return
    }
    if(!this.product.price){
      Swal.fire('Please set price', '', 'warning').then((result) => {
      });
      return
    }
    if(!this.selectedBrand){
      Swal.fire('Please set brand', '', 'warning').then((result) => {
      });
      return
    }
    if(!this.selectedSize){
      Swal.fire('Please set sizes for this product', '', 'warning').then((result) => {
      });
      return
    }
    if(!this.uploadedImage){
      Swal.fire('Please add one image for this product', '', 'warning').then((result) => {
      });
      return
    }
    const imageFormData = new FormData();
    imageFormData.append('name', this.product.name || '');
    imageFormData.append('brand', this.selectedBrand || '');
    imageFormData.append('size', this.selectedSize || '');
    imageFormData.append('price', this.product.price || '');
    imageFormData.append('description', this.product.description || '');
    if (this.uploadedImage != null) {
      imageFormData.append('image1', this.uploadedImage, this.uploadedImage.name);
    }
    if (this.uploadedImage2 != null) {
      imageFormData.append('image2', this.uploadedImage2, this.uploadedImage.name);
    }
    if (this.uploadedImage3 != null) {
      imageFormData.append('image3', this.uploadedImage3, this.uploadedImage.name);
    }

    this.productService.create(imageFormData)
      .subscribe((response) => {
          if (response.id != null) {
            Swal.fire('Sneaker saved ', 'Product successfully added', 'success').then((result) => {
            });
            this.router.navigate(['/my-sneakers'])
          } else {
            this.successResponse = 'Image not uploaded due to some error!';
          }
        }
      );
  }


  ngOnInit(): void {
    this.brandService.getAll().subscribe(data => {
      this.brands = data;
    });
    this.sizeService.getAll().subscribe(data => {
      this.sizes = data;
    });
  }

  goToProductList() {
    this.router.navigate(['/products']);
  }

  onSelectSize(event: Event) {
    this.selectedSize = (event.target as HTMLInputElement).value;
  }

  onSelectBrand(event: Event) {
    this.selectedBrand = (event.target as HTMLInputElement).value;
  }

}

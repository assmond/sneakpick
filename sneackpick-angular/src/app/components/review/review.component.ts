import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ReviewService} from "../../services/review.service";
import {Review} from "../../models/review.model";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  starNumber: number;
  @Input() productId: number;

  constructor(private reviewService: ReviewService) {
  }

  ngOnInit(): void {
    console.log("ID RIVEW NG INIT ", this.productId)
    this.getReview(this.productId);
  }


  getReview(id: number) {
    console.log("ID RIVEW " + this.productId)
    this.reviewService.getReview(id).subscribe(data => {
      console.log(data)
      this.starNumber = data.stars
    }, error => {
      console.log(error)
    })
  }

}

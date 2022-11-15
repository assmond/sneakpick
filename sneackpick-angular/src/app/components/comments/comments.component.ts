import {Component, Input, OnInit} from '@angular/core';
import {Comment} from "../../models/comment.model";
import {CommentService} from "../../services/comment.service";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  comments: Comment[]
  @Input() productId: number;

  constructor(private commentService: CommentService) {
  }

  ngOnInit(): void {
    this.loadComments()
  }

  loadComments() {
    this.commentService.getComents(this.productId).subscribe(data => {
      this.comments = data
    }, error => {
      console.log(error)
    })
  }
}

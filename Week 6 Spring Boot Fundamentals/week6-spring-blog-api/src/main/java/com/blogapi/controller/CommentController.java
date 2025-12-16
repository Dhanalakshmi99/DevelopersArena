package com.blogapi.controller;

import com.blogapi.model.dto.CommentResponse;
import com.blogapi.model.dto.ApiResponse;
import com.blogapi.model.entity.Comment;
import com.blogapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Get all comments for a post")
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @Operation(summary = "Add a comment to a post")
    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponse> addCommentToPost(@PathVariable Long postId,
                                                            @Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addCommentToPost(postId, comment));
    }

    @Operation(summary = "Update a comment")
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id,
                                                         @Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(id, comment));
    }

    @Operation(summary = "Delete a comment by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(new ApiResponse(true, "Comment deleted successfully"));
    }
}

package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<?> createCommentByProjectId(
            @RequestParam(name = "postId", required = true) Long postId,
            @RequestBody CommentDto commentDto
    ) {
        try {
            CommentDto comment = commentService.createCommentByProjectId(postId, commentDto);
            return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCommentsByProjectId(
            @RequestParam(name = "postId", required = true) Long postId
    ) {
        try {
            List<CommentDto> listOfComments = commentService.getAllCommentsByProjectId(postId);
            return new ResponseEntity<List<CommentDto>>(listOfComments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getCommentByIdByProjectId(
            @PathVariable(name = "commentId", required = true) Long commentId,
            @RequestParam(name = "postId", required = true) Long postId
    ) {
        try {
            CommentDto comments = commentService.getCommentByIdByProjectId(postId, commentId);
            return new ResponseEntity<CommentDto>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<?> updateCommentByProjectId(
            @PathVariable(name = "commentId", required = true) Long commentId,
            @RequestParam(name = "postId", required = true) Long postId,
            @RequestBody CommentDto commentDto
    ) {
        try {
            CommentDto comments = commentService.updateCommentByProjectId(postId, commentId, commentDto);
            return new ResponseEntity<CommentDto>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> deleteCommentByProjectId(
            @PathVariable(name = "commentId", required = true) Long commentId,
            @RequestParam(name = "postId", required = true) Long postId
    ) {
        try {
            String deleteComment = commentService.deleteCommentByProjectId(postId, commentId);
            return new ResponseEntity<String>(deleteComment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

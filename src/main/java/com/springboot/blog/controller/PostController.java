package com.springboot.blog.controller;

import com.springboot.blog.dto.PaginationDto;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        try {
            PostDto post = postService.createPost(postDto);
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPost() {
        try {
            List<PostDto> allPost = postService.getAllPost();
            return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        try {
            PostDto postById = postService.getPostById(id);
            return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-post")
    public ResponseEntity<?> getAllPostByPaginationAndSorting(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        try {
            PaginationDto allPostByPS = postService.getAllPostByPaginationAndSorting(pageNo, pageSize);
            return new ResponseEntity<PaginationDto>(allPostByPS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto, @PathVariable("id") Long id) {
        try {
            PostDto updatePost = postService.updatePost(postDto, id);
            return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        try {
            String deletePost = postService.deletePost(id);
            return new ResponseEntity<String>(deletePost, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

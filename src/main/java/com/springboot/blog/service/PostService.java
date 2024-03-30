package com.springboot.blog.service;

import com.springboot.blog.dto.PaginationDto;
import com.springboot.blog.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPost();
    PostDto getPostById(Long id);
    PaginationDto getAllPostByPaginationAndSorting(Integer pageNo, Integer pageSize);
    PostDto updatePost(PostDto postDto, Long id);
    String deletePost(Long id);
}

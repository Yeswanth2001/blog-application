package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PaginationDto;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        try {
            Post post = mapToEntity(postDto);
            Post newPost = postRepository.save(post);
            return mapToDto(newPost);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return mapToDto(post);
    }

    @Override
    public PaginationDto getAllPostByPaginationAndSorting(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> listOfPost = listOfPosts.stream().map(this::mapToDto).toList();

        return mapToDto(listOfPost, posts);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        postRepository.save(post);
        return mapToDto(post);
    }

    @Override
    public String deletePost(Long id) {
        postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        postRepository.deleteById(id);
        return "Successfully Deleted";
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

    private PaginationDto mapToDto(List<?> listOfPost, Page<?> posts) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setPageNo(posts.getNumber());
        paginationDto.setListOfItems(listOfPost);
        paginationDto.setPageSize(posts.getSize());
        paginationDto.setTotalPages(posts.getTotalPages());
        paginationDto.setTotalResult(posts.getTotalElements());
        paginationDto.setIsLast(posts.isLast());

        return paginationDto;
    }
}

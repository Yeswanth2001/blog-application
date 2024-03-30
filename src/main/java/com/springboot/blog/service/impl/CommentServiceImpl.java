package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDto createCommentByProjectId(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment commentResponse = commentRepository.save(comment);
        return mapToDto(commentResponse);
    }

    @Override
    public List<CommentDto> getAllCommentsByProjectId(Long postId) {
        List<Comment> allComments = commentRepository.findByPostId(postId);
        return allComments.stream().map(this::mapToDto).toList();
    }

    @Override
    public CommentDto getCommentByIdByProjectId(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Not Found"));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new RuntimeException("Not Found");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentByProjectId(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Not Found"));
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new RuntimeException("Not Found");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);

        Comment commentResponse = commentRepository.save(comment);
        return mapToDto(commentResponse);
    }

    @Override
    public String deleteCommentByProjectId(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Not Found"));
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new RuntimeException("Not Found");
        }
        commentRepository.deleteById(commentId);
        return "Successfully Comment Deleted";
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}

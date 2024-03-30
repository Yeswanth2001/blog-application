package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {

    CommentDto createCommentByProjectId(Long postId, CommentDto commentDto);
    List<CommentDto> getAllCommentsByProjectId(Long postId);
    CommentDto getCommentByIdByProjectId(Long postId, Long commentId);
    CommentDto updateCommentByProjectId(Long postId, Long commentId, CommentDto commentDto);
    String deleteCommentByProjectId(Long postId, Long commentId);
}

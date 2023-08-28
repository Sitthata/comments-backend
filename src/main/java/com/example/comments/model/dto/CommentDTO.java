package com.example.comments.model.dto;

import com.example.comments.model.User;

import java.util.List;

public record CommentDTO(Long id, String content, String TimeAgo, Integer score, User user, List<CommentDTO> replies) {

}

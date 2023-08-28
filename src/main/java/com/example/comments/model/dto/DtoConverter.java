package com.example.comments.model.dto;

import com.example.comments.Utils.TimeAgoUtility;
import com.example.comments.model.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {
    public static CommentDTO toDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreatedAt(TimeAgoUtility.getTimeAgo(comment.getCreatedAt()));
        commentDTO.setScore(comment.getScore());
        commentDTO.setUser(comment.getUser());

        // Recursive call to set the replies
        if (comment.getReplies() != null) {
            commentDTO.setReplies(comment.getReplies().stream().map(DtoConverter::toDto).collect(Collectors.toList()));
        }
        return commentDTO;
    }

    public static List<CommentDTO> toDtoList(List<Comment> comments) {
        return comments.stream().map(DtoConverter::toDto).collect(Collectors.toList());
    }
}

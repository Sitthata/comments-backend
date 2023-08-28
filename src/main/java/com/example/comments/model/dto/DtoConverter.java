package com.example.comments.model.dto;

import com.example.comments.Utils.TimeAgoUtility;
import com.example.comments.model.Comment;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {
    public static CommentDTO toDto(Comment comment) {
        List<CommentDTO> repliesDto = null;
        if (comment.getReplies() != null) {
            repliesDto = comment.getReplies().stream().map(DtoConverter::toDto).collect(Collectors.toList());
        }
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                TimeAgoUtility.getTimeAgo(comment.getCreatedAt()),
                comment.getScore(),
                comment.getUser(),
                repliesDto
        );
    }

    public static List<CommentDTO> toDtoList(List<Comment> comments) {
        return comments.stream().map(DtoConverter::toDto).collect(Collectors.toList());
    }
}

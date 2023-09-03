package com.example.comments.comment;

import com.example.comments.model.dto.CommentDTO;
import com.example.comments.model.dto.DtoConverter;
import com.example.comments.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<CommentDTO> getComment() {
        List<Comment> comments = commentRepository.findByParentCommentIsNull();
        return DtoConverter.toDtoList(comments);
    }

    public Comment addComment(Comment comment) {
        // If it's a reply, find the parent comment
        if (comment.getParentComment() != null) {
            Comment parentComment = commentRepository.findById(comment.getParentComment().getId()).orElse(null);
            comment.setParentComment(parentComment);
            if (parentComment == null) {
                System.out.println("Parent Comment not found");
                return null;
            }
        }
        return commentRepository.save(comment);
    }
}

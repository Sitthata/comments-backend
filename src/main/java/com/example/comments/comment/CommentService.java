package com.example.comments.comment;

import com.example.comments.model.dto.CommentDTO;
import com.example.comments.model.dto.DtoConverter;
import com.example.comments.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @GetMapping
    public List<CommentDTO> getComment() {
        List<Comment> comments = commentRepository.findByParentCommentIsNull();
        return DtoConverter.toDtoList(comments);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}

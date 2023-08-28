package com.example.comments.config;

import com.example.comments.comment.CommentRepository;
import com.example.comments.model.Comment;
import com.example.comments.model.User;
import com.example.comments.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.*;

@Configuration
public class SecurityConfig {
    @Bean
    CommandLineRunner commandLineRunner(CommentRepository commentRepository, UserRepository userRepository) {
        return args -> {
            User user1 = new User("John Doe");
            userRepository.save(user1);
            Comment firstComment = new Comment(
                    "This is the first comment",
                    now(),
                    0,
                    user1,
                    null,
                    null
            );
            Comment secondComment = new Comment(
                    "This is the second comment",
                    now(),
                    0,
                    user1,
                    null,
                    firstComment
            );
            Comment thirdComment = new Comment(
                    "This is the third comment",
                    now(),
                    0,
                    user1,
                    null,
                    firstComment
            );
            Comment fourthComment = new Comment(
                    "This is the fourth comment",
                    now(),
                    0,
                    user1,
                    null,
                    null
            );
            commentRepository.saveAll(List.of(firstComment, secondComment, thirdComment, fourthComment));
        };
    }
}

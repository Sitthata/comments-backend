package com.example.comments.model;

import com.example.comments.Utils.TimeAgoUtility;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

public class CommentSerializer extends JsonSerializer<Comment> {
    @Override
    public void serialize(Comment comment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", comment.getId());
        jsonGenerator.writeStringField("content", comment.getContent());
        jsonGenerator.writeNumberField("score", comment.getScore());

        if (comment.getUser() != null) {
            jsonGenerator.writeObjectFieldStart("user");
            jsonGenerator.writeNumberField("id", comment.getUser().getId());
            jsonGenerator.writeStringField("username", comment.getUser().getUsername());
            jsonGenerator.writeEndObject();
        }

        if (comment.getReplies() != null && !comment.getReplies().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("replies");
            for (Comment reply : comment.getReplies()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", reply.getId());
                jsonGenerator.writeStringField("content", reply.getContent());
                jsonGenerator.writeNumberField("score", reply.getScore());

                jsonGenerator.writeObjectFieldStart("user");
                jsonGenerator.writeNumberField("id", reply.getUser().getId());
                jsonGenerator.writeStringField("username", reply.getUser().getUsername());
                jsonGenerator.writeEndObject();

                jsonGenerator.writeStringField("createdAt", TimeAgoUtility.getTimeAgo(reply.getCreatedAt()));
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }

        jsonGenerator.writeStringField("createdAt", TimeAgoUtility.getTimeAgo(comment.getCreatedAt()));
        jsonGenerator.writeEndObject();
    }
}
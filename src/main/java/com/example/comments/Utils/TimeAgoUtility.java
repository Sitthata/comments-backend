package com.example.comments.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeAgoUtility {
    public static String getTimeAgo(LocalDateTime pastDate) {
        LocalDateTime now = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(pastDate, now);
        long hours = ChronoUnit.HOURS.between(pastDate, now);
        long minutes = ChronoUnit.MINUTES.between(pastDate, now);

        if (days > 30) {
            return days / 30 + " months ago";
        } else if (days > 0) {
            return days + " days ago";
        } else if (hours > 0) {
            return hours + " hours ago";
        } else if (minutes > 0) {
            return minutes + " minutes ago";
        } else {
            return "just now";
        }
    }
}

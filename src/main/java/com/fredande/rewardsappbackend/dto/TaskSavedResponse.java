package com.fredande.rewardsappbackend.dto;

import org.springframework.web.util.HtmlUtils;

public record TaskSavedResponse(String title, String description, Integer points) {

    public TaskSavedResponse(String title, String description, Integer points) {
        this.title = HtmlUtils.htmlEscape(title);
        this.description = HtmlUtils.htmlEscape(description);
        this.points = points;
    }

}

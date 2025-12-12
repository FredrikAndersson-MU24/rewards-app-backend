package com.fredande.rewardsappbackend.dto;

import org.springframework.web.util.HtmlUtils;

import java.util.Date;

public record TaskReadResponse(String title,
                               String description,
                               Integer points,
                               Date created,
                               Date updated,
                               boolean done) {

    public TaskReadResponse(String title, String description, Integer points, Date created, Date updated, boolean done) {
        this.title = HtmlUtils.htmlEscape(title);
        this.description = HtmlUtils.htmlEscape(description);
        this.points = points;
        this.created = created;
        this.updated = updated;
        this.done = done;
    }

}

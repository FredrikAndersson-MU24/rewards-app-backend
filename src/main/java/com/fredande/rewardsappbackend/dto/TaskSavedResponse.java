package com.fredande.rewardsappbackend.dto;

import org.springframework.web.util.HtmlUtils;

public class TaskSavedResponse {

    private final String title;
    private final String description;


    public TaskSavedResponse(String title, String description) {
        this.title = HtmlUtils.htmlEscape(title);
        this.description = HtmlUtils.htmlEscape(description);
    }

}

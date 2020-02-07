package com.github.lhvubtqn.lazyscanner.ui.files;

import java.io.Serializable;

public class File implements Serializable {
    String username, title, content, created_at;

    public File(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
    public File(String username, String title, String content, String created_at) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}

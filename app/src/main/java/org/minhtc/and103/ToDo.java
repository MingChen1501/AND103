package org.minhtc.and103;

import java.util.HashMap;

public class ToDo {
    private String id;
    private String title;
    private String content;

    public ToDo() {
    }

    public ToDo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public HashMap<String, Object> convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("title", title);
        result.put("content", content);
        return result;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    // toDisplay method here, display with template Title: $title\nContent: $content
    public String toDisplay() {
        return "Title: " + title + "\nContent: " + content;
    }
}

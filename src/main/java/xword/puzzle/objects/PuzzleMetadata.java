package xword.puzzle.objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author alex
 */
public class PuzzleMetadata {

    private String title;
    private Integer difficulty;
    private String author;
    private String editor;
    private String source;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("difficulty", difficulty)
                .append("author", author)
                .append("editor", editor)
                .append("source", source)
                .append("description", description)
                .toString();
    }
}

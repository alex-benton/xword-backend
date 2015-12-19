package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Clue;

import java.util.List;

/**
 * @author alex
 */
public class UpdatePuzzleResponse {

    private String id;
    private String editId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("editId", editId)
                .toString();
    }
}

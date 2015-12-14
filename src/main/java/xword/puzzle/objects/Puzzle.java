package xword.puzzle.objects;
import org.springframework.data.annotation.Id;

/**
 * Created by alex on 12/13/15.
 */
public class Puzzle {

    @Id
    private String puzzleId;
    private String puzzleEditId;

}

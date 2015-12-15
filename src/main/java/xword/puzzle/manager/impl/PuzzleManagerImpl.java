package xword.puzzle.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Puzzle;
import xword.puzzle.repository.PuzzleRepository;
import xword.util.IdGenerator;

import java.util.UUID;

/**
 * @author alex
 */
@Component
public class PuzzleManagerImpl implements PuzzleManager {

    @Autowired
    private PuzzleRepository puzzleRepository;


    /**
     * Get the Puzzle with the provided id. Returns null if a Puzzle can't be found for the provided id.
     *
     * @param id the id of the puzzle to get.
     * @return the found Puzzle, or null if a Puzzle couldn't be found.
     */
    @Override
    public Puzzle get(String id) {
        return puzzleRepository.findOne(id);
    }

    /**
     * Get the Puzzle with the provided editId.
     * Returns null if a Puzzle can't be found for the provided editId.
     *
     * @param editId the editId of the puzzle to get.
     * @return the found Puzzle, or null if a Puzzle couldn't be found.
     */
    @Override
    public Puzzle getByEditId(String editId) {
        return puzzleRepository.findByPuzzleEditId(editId);
    }

    /**
     * Save the provided Puzzle.
     *
     * @param puzzle the puzzle to save
     * @return the puzzle, possibly modified as a result of the save operation
     */
    @Override
    public Puzzle save(Puzzle puzzle) {
        puzzle.setPuzzleEditId(IdGenerator.generate());
        puzzle = puzzleRepository.save(puzzle);
        return puzzle;
    }

}
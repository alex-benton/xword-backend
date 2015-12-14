package xword.puzzle.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Puzzle;
import xword.puzzle.repository.PuzzleRepository;

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

}
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

    public Puzzle get(String id) {
        return puzzleRepository.findOne(id);
    }

}
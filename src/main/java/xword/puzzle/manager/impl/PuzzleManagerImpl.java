package xword.puzzle.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Puzzle;
import xword.puzzle.repository.PuzzleRepository;
import xword.util.IdGenerator;

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
        if (id == null) {
            return null;
        }
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
        if (StringUtils.isBlank(editId)) {
            return null;
        }
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
        if (puzzle == null) {
            return null;
        }

        if (StringUtils.isBlank(puzzle.getPuzzleEditId())) {
            puzzle.setPuzzleEditId(IdGenerator.generate());
        }

        if (puzzle.getCreatedDate() == null) {
            puzzle.setCreatedDate(System.currentTimeMillis());
        }

        puzzle.setModifiedDate(System.currentTimeMillis());

        return puzzleRepository.save(puzzle);
    }

    /**
     * Update the provided Puzzle.
     *
     * @param puzzle the puzzle to update
     * @return the updated puzzle
     */
    @Override
    public Puzzle update(Puzzle puzzle) {
        Puzzle existing = this.getByEditId(puzzle.getPuzzleEditId());

        if (existing != null) {
            existing.setBoard(puzzle.getBoard());
            existing.setClues(puzzle.getClues());
            existing.setMetadata(puzzle.getMetadata());
            return this.save(existing);
        } else {
            return this.save(puzzle);
        }
    }

    /**
     * Patch an existing Puzzle with fields from the provided Puzzle.
     *
     * @param puzzle the puzzle to patch
     * @return the patched puzzle
     */
    @Override
    public Puzzle patch(Puzzle puzzle) {
        if (puzzle == null) {
            return null;
        }

        Puzzle existing = this.getByEditId(puzzle.getPuzzleEditId());

        if (existing == null) {
            return null;
        }

        if (puzzle.getBoard() != null) {
            existing.setBoard(puzzle.getBoard());
        }

        if (puzzle.getClues() != null) {
            existing.setClues(puzzle.getClues());
        }

        if (puzzle.getMetadata() != null) {
            if (existing.getMetadata() == null) {
                existing.setMetadata(puzzle.getMetadata());
            } else {
                if (puzzle.getMetadata().getAuthor() != null) {
                    existing.getMetadata().setAuthor(puzzle.getMetadata().getAuthor());
                }
                if (puzzle.getMetadata().getDescription() != null) {
                    existing.getMetadata().setDescription(puzzle.getMetadata().getDescription());
                }
                if (puzzle.getMetadata().getDifficulty() != null) {
                    existing.getMetadata().setDifficulty(puzzle.getMetadata().getDifficulty());
                }
                if (puzzle.getMetadata().getEditor() != null) {
                    existing.getMetadata().setEditor(puzzle.getMetadata().getEditor());
                }
                if (puzzle.getMetadata().getSource() != null) {
                    existing.getMetadata().setSource(puzzle.getMetadata().getSource());
                }
                if (puzzle.getMetadata().getTitle() != null) {
                    existing.getMetadata().setTitle(puzzle.getMetadata().getTitle());
                }
            }
        }

        return this.save(existing);
    }

}
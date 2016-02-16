package xword.puzzle.util;

import xword.puzzle.objects.Box;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Contains static helper functions to convert boards from boxes to strings and vice-versa.
 *
 * @author alex
 */
public class PuzzleHelper {

    /**
     * Convert a V2 board of Boxes into a V1 board of string values.
     *
     * @param board the source board to convert
     * @return a List<List<String>> with values corresponding to the values of the source board
     */
    public static List<List<String>> convertV2Board(List<List<Box>> board) {
        return mapV2Board(board, (Box::getValue));
    }

    /**
     * Convert a V1 board of Strings into an obfuscated V2 board.
     *
     * @param board the source board to convert
     * @return a List<List<Box>> with obfuscated values (Box value == null if no value, " " if value exists)
     */
    public static List<List<Box>> obfuscateV1Board(List<List<String>> board) {
        return mapV1Board(board, string -> string == null ? null : " ");
    }

    /**
     * Convert a V1 board of Strings into a V2 board.
     *
     * @param board the source board to convert
     * @return a V2 board
     */
    public static List<List<Box>> convertV1Board(List<List<String>> board) {
        return mapV1Board(board, string -> string == null ? null : string);
    }

    /**
     * Obfuscate a V2 board.
     *
     * @param board the source board to obfuscate
     * @return a List<List<Box>> with obfuscated values (Box value == null if no value, " " if value exists)
     */
    public static List<List<Box>> obfuscateV2Board(List<List<Box>> board) {
        if (board == null) {
            return null;
        }

        return board.stream().map(
                row -> row.stream().map(
                        (box) -> {
                            Box b = new Box();
                            b.setAttributes(box.getAttributes());
                            b.setValue(box.getValue() == null ? null : " ");
                            return b;
                        }
                ).collect(Collectors.toList())
        ).collect(Collectors.toList());
    }


    /**
     * Helper function to map a V2 board (List<List<Box>>) into a V1 board (List<List<String>>).
     *
     * @param board the board to map
     * @param valueConverter a function to apply to the value
     * @return the mapped board
     */
    private static List<List<String>> mapV2Board(List<List<Box>> board, Function<Box, String> valueConverter) {
        if (board == null) {
            return null;
        }

        return board.stream().map(row -> row.stream().map(valueConverter::apply).collect(Collectors.toList())).collect(Collectors.toList());
    }

    /**
     * Helper function to map a V1 board (List<List<String>>) into a V2 board (List<List<Box>>).
     *
     * @param board the board to map
     * @param valueConverter a function to apply to the value
     * @return the mapped board
     */
    private static List<List<Box>> mapV1Board(List<List<String>> board, Function<String, String> valueConverter) {
        if (board == null) {
            return null;
        }

        return board.stream().map(
                row -> row.stream().map(
                        (string) -> {
                            Box b = new Box();
                            b.setAttributes(null);
                            b.setValue(valueConverter.apply(string));
                            return b;
                        }
                ).collect(Collectors.toList())
        ).collect(Collectors.toList());
    }
}

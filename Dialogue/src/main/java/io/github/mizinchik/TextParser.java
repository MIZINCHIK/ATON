package io.github.mizinchik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Parser for the dialogue.
 */
public class TextParser {
    private static final String text = "script.txt";

    /**
     * Parses the dialogue line from a script file.
     *
     * @param result list of lines
     * @throws IOException something occurred during the IO
     */
    public static void parseScript(List<Line> result) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                Objects.requireNonNull(Line.class.getClassLoader().getResource(text)).getFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    Line newLine = new Line(parts[0], parts[1]);
                    result.add(newLine);
                }
            }
        }
    }
}

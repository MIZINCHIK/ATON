package io.github.mizinchik;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple script-reading game.
 *
 * @author MIZINCHIK
 */
public class Dialogue {
    private final List<Line> lines;

    /**
     * Runs a sample game.
     *
     * @param args console
     */
    public static void main(String[] args) {
        Dialogue dialogue = new Dialogue();
        ExecutorService threadPool = Executors.newFixedThreadPool(6);
        threadPool.submit(new ThespianThread("Chandler", dialogue));
        threadPool.submit(new ThespianThread("Joey", dialogue));
        threadPool.submit(new ThespianThread("Monica", dialogue));
        threadPool.submit(new ThespianThread("Phoebe", dialogue));
        threadPool.submit(new ThespianThread("Rachel", dialogue));
        threadPool.submit(new ThespianThread("Ross", dialogue));
        threadPool.shutdown();
    }

    /**
     * Creates a game from the script file.
     */
    public Dialogue() {
        lines = new ArrayList<>();
        try {
            TextParser.parseScript(lines);
        } catch (IOException e) {
            throw new RuntimeException("Script file troubles");
        }
    }

    /**
     * Pops the next line in the queue if the actor's name is alright.
     * Null otherwise.
     *
     * @param name of the actor who wants to read the line
     * @return Line to read if the name is correct
     */
    public Line nextLine(String name) {
        synchronized (lines) {
            if (lines.isEmpty() || !lines.get(0).name().equals(name)) {
                return null;
            }
            return lines.remove(0);
        }
    }

    /**
     * Checks whether the game is over (i.e. all lines
     * are said).
     *
     * @return true if lines is empty
     */
    public boolean isEmpty() {
        return lines.isEmpty();
    }
}
package io.github.mizinchik;

/**
 * Represents an actor in a play.
 *
 * @author MIZINCHIK
 */
public class ThespianThread implements Runnable {
    private final String name;
    private final Dialogue dialogue;

    /**
     * Creates an actor from the name and the dialogue instance.
     *
     * @param name of the actor
     * @param dialogue which the actor participates in
     */
    public ThespianThread(String name, Dialogue dialogue) {
        this.name = name;
        this.dialogue = dialogue;
    }

    /**
     * Runs the dialogue.
     */
    @Override
    public void run() {
        while (!dialogue.isEmpty()) {
            Line nextLine = dialogue.nextLine(this.name);
            if (nextLine != null) {
                System.out.println(name + ":" + nextLine.line());
            }
        }
    }
}

package task;

public class Deadline extends Task {
    private String dueTime;

    public Deadline(String description, String due) {
        super(description);
        this.dueTime = due;
    }

    public Deadline(String description, String due, boolean isDone) {
        super(description, isDone);
        this.dueTime = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due: " + dueTime + ")";
    }

    @Override
    public String serialize() {
        return "D | " + super.serialize() + " | " + dueTime; // assuming that the dueTime does not contain "|"
    }
}

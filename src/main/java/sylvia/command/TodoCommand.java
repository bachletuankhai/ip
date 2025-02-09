package sylvia.command;

import sylvia.SylviaException;
import sylvia.task.Task;
import sylvia.task.Todo;

/**
 * Represents a command to add a simple todo.
 */
public class TodoCommand extends AddTaskCommand {
    public static final String MANUAL = "Usage: todo <description>\n\n" + "Adds a simple todo to the task list.\n"
            + "The description cannot be empty.\n\n" + "Example: todo read book\n\n" + "Aliases: td";

    public TodoCommand(String body) {
        super(body);
    }

    /**
     * Gets the manual for the todo command.
     *
     * @return The manual for the todo command.
     */
    public static String getManual() {
        return MANUAL;
    }

    @Override
    protected Task getTaskFromDescription(String description) throws SylviaException {
        if (description.isEmpty()) {
            throw new SylviaException("The description of a todo cannot be empty.",
                    "Sorry, you need to tell me what you want to add. I can't add empty tasks.");
        }
        return new Todo(description);
    }
}

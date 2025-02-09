package sylvia;

import sylvia.command.Command;
import sylvia.command.CommandParser;
import sylvia.configuration.Info;
import sylvia.state.ProgramState;
import sylvia.storage.Storage;
import sylvia.storage.StorageLoadException;
import sylvia.storage.StorageSaveException;
import sylvia.task.TaskList;
import sylvia.ui.ErrorResponse;
import sylvia.ui.Response;
import sylvia.ui.UI;

/**
 * Represents the main class of the bot.
 */
public class Sylvia {
    private static final String DATA_FILE_PATH = Info.DATA_FILE_PATH;

    private Storage storage;
    private TaskList list;
    private UI ui;
    private ProgramState state;
    private CommandParser parser;

    /**
     * Constructs a new chatbot instance. The chatbot will load data from the
     * default data file. If the data file does not exist, a new data file will be
     * created.
     *
     * @param ui The user interface controller of the bot.
     */
    public Sylvia(UI ui) {
        this.parser = new CommandParser();
        this.ui = ui;
        this.state = new ProgramState();
        this.storage = new Storage(DATA_FILE_PATH);
        try {
            list = storage.load();
        } catch (StorageLoadException e) {
            ui.showBotError(e);
            list = new TaskList();
        }
    }

    /**
     * Runs a command and returns the response from the bot.
     *
     * @param commandString The command to run.
     * @return The response from the bot.
     */
    public Response runCommand(String commandString) {
        Command command;
        try {
            command = Command.parse(commandString, parser);
            String response = command.execute(list, state);
            if (state.isExit()) {
                ui.exit();
            }
            return new Response(response);
        } catch (SylviaException e) {
            return new ErrorResponse(e.getBotMessage());
        }
    }

    /**
     * Exits the bot. The data will be saved to the data file.
     */
    public void exit() {
        assert state.isExit() : "The bot should be in the exit state.";
        try {
            storage.save(list);
        } catch (StorageSaveException e) {
            ui.showBotError(e);
        }
    }
}

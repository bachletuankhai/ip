package sylvia.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sylvia.state.ProgramState;
import sylvia.task.TaskList;

public class ExitCommandTest {
    @Test
    public void execute_exitCommand_setsProgramStateToExit() {
        // Arrange
        ExitCommand exitCommand = new ExitCommand();
        TaskList taskList = new TaskList();
        ProgramState programState = new ProgramState();

        // Act
        String response = exitCommand.execute(taskList, programState);

        // Assert
        assertEquals("Cya!!", response);
        assertEquals(true, programState.isExit());
    }
}

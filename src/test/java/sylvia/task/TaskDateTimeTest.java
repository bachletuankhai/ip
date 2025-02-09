package sylvia.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDateTimeTest {
    @Test
    public void parse_validDateTime_returnsTaskDateTime() throws SylviaDateTimeParseException {
        // Arrange
        String dateTimeString = "2020-08-25 18:00";

        // Act
        TaskDateTime dateTime = new TaskDateTime(dateTimeString);

        // Assert
        assertEquals("Tue, Aug 25 2020 18:00", dateTime.toString());
    }

    @Test
    public void parse_invalidDateTime_throwsSylviaDateTimeParseException() {
        // Arrange
        String dateTimeString = "2020-08-25 18:00:00";

        // Act & Assert
        assertThrows(SylviaDateTimeParseException.class, () -> new TaskDateTime(dateTimeString));
    }

    @Test
    public void serialize_validTaskDateTime_returnsSerializedString() throws SylviaDateTimeParseException {
        // Arrange
        TaskDateTime dateTime = new TaskDateTime("2020-08-25 18:00");

        // Act
        String serializedString = dateTime.serialize();

        // Assert
        assertEquals("2020-08-25 18:00", serializedString);
    }
}

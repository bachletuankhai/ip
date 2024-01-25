package Task;

public class DeadlineCommand extends Command {
  private String taskDescription;
  private String dueTime;

  public DeadlineCommand(String body) {
    super(body);
    String[] parts = body.split("/by", 2);
    this.taskDescription = parts[0].trim();
    this.dueTime = parts[1].trim();
  }

  public boolean execute(TaskList list) {
    Task task = new Deadline(taskDescription, dueTime);
    list.addTask(task);
    System.out.println("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
    return true;
  }
}

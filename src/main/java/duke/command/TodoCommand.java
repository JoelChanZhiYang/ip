package duke.command;

import duke.exception.NoActionException;
import duke.exception.SaveFileException;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class TodoCommand extends Command {
    private String action;
    public TodoCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoActionException, SaveFileException {
        if (action.length() == 0) {
            throw new NoActionException("Command 'todo' requires a task action");
        }
        Task newTask = new Todo(action);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks);
        storage.save(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoCommand) {
            TodoCommand otherTask = (TodoCommand) obj;
            return otherTask.action.equals(this.action);
        }
        return false;
    }
}

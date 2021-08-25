package duke.command;

import duke.exception.NoActionException;
import duke.exception.SaveFileException;

import duke.task.Task;

import duke.task.Todo;
import duke.util.TaskList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoCommandTest {
    @Test
    public void execute_goodInput_createTodo() throws SaveFileException, NoActionException {
        TodoCommand command = new TodoCommand("Test Action");
        TaskListStub taskListStub = new TaskListStub();
        command.execute(taskListStub, new UiStub(), new StorageStub());
        assertEquals(taskListStub.getTask(), new Todo("Test Action"));
    }

    @Test
    public void execute_noAction_throwNoActionException() {
        TodoCommand command = new TodoCommand("");
        TaskListStub taskListStub = new TaskListStub();
        assertThrows(NoActionException.class, () -> {
            command.execute(taskListStub, new UiStub(), new StorageStub());
        });

    }

    private class TaskListStub extends duke.util.TaskList {
        Task task;

        @Override
        public void add(Task task) {
            this.task = task;
        }

        public Task getTask() {
            return this.task;
        }
    }

    private class UiStub extends duke.util.Ui {
        @Override
        public void showTaskAdded(Task newTask, TaskList tasks) {
            return;
        }
    }

    private class StorageStub extends duke.util.Storage {
        public StorageStub() {
            super("");
        }

        @Override
        public void save(TaskList tasks) {
            return;
        }
    }
}
package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toSaveFormat_methodCall_saveFormat() {
        assertEquals((new Todo("Test Action")).toSaveFormat(), "[T]||false||Test Action");
    }

    @Test
    public void toString_methodCall_saveFormat() {
        assertEquals((new Todo("Test Action")).toString(), "[T][ ] Test Action");
    }

    @Test
    public void complete_methodCall_changeCompleteStatus() {
        Todo todo = new Todo("Test Action");
        todo.complete();
        assertEquals(todo.isComplete(), true);
    }
}
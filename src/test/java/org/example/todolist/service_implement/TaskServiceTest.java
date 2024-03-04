package org.example.todolist.service_implement;

import org.example.todolist.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;
    @Test
    void getAllByTodolist() {
    }

    @Test
    void getAllByTodolistAndState() {
    }

    @Test
    void find() {
    }

    @Test
    void create() {
    }




    @Test
    void delete() {
    }
}
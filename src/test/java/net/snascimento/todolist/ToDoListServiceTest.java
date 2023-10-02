package net.snascimento.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import net.snascimento.todolist.entity.Todo;
import net.snascimento.todolist.repository.TodoRepository;
import net.snascimento.todolist.service.TodoService;

@SpringBootTest
class ToDoListServiceTest {

	@Mock
	private TodoRepository todoRepository;

	@InjectMocks
	private TodoService todoService;


	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		when(todoRepository.save(todo)).thenReturn(todo);

		var todoTest = new ArrayList<>();
		//todoTest.add(todoService.create(todo));

		assertNotNull(todoTest);
    todoTest.forEach(System.out::println);
		assertEquals(1, todoTest.size());
	
	}

}
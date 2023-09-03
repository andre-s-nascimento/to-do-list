package net.snascimento.todolist;

import net.snascimento.todolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ToDoListApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		webTestClient.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(3)
				.jsonPath("$[2].nome").isEqualTo(todo.getNome())
				.jsonPath("$[2].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[2].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[2].prioridade").isEqualTo(todo.getPrioridade());

	}

	@Test
	void testCreateTodoFailure() {
		webTestClient.post()
				.uri("/todos")
				.bodyValue(
						new Todo("", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testListTodos(){
		webTestClient
				.get()
				.uri("/todos")
				.exchange()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(2);
	}

	@Test
	void testDeleteSuccess(){
		webTestClient
				.delete()
				.uri("/todos/1001")
				.exchange()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(2);
	}

	@Test
	void testDeleteFailure(){
		webTestClient
				.delete()
				.uri("/todos/5001")
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testUpdateSuccess(){
		var todoUpdate = new Todo(1002L, "Tarefa UPDATE 2", "Tarefa UPDATE 002", true, 2);
		webTestClient
				.put()
				.uri("/todos/1002")
				.bodyValue(todoUpdate)
				.exchange()
				.expectBody()
				.jsonPath("$[1].nome").isEqualTo(todoUpdate.getNome())
				.jsonPath("$[1].descricao").isEqualTo(todoUpdate.getDescricao())
				.jsonPath("$[1].realizado").isEqualTo(todoUpdate.isRealizado())
				.jsonPath("$[1].prioridade").isEqualTo(todoUpdate.getPrioridade());
	}
	
	@Test
	void testUpdateFailure(){
		var todoUpdate = new Todo(5002L, "Tarefa UPDATE 5", "Tarefa UPDATE 005", true, 2);
		webTestClient
				.put()
				.uri("/todos/5002")
				.bodyValue(todoUpdate)
				.exchange()
				.expectStatus().isBadRequest();
		
	}

}

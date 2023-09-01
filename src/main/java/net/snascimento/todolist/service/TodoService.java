package net.snascimento.todolist.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import net.snascimento.todolist.entity.Todo;
import net.snascimento.todolist.exception.BadRequestException;
import net.snascimento.todolist.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Long id, Todo todo) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Todo %d não existe! ".formatted(id));
        });

        return list();
    }

    public List<Todo> delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> todoRepository.delete(existingTodo), () -> {
            throw new BadRequestException("Todo %d não existe! ".formatted(id));
        });
        return list();
    }

}

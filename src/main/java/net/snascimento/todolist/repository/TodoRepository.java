package net.snascimento.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.snascimento.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}

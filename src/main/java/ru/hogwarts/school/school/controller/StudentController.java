package ru.hogwarts.school.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.school.model.Student;
import ru.hogwarts.school.school.service.StudentService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable long id) {
        return studentService.getById(id);
    }

    @GetMapping
    public Collection<Student> getAll(@RequestParam(value = "age", required = false)Integer age) {
        return Optional.ofNullable(age)
                .map(studentService::getAllByAge)
                .orElseGet(studentService::getALl);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable long id, @RequestBody Student student ) {
        return studentService.update(id, student);
    }

    @GetMapping("/{id}")
    public Optional<Student> deleteById(@PathVariable long id) {
        return studentService.deleteById(id);
    }

}

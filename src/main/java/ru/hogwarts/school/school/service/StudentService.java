package ru.hogwarts.school.school.service;

import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private static long counterId;
    private final Map<Long, Student> students = new HashMap<>();

    public Student add(Student student) {
        student.setId(counterId++);
        students.put(student.getId(), student);
        return student;
    }

    public Student update(long id, Student student) {
        if (students.containsKey(id)) {
            students.replace(id, student);
        }
        return null;
    }

    public Optional<Student> getById(long id) {
        return Optional.ofNullable(students.get(id));
    }

    public Collection<Student> getALl() {
        return Collections.unmodifiableCollection(students.values());
    }

    public Optional<Student> deleteById(long id) {
        return Optional.ofNullable(students.remove(id));
    }

    public Collection<Student> getAllByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge()== age)
                .collect(Collectors.toList());
    }
}

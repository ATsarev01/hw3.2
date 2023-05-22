package ru.hogwarts.school.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.school.model.Faculty;
import ru.hogwarts.school.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private static long counterId;
    private final Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty add(Faculty faculty) {
        faculty.setId(counterId++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty update(long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            faculties.replace(id, faculty);
        }
        return null;
    }

    public Optional<Faculty> getById(long id) {
        return Optional.ofNullable(faculties.get(id));
    }

    public Collection<Faculty> getALl() {
        return Collections.unmodifiableCollection(faculties.values());
    }

    public Optional<Faculty> deleteById(long id) {
        return Optional.ofNullable(faculties.remove(id));
    }

    public Collection<Faculty> getAllByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}

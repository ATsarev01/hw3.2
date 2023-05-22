package ru.hogwarts.school.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.school.model.Faculty;
import ru.hogwarts.school.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;
@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = FacultyController.this.facultyService;
    }

    @GetMapping("/{id}")
    public Optional<Faculty> getById(@PathVariable long id) {
        return facultyService.getById(id);
    }

    @GetMapping
    public Collection<Faculty> getAll(@RequestParam(value = "color", required = false)String color) {
        return Optional.ofNullable(color)
                .map(facultyService::getAllByColor)
                .orElseGet(facultyService::getALl);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable long id, @RequestBody Faculty faculty ) {
        return facultyService.update(id, faculty);
    }

    @GetMapping("/{id}")
    public Optional<Faculty> deleteById(@PathVariable long id) {
        return facultyService.deleteById(id);
    }
}

package com.chernyak.exerciseservice.controller;

import com.chernyak.exerciseservice.entity.Exercise;
import com.chernyak.exerciseservice.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class ExerciseController {

    ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping(value = "/today")
    public ResponseEntity<Exercise> getExerciseOnDate(@RequestParam String goalId) {
        return new ResponseEntity<Exercise>(exerciseService.getExerciseForToday(goalId), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Exercise> getExerciseOnDate(@PathVariable String id, @RequestBody Exercise exercise) {
        return new ResponseEntity<Exercise>(exerciseService.update(id, exercise), HttpStatus.OK);
    }
}

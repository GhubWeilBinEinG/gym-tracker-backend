package htw_berlin.gym_tracker.api;

import htw_berlin.gym_tracker.entity.Exercise;
import htw_berlin.gym_tracker.entity.Workout;
import htw_berlin.gym_tracker.service.ExerciseService;
import htw_berlin.gym_tracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:5173", "https://gym-tracker-frontend-nsbu.onrender.com/"
})


@RestController
public class GymTrackerController {

    @GetMapping

    public String test(){
        return "Hello World";
    }

    @Autowired
    ExerciseService exerciseService;
    @Autowired
    WorkoutService workoutService;

    @CrossOrigin
    @PostMapping("/exercises")
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.save(exercise);
    }

    @CrossOrigin
    @GetMapping("/exercises/{id}")
    public Exercise getExercise(@PathVariable Long id) {
        return exerciseService.get(id);
    }

    @CrossOrigin
    @GetMapping(path="/exercises")
    public List<Exercise> getAllExercises() {
        return List.of(
                new Exercise("Hip Thrust", "Glutes"),
                new Exercise("Squats", "Quads")
        );
    }

    @CrossOrigin
    @PostMapping("/workout")
    public Workout createWorkout(@RequestBody Workout workout) {
        return workoutService.save(workout);
    }

    @CrossOrigin
    @GetMapping("/workout/{id}")
    public Workout getWorkout(@PathVariable Long id) {
        return workoutService.get(id);
    }

    @GetMapping(path="/workout")
    public List<Workout> getAllWorkout() {
        return workoutService.getAll();
    }
}
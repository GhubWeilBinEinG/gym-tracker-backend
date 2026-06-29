package htw_berlin.gym_tracker.api;

import htw_berlin.gym_tracker.entity.Exercise;
import htw_berlin.gym_tracker.entity.ExerciseSearchResult;
import htw_berlin.gym_tracker.entity.Workout;
import htw_berlin.gym_tracker.entity.WorkoutExercise;
import htw_berlin.gym_tracker.service.ExerciseSearchService;
import htw_berlin.gym_tracker.service.ExerciseService;
import htw_berlin.gym_tracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:5173", "https://gym-tracker-frontend-nsbu.onrender.com/" })
@RestController
public class GymTrackerController {

    @Autowired
    ExerciseService exerciseService;
    @Autowired
    WorkoutService workoutService;
    @Autowired
    ExerciseSearchService exerciseSearchService;

    // --- Exercises (local DB) ---

    @PostMapping("/exercises")
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.save(exercise);
    }

    @GetMapping("/exercises/{id}")
    public Exercise getExercise(@PathVariable Long id) {
        return exerciseService.get(id);
    }

    @GetMapping("/exercises")
    public List<Exercise> getAllExercises() {
        return List.of(
                new Exercise("Hip Thrust", "Glutes"),
                new Exercise("Squats", "Quads")
        );
    }

    @GetMapping
    public String test() {
        return "Hello World";
    }


    @GetMapping("/exercises/search")
    public List<ExerciseSearchResult> searchExercises(
            @RequestParam(required = false) String muscle,
            @RequestParam(required = false) String name) {
        if (name != null && !name.isBlank()) {
            return exerciseSearchService.searchByName(name);
        }
        return exerciseSearchService.searchByMuscle(muscle != null ? muscle : "chest");
    }

    @PostMapping("/workout")
    public Workout createWorkout(@RequestBody Workout workout) {
        return workoutService.save(workout);
    }

    @GetMapping("/workout/{id}")
    public Workout getWorkout(@PathVariable Long id) {
        return workoutService.get(id);
    }

    @GetMapping("/workout")
    public List<Workout> getAllWorkout() {
        return workoutService.getAll();
    }

    @PostMapping("/workout/{id}/exercises")
    public Workout addExerciseToWorkout(@PathVariable Long id, @RequestBody WorkoutExercise exercise) {
        return workoutService.addExercise(id, exercise);
    }
}

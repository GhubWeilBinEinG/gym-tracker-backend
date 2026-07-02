package htw_berlin.gym_tracker.api;

import htw_berlin.gym_tracker.entity.ExerciseSearchResult;
import htw_berlin.gym_tracker.entity.LoggedExercise;
import htw_berlin.gym_tracker.entity.Workout;
import htw_berlin.gym_tracker.dto.AddExerciseDTO;
import htw_berlin.gym_tracker.repository.WorkoutSessionRepo;
import htw_berlin.gym_tracker.service.ExerciseSearchService;
import htw_berlin.gym_tracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import htw_berlin.gym_tracker.entity.WorkoutSession;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class GymTrackerController {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    ExerciseSearchService exerciseSearchService;

    @Autowired
    WorkoutSessionRepo workoutSessionRepo;

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
    public Workout createWorkout(@RequestBody Workout workout, @AuthenticationPrincipal Jwt jwt) {
        return workoutService.save(workout, jwt.getSubject());
    }

    @GetMapping("/workout/{id}")
    public Workout getWorkout(@PathVariable Long id) {
        return workoutService.get(id);
    }

    @GetMapping("/workout")
    public List<Workout> getAllWorkout(@AuthenticationPrincipal Jwt jwt) {
        return workoutService.getAll(jwt.getSubject());
    }

    @PostMapping("/workout/{id}/exercises")
    public Workout addExerciseToWorkout(@PathVariable Long id, @RequestBody AddExerciseDTO dto) {
        return workoutService.addExercise(id, dto);
    }

    @DeleteMapping("/workout/{id}")
    public ResponseEntity<Void> deleteWorkout(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt jwt) {
        workoutService.delete(id, jwt.getSubject());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/workout/{workoutId}/exercises/{exerciseId}")
    public ResponseEntity<Workout> removeExerciseFromWorkout(
            @PathVariable Long workoutId,
            @PathVariable Long exerciseId,
            @AuthenticationPrincipal Jwt jwt) {
        Workout updated = workoutService.removeExercise(workoutId, exerciseId, jwt.getSubject());
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/workout/{id}/sessions")
    public ResponseEntity<Void> saveWorkoutSession(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload,
            @AuthenticationPrincipal Jwt jwt) {

        try {
            Workout workout = workoutService.get(id);

            WorkoutSession session = new WorkoutSession();
            session.setWorkoutId(id);
            session.setWorkoutName(workout != null ? workout.getWorkoutName() : "Aktivität");
            session.setUserSubject(jwt.getSubject());
            session.setDate(java.time.Instant.now());

            Object exercisesObj = payload.get("exercises");
            List<Map<String, Object>> incomingExercises = new ArrayList<>();

            if (exercisesObj instanceof List<?>) {
                for (Object item : (List<?>) exercisesObj) {
                    if (item instanceof Map<?, ?>) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> map = (Map<String, Object>) item;
                        incomingExercises.add(map);
                    }
                }
            }

            List<LoggedExercise> loggedExercises = new ArrayList<>();

            for (Map<String, Object> exMap : incomingExercises) {
                LoggedExercise loggedEx = new LoggedExercise();

                Object exIdObj = exMap.get("exerciseId");
                if (exIdObj != null) {
                    loggedEx.setExerciseId(Long.valueOf(exIdObj.toString()));
                }

                loggedEx.setName(exMap.get("name") != null ? exMap.get("name").toString() : "Unbekannt");

                Object setsObj = exMap.get("sets");
                if (setsObj instanceof List<?>) {
                    List<?> setsList = (List<?>) setsObj;
                    StringBuilder jsonBuilder = new StringBuilder("[");

                    for (int i = 0; i < setsList.size(); i++) {
                        Object setItem = setsList.get(i);
                        if (setItem instanceof Map<?, ?>) {
                            Map<?, ?> setMap = (Map<?, ?>) setItem;

                            // Holt die Werte und nutzt 0 als Fallback, falls mal was leer ist
                            Object weight = setMap.get("actualWeight");
                            Object reps = setMap.get("actualReps");

                            jsonBuilder.append("{")
                                    .append("\"actualWeight\":").append(weight != null ? weight : 0)
                                    .append(",")
                                    .append("\"actualReps\":").append(reps != null ? reps : 0)
                                    .append("}");

                            if (i < setsList.size() - 1) {
                                jsonBuilder.append(",");
                            }
                        }
                    }
                    jsonBuilder.append("]");
                    loggedEx.setSets(jsonBuilder.toString());
                } else {
                    loggedEx.setSets("[]");
                }

                loggedExercises.add(loggedEx);
            }

            session.setExercises(loggedExercises);
            workoutSessionRepo.save(session);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            System.err.println("Fehler beim Speichern der Session: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/history/sessions")
    public List<WorkoutSession> getPastSessions(@AuthenticationPrincipal Jwt jwt) {
        return workoutSessionRepo.findByUserSubjectOrderByDateDesc(jwt.getSubject());
    }
}
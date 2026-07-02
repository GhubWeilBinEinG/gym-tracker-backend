package htw_berlin.gym_tracker.api;

import htw_berlin.gym_tracker.entity.CustomExercise;
import htw_berlin.gym_tracker.service.CustomExerciseService;
import htw_berlin.gym_tracker.service.ExerciseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custom-exercises")
public class CustomExerciseController {

    @Autowired
    CustomExerciseService service;
    @Autowired
    ExerciseSearchService searchService;


    @GetMapping
    public List<CustomExercise> getAll(@AuthenticationPrincipal Jwt jwt) {
        return service.getAllForUser(jwt.getSubject());
    }

    @PostMapping
    public CustomExercise create(
            @RequestBody CustomExercise exercise,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return service.save(exercise, jwt.getSubject());

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomExercise> update(
            @PathVariable Long id,
            @RequestBody CustomExercise exercise,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(service.update(id, exercise, jwt.getSubject())); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        service.delete(id, jwt.getSubject());
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping("/search")
    public List<CustomExercise> searchCustom(
            @RequestParam String name,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return service.searchByName(jwt.getSubject(), name);
    }
}

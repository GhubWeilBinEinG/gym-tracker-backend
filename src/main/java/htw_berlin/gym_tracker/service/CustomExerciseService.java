package htw_berlin.gym_tracker.service;

import htw_berlin.gym_tracker.entity.CustomExercise;
import htw_berlin.gym_tracker.repository.CustomExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomExerciseService {

    @Autowired
    CustomExerciseRepo repo;

    // Nur die Exercises des angefragten Users
    public List<CustomExercise> getAllForUser(String userId) {
        return repo.findByUserId(userId);
    }

    public CustomExercise save(CustomExercise exercise, String userId) {
        exercise.setUserId(userId);
        return repo.save(exercise);
    }
    public List<CustomExercise> searchByName(String userId, String name) {
        return repo.findByUserId(userId)
                .stream()
                .filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public CustomExercise update(Long id, CustomExercise updated, String userId) {
        CustomExercise existing = repo.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new RuntimeException("Übung nicht gefunden oder gehört dir nicht"));

        existing.setName(updated.getName());
        existing.setMuscle(updated.getMuscle());
        existing.setEquipment(updated.getEquipment());
        existing.setInstructions(updated.getInstructions());

        return repo.save(existing);
    }

    public void delete(Long id, String userId) {
        CustomExercise existing = repo.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new RuntimeException("Übung nicht gefunden oder gehört dir nicht"));
        repo.delete(existing);
    }
}

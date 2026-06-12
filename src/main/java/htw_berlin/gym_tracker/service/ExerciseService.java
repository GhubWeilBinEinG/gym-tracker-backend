package htw_berlin.gym_tracker.service;

import htw_berlin.gym_tracker.entity.Exercise;
import htw_berlin.gym_tracker.repository.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepo exerciseRepo;

    public Exercise save(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    public Exercise get(Long id) {
        return exerciseRepo.findById(id).orElseThrow(() -> new RuntimeException("Exercise nicht gefunden!"));
    }
}

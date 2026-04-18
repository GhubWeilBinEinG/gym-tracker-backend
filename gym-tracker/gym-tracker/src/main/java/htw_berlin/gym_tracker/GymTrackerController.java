package htw_berlin.gym_tracker;

import org.apache.catalina.users.GenericRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class GymTrackerController {

    @GetMapping

    public String test(){
        return "Hello World";
    }

    @GetMapping(path="/GymTracker")
    public List<Exercise> getAllExercises() {
        return List.of(
                new Exercise("Hip Thrust", "Glutes"),
                new Exercise("Squats", "Quads")
        );
    }

}

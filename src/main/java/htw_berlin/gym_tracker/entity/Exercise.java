package htw_berlin.gym_tracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String muscleGroup;

    public Exercise() {}

    public Exercise(String name, String muscleGroup) {
        this.name = name;
        this.muscleGroup= muscleGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name, String muscleGroup) {
        this.name = name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}

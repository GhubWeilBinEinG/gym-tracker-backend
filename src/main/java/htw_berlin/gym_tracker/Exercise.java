package htw_berlin.gym_tracker;

public class Exercise {
    private String name;
    private String muscleGroup;

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

package htw_berlin.gym_tracker;

public class Workout {
    private String workoutName;
    private String name;
    private int reps;
    private int sets;


    public Workout(String workoutName, String name, int reps, int sets) {
        this.workoutName= workoutName;
        this.name = name;
        this.reps= reps;
        this.sets= sets;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}

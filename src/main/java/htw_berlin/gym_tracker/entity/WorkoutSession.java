package htw_berlin.gym_tracker.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workoutId;
    private String workoutName;
    private String userSubject; // Für die Zuordnung zum eingeloggten User
    private Instant date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id")
    private List<LoggedExercise> exercises;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getWorkoutId() { return workoutId; }
    public void setWorkoutId(Long workoutId) { this.workoutId = workoutId; }
    public String getWorkoutName() { return workoutName; }
    public void setWorkoutName(String workoutName) { this.workoutName = workoutName; }
    public String getUserSubject() { return userSubject; }
    public void setUserSubject(String userSubject) { this.userSubject = userSubject; }
    public Instant getDate() { return date; }
    public void setDate(Instant date) { this.date = date; }
    public List<LoggedExercise> getExercises() { return exercises; }
    public void setExercises(List<LoggedExercise> exercises) { this.exercises = exercises; }
}
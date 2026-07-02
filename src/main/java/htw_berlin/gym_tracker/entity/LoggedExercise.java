package htw_berlin.gym_tracker.entity;

import jakarta.persistence.*;

@Entity
public class LoggedExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long exerciseId;
    private String name;

    @Column(columnDefinition = "TEXT") // Speichert das Sets-Array einfach als JSON-String ab
    private String sets;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getExerciseId() { return exerciseId; }
    public void setExerciseId(Long exerciseId) { this.exerciseId = exerciseId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSets() { return sets; }
    public void setSets(String sets) { this.sets = sets; }
}
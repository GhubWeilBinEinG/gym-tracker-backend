package htw_berlin.gym_tracker.entity;

import jakarta.persistence.*;

@Entity
public class CustomExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String muscle;
    private String equipment;

    @Column(length = 1000)
    private String instructions;

    private String userId;

    public CustomExercise() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMuscle() { return muscle; }
    public void setMuscle(String muscle) { this.muscle = muscle; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
package SYSC4806.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


/**
 * Category is a model class that represents a group of learning outcomes.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<LearningOutcome> learningOutcomes = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LearningOutcome> getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(List<LearningOutcome> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }

    public void addLO(LearningOutcome lo) {
        learningOutcomes.add(lo);
    }

    public void removeLO(LearningOutcome lo) {
        learningOutcomes.remove(lo);
    }
}

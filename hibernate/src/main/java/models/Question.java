package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    @OneToMany(mappedBy = "question")
    private List<QuestionItem> questionItems;
    public Question() {
        questionItems = new ArrayList<>();
    }
}

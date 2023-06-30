package ar.edu.unlam.tallerweb1.domain.conditionScore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ConditionScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Integer> historico;

    public Long getId() {
        return id;
    }

    public List<Integer> getHistorico() {
        return historico;
    }

    public ConditionScore() {
        this.historico = new ArrayList<>();
        this.historico.add(50);
    }

    public Integer getLastCS() {
        return this.historico.get(0);
    }

    public void setHistorico(List<Integer> historico) {
        this.historico = historico;
    }

    public void addNewPoint(int newCS) {
        this.historico.add(0,newCS);
    }
}

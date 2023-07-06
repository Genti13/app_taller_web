package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.conditionScore.RepositorioConditionScore;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioConditionScore")
public class RepositorioConditionScoreImpl implements RepositorioConditionScore{

    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioConditionScoreImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarCS(ConditionScore conditionScore) {
        Session session = sessionFactory.getCurrentSession();
        session.save(conditionScore);

    }
}

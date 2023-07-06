package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.dieta.RepositorioDieta;
import ar.edu.unlam.tallerweb1.domain.rutina.Rutina;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("repositorioDieta")
public class RepositorioDietaImp implements RepositorioDieta {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDietaImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Rutina> get() {
        return new ArrayList<Rutina>();
    }

    @Override
    public List<Rutina> getRutinas() {
        return null;
    }

    @Override
    public List<Dieta> buscarDietaConIDUsuario(Long idUser) {

        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Dieta.class).createAlias("usuario", "u").add(Restrictions.eq("u.id", idUser)).list();

    }

    @Override
    public List<Dieta> buscarDietaConMail(String mail) {
        Session session = sessionFactory.getCurrentSession();

        Usuario usuario = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", mail)).uniqueResult();

        List<Dieta> dietas = new ArrayList<>(usuario.getDieta());

        return dietas;
    }

    @Override
    public Usuario getUsuario(String mail) {
        Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", mail)).uniqueResult();
    }

    @Override
    public Dieta getUltimaDieta(String mail) {
        Session session = sessionFactory.getCurrentSession();

        Usuario usuario = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", mail)).uniqueResult();
        Dieta dieta = usuario.getDieta().get(usuario.getDieta().size() -1);
        return dieta;
    }

    @Override
    public List<Dieta> getAllDietas(String mail) {
        Session session = sessionFactory.getCurrentSession();

        Usuario usuario = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", mail)).uniqueResult();
        List<Dieta> dieta = usuario.getDieta();
        return new ArrayList<Dieta>(dieta);
    }

    @Override
    public List<Dieta> getDietasRecomendadas() {
        return null;
    }
}

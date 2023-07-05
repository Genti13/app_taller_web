package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.estados.Estado;
import ar.edu.unlam.tallerweb1.domain.estados.RepositorioEstado;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("repositorioEstado")
public class RepositorioEstadoImpl implements RepositorioEstado {
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioEstadoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Estado buscarEstado(String estado) {
		final Session session = sessionFactory.getCurrentSession();
		return (Estado) session.createCriteria(Estado.class)
				.add(Restrictions.eq("nombre", estado))
				.uniqueResult();
	}
	@Override
	public void guardar(Estado estado) {
		sessionFactory.getCurrentSession().save(estado);
	}

}

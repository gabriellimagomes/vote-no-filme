package br.com.gabriel.filme.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.Ranking;
import br.com.gabriel.filme.models.Usuario;
import br.com.gabriel.filme.models.Voto;

@Component
public class VotoRepositoryImpl extends Repository<Voto, Long> implements
		VotoRepository {

	VotoRepositoryImpl(Session session) {
		super(session);
	}

	@Override
	public void votar(Voto voto) {
		session.save(voto);
	}

	@Override
	public List<Ranking> rankingGeral() {
		List<Ranking> rankings = new ArrayList<Ranking>();

		montaRankingPelaQtVotos(rankings);

		montaRankingFilmesSemVotos(rankings);

		return rankings;
	}

	@Override
	public List<Ranking> rankingPorUsuario(Usuario usuario) {
		List<Ranking> rankings = new ArrayList<Ranking>();
		
		montaRankingPelaQtVotos(rankings, usuario);

		montaRankingFilmesSemVotos(rankings, usuario);

		return rankings;
	}

	@SuppressWarnings("unchecked")
	private void montaRankingFilmesSemVotos(List<Ranking> rankings,	Usuario usuario) {

		List<Filme> filmesSemVotos = session
				.createQuery("from Filme as filme where filme.id not in (select filmeVotado from Voto as voto where voto.usuario.id = :usuarioId)")
				.setLong("usuarioId", usuario.getId())
				.list();

		montaListaFilmesSemVotos(rankings, filmesSemVotos);
	}

	@SuppressWarnings("unchecked")
	private void montaRankingFilmesSemVotos(List<Ranking> rankings) {

		List<Filme> filmesSemVotos = session
				.createQuery("from Filme as filme where filme.id not in (select filmeVotado from Voto)")
				.list();

		montaListaFilmesSemVotos(rankings, filmesSemVotos);
	}

	private void montaListaFilmesSemVotos(List<Ranking> rankings,
			List<Filme> filmesSemVotos) {
		Ranking ranking;
		for (Filme filmeSemVoto : filmesSemVotos) {
			ranking = new Ranking(filmeSemVoto, 0L);
			rankings.add(ranking);
		}
	}

	@SuppressWarnings("unchecked")
	private void montaRankingPelaQtVotos(List<Ranking> rankings, Usuario usuario) {

		List<Object[]> filmesComQtVotos = session
				.createCriteria(Voto.class)
				.add(Restrictions.eq("usuario.id", usuario.getId()))
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.groupProperty("filmeVotado"))
								.add(Projections.count("filmeVotado").as("qt_votos")))
				.addOrder(Order.desc("qt_votos")).list();

		montaListaRanking(rankings, filmesComQtVotos);
	}

	private void montaListaRanking(List<Ranking> rankings,
			List<Object[]> filmesComQtVotos) {
		Filme filmeVotado;
		Long quantidadeVotos;
		Ranking ranking;
		for (Object[] filmeComQtVotos : filmesComQtVotos) {
			filmeVotado = (Filme) filmeComQtVotos[0];
			quantidadeVotos = (Long) filmeComQtVotos[1];
			ranking = new Ranking(filmeVotado, quantidadeVotos);
			rankings.add(ranking);
		}
	}

	@SuppressWarnings("unchecked")
	private void montaRankingPelaQtVotos(List<Ranking> rankings) {

		List<Object[]> filmesComQtVotos = session
				.createCriteria(Voto.class)
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.groupProperty("filmeVotado"))
								.add(Projections.count("filmeVotado").as("qt_votos")))
				.addOrder(Order.desc("qt_votos")).list();

		montaListaRanking(rankings, filmesComQtVotos);
	}

}

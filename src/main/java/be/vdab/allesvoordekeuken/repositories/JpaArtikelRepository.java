package be.vdab.allesvoordekeuken.repositories;

import be.vdab.allesvoordekeuken.domain.Artikel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaArtikelRepository implements ArtikelRepository
{
    EntityManager manager;
    //CONSTRUCTORS
    public JpaArtikelRepository(EntityManager manager)
    {
        this.manager = manager;
    }

    //METHODS
    @Override
    public Optional<Artikel> findById(long id)
    {
        return Optional.ofNullable(manager.find(Artikel.class,id));
    }

    @Override
    public void create(Artikel artikel) {
        manager.persist(artikel);
    }

    @Override
    public List<Artikel> findByNaamContains(String woord) {
        return manager.createNamedQuery("Artikel.findByNaamContains", Artikel.class)
                .setParameter("zoals", '%' + woord + '%').getResultList();
    }

    @Override
    public int verhoogPrijs(BigDecimal percentage) {
        BigDecimal factor =
                BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
        return manager.createNamedQuery("Artikel.verhoogPrijs")
                .setParameter("factor", factor)
                .executeUpdate();
    }


}
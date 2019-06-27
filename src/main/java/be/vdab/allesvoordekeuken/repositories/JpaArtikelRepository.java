package be.vdab.allesvoordekeuken.repositories;

import be.vdab.allesvoordekeuken.domain.Artikel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public Optional<Artikel> findById(int id)
    {
        return Optional.ofNullable(manager.find(Artikel.class,id));
    }
}
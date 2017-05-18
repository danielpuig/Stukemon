/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pokemon;
import entities.Trainer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author daniel.puig
 */
@Stateless
public class StukemonEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    public boolean insertTrainer(Trainer t) {
        if (!existsTrainer(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }
        return false;
    }
    
    public boolean existsTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer trainerEncontrado = em.find(Trainer.class, t.getName());
        em.close();
        return trainerEncontrado != null;
    }
    
    public boolean insertPoke(Pokemon p) {
        if (!existsPoke(p)) {
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }
        return false;
    }
    
    public boolean existsPoke(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        Pokemon pokeEncontrado = em.find(Pokemon.class, p.getName());
        em.close();
        return pokeEncontrado != null;
    }
    
    public List<Trainer> selectAllTrainersWithPoke(){
        List<Trainer> listTrainer = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
        List<Trainer> retTrainer = new ArrayList<>();
        for(Trainer currentTrainer: listTrainer){
            if(currentTrainer.getPokemonCollection().size()<6){
                retTrainer.add(currentTrainer);
            }
        }
        return retTrainer;
    }
    
    public Trainer findTrainer(String name) {
        return (Trainer) emf.createEntityManager().createNamedQuery("Trainer.findByName").setParameter("name", name).getSingleResult();
    }
    
    public boolean hasSixPokemon(Trainer t) {
        List<Pokemon> res = emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", t).getResultList();
        return (res.size() <= 5);
    }
    
}

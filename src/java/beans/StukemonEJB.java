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
    
    public boolean deletePoke(String name) {
        Pokemon p = findPokemon(name);
        EntityManager em = emf.createEntityManager();
        Pokemon pokeEncontrado = em.find(Pokemon.class, p.getName());
        if (existsPoke(pokeEncontrado)) {

            em.remove(pokeEncontrado);
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
    
    public List<Trainer> selectAllTrainers() {
        return emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
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
    
    public List<Trainer> selectAllTrainersWithPokeAndPotion(){
        List<Trainer> listTrainer = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
        List<Trainer> retTrainer = new ArrayList<>();
        for(Trainer currentTrainer: listTrainer){
            if(currentTrainer.getPokemonCollection().size()>0 && currentTrainer.getPotions()>0){
                retTrainer.add(currentTrainer);
            }
        }
        return retTrainer;
    }
    
    public List<Pokemon> selectAllPokemon() {
        return emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
    }
    
    public Trainer findTrainer(String name) {
        return (Trainer) emf.createEntityManager().createNamedQuery("Trainer.findByName").setParameter("name", name).getSingleResult();
    }
    
    public Pokemon findPokemon(String name) {
        return (Pokemon) emf.createEntityManager().createNamedQuery("Pokemon.findByName").setParameter("name", name).getSingleResult();
    }
    
    public boolean hasSixPokemon(Trainer t) {
        List<Pokemon> res = emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", t).getResultList();
        return (res.size() <= 5);
    }
    
    public List<Pokemon> getPokemonsByTrainer(String name) {
        Trainer t = findTrainer(name);
        return emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", t).getResultList();
    }
    
    public void updatePoke(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        em.merge(p);
    }

    public void updateTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        em.merge(t);
    }
    
}

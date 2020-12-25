package org.example.database.daos;

import org.example.database.entities.Mutasi;
import org.example.database.entities.Nasabah;

import com.google.gson.Gson;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class NasabahDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public NasabahDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = entityManager.getTransaction();
    }


    public List<Nasabah> getAllNsb(){
        return entityManager.createQuery("SELECT a FROM Nasabah a", Nasabah.class).getResultList();
    }


    public Nasabah findUser(String username) {
        try {
            String select = "SELECT a FROM Nasabah a WHERE username=:username";
            Query query = entityManager.createQuery(select, Nasabah.class);
            query.setParameter("username", username);
            System.out.println("debug : "+ (Nasabah)query.getSingleResult());
            return (Nasabah) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }


    public Integer getSaldo(String username) {
        try {
            String select = "SELECT balance FROM Nasabah WHERE username=:username";
            Query query = entityManager.createQuery(select);
            query.setParameter("username", username);
            System.out.println("debug : "+ (Integer)query.getSingleResult());
            return (Integer) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public List<Mutasi> getMutasi(String accountnumber) {
        try { //from Nasabah nya nanti diganti dulu ke model mutasi
            String select = "SELECT a FROM Mutasi a WHERE accountnumber=:accountnumber";
            Query query = entityManager.createQuery(select, Mutasi.class);
            query.setParameter("accountnumber", accountnumber);
            System.out.println("debug : "+ (List<Mutasi>)query.getResultList());
            return (List<Mutasi>) query.getResultList();
        } catch (NoResultException e){
            return null;
        }
    }


    public void persist(String nasabahString){
        Nasabah nasabah = new Gson().fromJson(nasabahString, Nasabah.class);
        entityManager.persist(nasabah);
    }

//    public void update(String nsbString){
//        Nasabah currentNasabah = new Gson().fromJson(nsbString, Nasabah.class);
//        Nasabah nextNasabah = entityManager.find(Nasabah.class, currentNasabah.getId());
//        nextNasabah.setFullname(currentNasabah.getFullname());
//        nextNasabah.setPhonenumber(currentNasabah.getPhonenumber());
//        nextNasabah.setAddress(currentNasabah.getAddress());
//        nextNasabah.setPassword(currentNasabah.getPassword());
//        nextNasabah.setStatus(currentNasabah.getStatus());
//        entityManager.merge(nextNasabah);
//    }


    public void doLogin(String id) {
        Nasabah nasabah = entityManager.find(Nasabah.class, Long.valueOf(id));
            Boolean statusLogin = true;
            nasabah.setIsLogin(statusLogin);
    }


    public void doLogout(String id) {
        Nasabah nasabah = entityManager.find(Nasabah.class, Long.valueOf(id));
        Boolean statusLogin = false;
        nasabah.setIsLogin(statusLogin);
    }

    public boolean isRegistered(String nasabahString) {
        List<Nasabah> listAllNasabah = getAllNsb();
        Nasabah nasabah = new Gson().fromJson(nasabahString, Nasabah.class);
        boolean registered = false;
        for (Nasabah obj : listAllNasabah){
            if(obj.getUsername().equalsIgnoreCase(nasabah.getUsername()) || obj.getAccountnumber().equals(nasabah.getAccountnumber())){
                registered = true;
            }
        }
        return registered;
    }

    public boolean checkPassword(String nasabahString){
        List<Nasabah> listAllUser = getAllNsb();
        Nasabah nasabah = new Gson().fromJson(nasabahString, Nasabah.class);
        boolean canLogin = false;
        for (Nasabah obj : listAllUser){
            if(obj.getUsername().equalsIgnoreCase(nasabah.getUsername())){
                if(obj.getPassword()==nasabah.getPassword()){
                    canLogin = true;
                }
            }
        }
        return canLogin;
    }

}
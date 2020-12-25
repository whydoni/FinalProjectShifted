package org.example.database.application;

import org.example.database.rabbitmq.DatabaseRecvMq;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DatabaseJpaMqMain {

    public static EntityManager entityManager = Persistence
            .createEntityManagerFactory("nasabah-unit")
            .createEntityManager();

    public static DatabaseRecvMq receiveMq = new DatabaseRecvMq(entityManager);

    public static void main(String[] args) {
        try{
            System.out.println(" [*] Waiting for messages...");
            //DB Consumer for CRUD
            receiveMq.regisNasabah();
            receiveMq.findByUsername();
            receiveMq.getSaldoNsb();
            receiveMq.getMutasi();

            //DB Consumer for Session isLogin
            receiveMq.loginNasabah();
            receiveMq.logoutNasabah();
        }catch (Exception e){
            System.out.println("ERROR! on DatabaseMqMain : " + e);
        }
    }

}

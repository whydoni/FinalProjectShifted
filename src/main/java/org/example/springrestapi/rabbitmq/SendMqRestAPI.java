package org.example.springrestapi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class SendMqRestAPI {


    // Register Data Nasabah
    public static void registerNasabah(String nasabah) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("createDataNasabah", false, false, false, null);
            channel.basicPublish("", "createDataNasabah", null, nasabah.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + nasabah + "'");
        }
    }


    //Find Data Nasabah by Username
    public static void findNasabah(String username) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("findDataNasabah", false, false, false, null);
            channel.basicPublish("", "findDataNasabah", null, username.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + username + "'");
        }
    }

    //Get Saldo Nasabah by Username
    public static void getSaldoNasabah(String username) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("getSaldoNasabah", false, false, false, null);
            channel.basicPublish("", "getSaldoNasabah", null, username.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + username + "'");
        }
    }

    //Get Rekening Nasabah by Username
    public static void getRekeningNasabah(String username) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("getRekeningNasabah", false, false, false, null);
            channel.basicPublish("", "getRekeningNasabah", null, username.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + username + "'");
        }
    }

    //Get List Mutasi Nasabah by accountnumber
    public static void getMutasi(String accountnumber) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("getMutasi", false, false, false, null);
            channel.basicPublish("", "getMutasi", null, accountnumber.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + accountnumber + "'");
        }
    }


    //Login Nasabah
    public static void loginNasabah(String nasabahString) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("doLoginNasabah", false, false, false, null);
            channel.basicPublish("", "doLoginNasabah", null, nasabahString.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + nasabahString + "'");
        }
    }


    //Logout Nasabah
    public static void logoutNasabah() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String strings ="Request Logout";
            channel.queueDeclare("doLogoutNasabah", false, false, false, null);
            channel.basicPublish("", "doLogoutNasabah", null, strings.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + strings + "'");
        }
    }
}

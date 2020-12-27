package org.example.springrestapi.controller;

import com.google.gson.Gson;
import org.example.database.entities.Nasabah;
import org.example.springrestapi.SpringbootDummyBankMain;
import org.example.springrestapi.rabbitmq.*;


import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestAPIController {
    public final RecvMqRestAPI restApiReceive = new RecvMqRestAPI();
    public final Logger logger = LoggerFactory.getLogger(SpringbootDummyBankMain.class);


    //--------------------------Register Nasabah-------------------------------------
    @RequestMapping(value = "/mbanking/api/register/", method = RequestMethod.POST)
    public ResponseEntity<?> registerNsb(@RequestBody Nasabah nasabah) {
        try {
            SendMqRestAPI.registerNasabah(new Gson().toJson(nasabah));
            restApiReceive.receiveFromDatabase();
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("Error on Register :  " + e);
        }
        return new ResponseEntity<>("Success, data created! \n", HttpStatus.OK);
    }


    //--------------------------Find Data Nasabah by Username-------------------------------------
    @RequestMapping(value = "/mbanking/api/nasabah/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> findNsb(@PathVariable("username") String username) {
        try {
            SendMqRestAPI.findNasabah(username);
            return new ResponseEntity<>(restApiReceive.RecvDataUser(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error on get Data Nasabah");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }


    //--------------------------Get Saldo Nasabah by Username-------------------------------------
    @RequestMapping(value = "/mbanking/api/saldo/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getSaldoNsb(@PathVariable("username") String username) {
        try {
            SendMqRestAPI.getSaldoNasabah(username);
            return new ResponseEntity<>(restApiReceive.RecvSaldoUser(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error on get Saldo");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }


    //--------------------------Get Mutasi Nasabah by Username-------------------------------------
    @RequestMapping(value = "/mbanking/api/mutasi/{accountnumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getMutasi(@PathVariable("accountnumber") String accountnumber) {
        try {
            SendMqRestAPI.getMutasi(accountnumber);
            return new ResponseEntity<>(restApiReceive.RecvMutasiUser(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error on get Mutasi");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }


    //--------------------------Login Nasabah-------------------------------------
    @RequestMapping(value = "/mbanking/api/login/", method = RequestMethod.POST)
    public ResponseEntity<?> loginNsb(@RequestBody Nasabah nasabah) {
        try {
            SendMqRestAPI.loginNasabah(new Gson().toJson(nasabah));
            return new ResponseEntity<>(restApiReceive.RecvLoginMsg(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error on Login");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }


    //--------------------------Logout Nasabah-------------------------------------
    @RequestMapping(value = "/mbanking/api/logout/", method = RequestMethod.POST)
    public ResponseEntity<?> logutNsb() {
        try {
            SendMqRestAPI.logoutNasabah();
            return new ResponseEntity<>(restApiReceive.RecvLogoutMsg(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error on Logout");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

    }


//    @RequestMapping(value = "/account", method = RequestMethod.POST)
//    public ResponseEntity getVirtualAccount(@RequestBody RequestVa body){
//        try {
//            String response = "";
//            String request = new Gson().toJson(body);
//            System.out.println(request);
//            customMessage = new CustomMessage("getVirtualAccount", "Send Request To Bank Server", request);
//            String message = new Gson().toJson(customMessage);
//            producer.send(message);
//            response = consumer.getResponse();
//            System.out.println(response);
//            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
//            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/topup", method = RequestMethod.POST)
//    public ResponseEntity doTopUp(@RequestBody RequestVa body){
//        try {
//            String response = "";
//            String request = new Gson().toJson(body);
//            customMessage = new CustomMessage("topUp", "Send Request To Bank Server", request);
//            String message = new Gson().toJson(customMessage);
//            producer.send(message);
//            response = consumer.getResponse();
//            System.out.println(response);
//            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
//            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/mutasi", method = RequestMethod.POST)
//    public ResponseEntity getVirtualAccount(@RequestBody RequestMutasi body){
//        try {
//            String response = "";
//            String request = new Gson().toJson(body);
//            customMessage = new CustomMessage("getMutasi", "Send Request To Bank Server", request);
//            String message = new Gson().toJson(customMessage);
//            producer.send(message);
//            response = consumer.getResponse();
//            System.out.println(response);
//            CustomMessage responseMessage = new Gson().fromJson(response, CustomMessage.class);
//            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.client;

import com.akursat.model.Users;
import com.akursat.ws.UserService;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author akursat
 */
public class TestClient {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8080/JaxWSDemo/user-service?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.akursat.com/", "UserServiceImplService");

        Service service = Service.create(url, qname);

        UserService service1 = service.getPort(UserService.class);
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20100520");
        System.out.println("INSERT");
        service1.createUser("ali", "ali@gmail.com", "123456", date, (short) 1);
        System.out.println("UPDATE");
        service1.updateUser("ali", "ali_2@yahoo.com");
        System.out.println("READ");
        System.out.println("Getting..:" + service1.find("fxswq43"));
        System.out.println("DELETE");
        service1.deleteUser("test");
 

        System.out.println("Data displayed");
    }
}

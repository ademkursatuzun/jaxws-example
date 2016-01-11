/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.ws;

import com.akursat.model.Users;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author akursat
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface UserService {


    //SELECT//
    @WebMethod(operationName = "select")
    public String find(String username);

    /*
    @WebMethod(operationName = "selectAll")
    public List<Users> findAll();
     */
    //INSERT//
    @WebMethod(operationName = "add")
    public void createUser(String username, String email, String password, Date birthday, Short sex);

    //DELETE//
    @WebMethod(operationName = "delete")
    public void deleteUser(String username);

    //UPDATE//
    @WebMethod(operationName = "update")
    public void updateUser(String username, String email);

}

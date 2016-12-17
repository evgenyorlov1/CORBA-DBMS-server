package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class User implements Serializable {
    public String username;
    public String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;        
    }
}

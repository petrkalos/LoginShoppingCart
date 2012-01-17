/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kalos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petros Kalos
 */
public class DBManager {

    private static ArrayList<User> list = new ArrayList();
    /*
    private static final String filename = "usrdatabase.bin";
    
    public static int saveUser(User user) {  
        try {
            if(searchUser(user)) return 0;
            BufferedWriter out = new BufferedWriter(new FileWriter(filename,true));
            out.write(user.toString());
            out.close();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static boolean searchUser(User user) {
        User tmpUser;
        String obj;
        File file = new File(filename);

        if (file.exists()) {
            try {
                BufferedReader in =  new BufferedReader(new FileReader(filename));
                while ((obj = in.readLine()) != null) {
                    String[] str = obj.split("@");
                    if(str[0].equals(user.getUsername())) return true;
                }
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    */
    
    public static int saveUser(User user){
        if(searchUser(user.getUsername())!=null) return 0;
        list.add(user);
        return 1;
    }
    
    public static User authUser(String username,String password){
        User user = searchUser(username);
        if(user!=null){
            if(user.getPassword().equals(password)) return user;
        }
        return null;
    }
    
    public static User authGoogle(String username,String password){
        if(GoogleLogin.login(username, password)){
            User user = searchUser(username);
            if(user == null){
                user = new User(username,password);
                DBManager.saveUser(user);
            }
            return user;
        }
        return null;
    }
    
    private static User searchUser(String username){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUsername().equals(username)){
                return list.get(i);
            }
        }
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kalos;

import java.io.Serializable;

/**
 *
 * @author Petros Kalos
 */
public class User implements Serializable {

    private String username;
    private String password;
    private ProductList pl = new ProductList("/home/inf2008/kalos/public_html/productslist.xml");

    User(String username, String password) {
        this.username = username;
        this.password = password;
        EmailManager.send(username);
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public boolean equals(User user){
        return this.username.equals(user.getUsername());
    }
    
    public ProductList getProductList(){
        return pl;
    }
    
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        buffer.append(username);
        buffer.append("@");
        buffer.append(password);
        buffer.append("\n");

        return buffer.toString();
    }
    
}

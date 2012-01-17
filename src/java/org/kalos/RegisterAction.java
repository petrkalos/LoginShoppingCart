/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kalos;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{
    
    private String username;
    private String password;
    
    @Override
    public String execute() {
        int re = DBManager.saveUser(new User(username,password));
        if( re == 1){
            return "success";
        }else{
            super.addActionError("User not available");
            return "error";
        }
    }
    
    @Override
    public void validate() {
        if (getUsername().length() == 0) {
            addFieldError("username", "Email is required");
        }
        if (EmailManager.validate(username)) {
            addFieldError("username", "Provide a valid email address");
            System.out.println("is not a mail");
        }else{
            System.out.println("is mail");
        }
        if (getPassword().length() == 0) {
            addFieldError("password", "Password is required");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

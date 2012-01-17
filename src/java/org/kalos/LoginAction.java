/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kalos;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

    private String username;
    private String password;
    private String loginservice;
    
    private User user;
    
    @Override
    public String execute() {
        DBManager.saveUser(new User("admin","123"));
        if(loginservice.equals("Google")){
            user = DBManager.authGoogle(username, password);
        }else if(loginservice.equals("Native")){
            user = DBManager.authUser(username, password);
        }
        if (user!=null){
            return "success";
        } else {
            super.addActionError("Wrong compination of username/password");
            return "error";
        }
    }
    
    @Override
    public void validate() {
        if (getUsername().length() == 0) {
            addFieldError("username", "Username is required");
            
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
    
    public String getLoginservice() {
        return loginservice;
    }

    public void setLoginservice(String loginservice) {
        this.loginservice = loginservice;
    }
    
    public User getUser(){
        return user;
    }

    
}

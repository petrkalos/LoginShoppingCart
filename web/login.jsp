<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://code.google.com/p/jcaptcha4struts2/taglib/2.0" prefix="jcaptcha" %>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
    <s:head />

    <body>
        <h2>Struts 2 - Login Application</h2>
        
        <s:actionerror/>
        <s:form action="login.action" method="post">
            <s:radio name="loginservice" label="Login with: " list="{'Google','Native'}"  value="'Google'" />
            <s:textfield name="username" key="label.username" size="20" />
            <s:password name="password" key="label.password" size="20" />
            
            <s:submit method="execute" key="label.login" align="center" />
        </s:form>
        <form action="register.jsp" method="GET">
            <div>
                <input type="submit" value="Register">
            </div>
        </form>
    </body>
</html>


<%@page import= "org.kalos.*,java.util.ArrayList,java.util.Iterator,org.w3c.dom.Element" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="f"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>

<html>
    <head>
        <script>        
            var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object        
        
            function getXMLObject(){
                var xmlHttp = false;
            
                try {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
                }
                catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
                    }
                    catch (e2) {
                        xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
                    }
                }
                if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
                    xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
                }
                if(xmlHttp == false) alert("XML");
                return xmlHttp;  // Mandatory Statement returning the ajax object created
            
            }
        
            function handleServerResponse() {
                if (xmlhttp.readyState == 4) {
                    if(xmlhttp.status == 200) {
                        var response = new String(xmlhttp.responseText);
                        var split_res = response.split("@");
                    
                        if(split_res[0] == "input"){
                            document.getElementById("res-"+split_res[1]).innerHTML = split_res[2]; //Update the HTML Form elementresponse.getWriter().println(conv.convert(from, to, value));
                            document.getElementById("res-total").innerHTML = split_res[3]; //Update the HTML Form elementresponse.getWriter().println(conv.convert(from, to, value));
                        }else if(split_res[0] == "onload"){
                            document.getElementById("res-total").innerHTML = split_res[1];
                            for (var i=2;i<split_res.length;i+=2){
                                document.getElementById("res-"+split_res[i]).innerHTML = split_res[i+1];
                            }
                        }
                    }
                    else {
                        alert("Error during AJAX call. Please try again");
                    }
                }
            }
 
            function ajaxFunction(str) {
                if(xmlhttp) {
                    xmlhttp.open("POST","ShoppingCartServlet",true);
                    xmlhttp.onreadystatechange  = handleServerResponse;
                    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xmlhttp.send(str);
                }
            }
                
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        Hello : <s:property value="user.username" /><br>
        <c:if test='${user.username == null}'>
            <c:redirect url="login.jsp"> </c:redirect>
        </c:if>
        
        <s:set scope="session" var="productslist" value="user.productList" />
        <table border ="1" cellpadding="3">
            <tbody>
                <tr>
                    <td align="LEFT"><strong>Item</strong></td>
                    <td align="RIGHT"><strong>Price</strong></td>
                    <td align="RIGHT"><strong>Quantity</strong></td>
                    <td align="RIGHT"><strong>  Total  </strong></td>
                </tr>


                <c:forEach var="product" items="${productslist.list}">
                    <tr>
                        <td align="LEFT"><strong> ${product.name} </strong></td>
                        <td align="RIGHT"><strong> ${product.price} </strong></td>
                        <td align="RIGHT">
                            <input type="text"
                                   value="${product.amount}"
                                   size="5" id="${product.name}"
                                   onblur="ajaxFunction(
                                       'action=input'+
                                           '&product=${product.name}'+
                                           '&amount='+${product.name}.value
                                   );
                                   "/> 
                        <td align="RIGHT" id="res-${product.name}">${product.total}</td>
                    </tr>
                </c:forEach>


                <tr>
                    <td align="LEFT"><strong></strong></td>
                    <td align="RIGHT"><strong></strong></td>
                    <td align="RIGHT"><strong></strong></td>
                    <td height="20" width="100" id="res-total" align="RIGHT">
                        <strong>${productslist.cost}</strong>
                    </td>
                </tr>
            </tbody>
        </table>
        <INPUT TYPE="BUTTON" VALUE="#" ONCLICK="window.location.href='https://github.com/petrkalos/ShoppingCart'"> 
        <INPUT TYPE="BUTTON" VALUE="?" ONCLICK="window.location.href='https://docs.google.com/document/d/13Q-rBESOOX1Qhy9sv17ym3Qn3d3yJCwIxwWms4rC-UM/edit'">
    </body>
</html>

package org.kalos;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Agellous
 */
public class ProductList {

    private ArrayList<Product> list = new ArrayList();
    DecimalFormat df = new DecimalFormat(".##");
    private double totalcost;

    public ProductList() {
        list.add(new Product("Cafe", 18.5));
        list.add(new Product("Sugar", 6.95));
        list.add(new Product("Water", 1.29));
        //readXML("productlist.xml");
        this.totalcost = 0;      
    }

    public ProductList(String path) {
        readXML(path);
        this.totalcost = 0;
    }

    public ArrayList getList() {
        return this.list;
    }

    public double totalCost() {
        this.totalcost = 0;
        for (int i = 0; i < list.size(); i++) {
            this.totalcost += list.get(i).calc();
        }
        try {
            return df.parse(df.format(this.totalcost)).doubleValue();
        } catch (ParseException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double productCost(String product) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(product)) {
                try {
                    return df.parse(df.format(list.get(i).calc())).doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public void setProductAmount(String product, int amount) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(product)) {
                list.get(i).setAmount(amount);
            }
        }
    }

    public void getCookies(HttpServletResponse response) {
        Cookie ck;
        for (int i = 0; i < list.size(); i++) {
            ck = new Cookie(i + "product", list.get(i).getName());
            response.addCookie(ck);
            ck = new Cookie(i + "amount", Integer.toString(list.get(i).getAmount()));
            response.addCookie(ck);
        }
    }

    public final void readXML(String path) {

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(path));

            // normalize text representation
            doc.getDocumentElement().normalize();

            NodeList listOfProducts = doc.getElementsByTagName("product");
            

            for (int s = 0; s < listOfProducts.getLength(); s++) {


                Node productNode = listOfProducts.item(s);
                if (productNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element productElement = (Element) productNode;

                    //-------
                    NodeList nameList = productElement.getElementsByTagName("name");
                    Element nameElement = (Element) nameList.item(0);

                    NodeList textFNList = nameElement.getChildNodes();
                    
                    String product_name = ((Node) textFNList.item(0)).getNodeValue().trim();
                    //-------
                    NodeList priceList = productElement.getElementsByTagName("price");
                    Element priceElement = (Element) priceList.item(0);

                    NodeList textLNList = priceElement.getChildNodes();
                    String product_price = ((Node) textLNList.item(0)).getNodeValue().trim();
                    
                    list.add(new Product(product_name,Double.parseDouble(product_price)));

                }//end of if clause


            }//end of for loop with s var


        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        //System.exit (0);

    }//end of main
    
    public double getCost(){
        return this.totalcost;
    }
}

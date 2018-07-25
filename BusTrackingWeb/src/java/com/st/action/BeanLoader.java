package com.st.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BeanLoader {

    public static HashMap<String, String> beans;

    public static void readXML(URL url) {

        try {
            System.out.println("url : " + url);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            FileInputStream fis = new FileInputStream(url.getFile());
            Document doc = dBuilder.parse(fis);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("form-bean");
            System.out.println("node : " + nList);
            String name = "";
            if (fis != null && nList != null) {
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        System.out.print("name = " + eElement.getAttribute("name"));
                        System.out.print("type = " + eElement.getAttribute("type"));

                    }
                }
                fis.close();

            }

        } catch (SAXException ex) {
            Logger.getLogger(BeanLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BeanLoader.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BeanLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

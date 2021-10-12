package com.ivoronline.springboot_services_tls_twoway_soapconnection_client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

@Component
public class MyRunner implements CommandLineRunner {

  //PROPERTIES
  static String xmlFile            = "/Request.xml";
  static String serverURL          = "https://localhost:8085/Echo";

  //CLIENT TRUST STORE
  static String trustStoreName     = "ClientTrustStore.jks";
  static String trustStorePassword = "mypassword";
  static String trustStoreType     = "JKS";

  //CLIENT TRUST STORE
  static String keyStoreName       = "ClientKeyStore.jks";
  static String keyStorePassword   = "mypassword";
  static String keyStoreType       = "JKS";

  //===============================================================================
  // RUN
  //===============================================================================
  @Override
  public void run(String... args) throws Exception {

    //CREATE SOAP MESSAGE FROM XML FILE
    Document    requestXMLDocument = UtilXML.fileToDocument(xmlFile);
    SOAPMessage requestSOAPMessage = UtilSOAP.XMLDocumentToSOAPMessage(requestXMLDocument);

    //GET REQUEST FACTORY (for Two-Way TLS)
    UtilClientSOAPConnection.configureTwoWayTLS(
      trustStoreName, trustStorePassword, trustStoreType,  //One-Way TLS
      keyStoreName  , keyStorePassword  , keyStoreType     //Two-Way TLS
    );

    //SEND REQUEST
    SOAPConnectionFactory factory             = SOAPConnectionFactory.newInstance();
    SOAPConnection        connection          = factory.createConnection();
    SOAPMessage           responseSOAPMessage = connection.call(requestSOAPMessage, serverURL);

    //DISPLAY RESPONSE
    System.out.println(UtilSOAP.SOAPMessageToSOAPString(responseSOAPMessage));

  }

}

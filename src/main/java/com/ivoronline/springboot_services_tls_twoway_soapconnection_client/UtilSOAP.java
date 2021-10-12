package com.ivoronline.springboot_services_tls_twoway_soapconnection_client;

import org.w3c.dom.Document;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;

public class UtilSOAP {

  //=======================================================================================
  // XML DOCUMENT TO SOAP MESSAGE
  //=======================================================================================
  public static SOAPMessage XMLDocumentToSOAPMessage(Document document) throws Exception {

    //ADD SOAP ENVELOPE
    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
                soapMessage.getSOAPBody().addDocument(document);

    //RETURN SOAP MESSAGE
    return soapMessage;

  }

  //=======================================================================================
  // SOAP MESSAGE TO SOAP STRING
  //=======================================================================================
  public static String SOAPMessageToSOAPString(SOAPMessage soapMessage) throws Exception {

    //CONVERT SOAP MESSAGE TO OUTPUT STREAM
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                          soapMessage.writeTo(outputStream);

    //STORE RESULT INTO STRING
    String output = new String(outputStream.toByteArray());

    //RETURN STRING
    return output;

  }

}

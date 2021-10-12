package com.ivoronline.springboot_services_tls_twoway_soapconnection_client;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;

public class UtilClientSOAPConnection {

  //=======================================================================================
  // CONFIGURE TWO WAY TLS
  //=======================================================================================
  // UtilClientSOAPConnection.configureTwoWayTLS(
  //   "ClientTrustStore.jks", "mypassword", "JKS",                            //Trust Store
  //   "ClientKeyStore.jks"  , "mypassword", "JKS"                             //Key   Store
  // )
  public static void configureTwoWayTLS(
    String trustStoreName, String trustStorePassword, String trustStoreType,   //Trust Store
    String keyStoreName  , String keyStorePassword  , String keyStoreType      //Key   Store
  ) throws Exception {

    //LOAD STORES
    KeyStore            trustStore = UtilKeys.getStore(trustStoreName, trustStorePassword, trustStoreType);
    KeyStore            keyStore   = UtilKeys.getStore(keyStoreName  , keyStorePassword  , keyStoreType  );

    //CONFIGURE TRUST MANAGER FACTORY (For One-Way TLS)
    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX");
                        trustManagerFactory.init(trustStore);

    //CONFIGURE KEY MANAGER FACTORY   (For Two-Way TLS)
    KeyManagerFactory   keyManagerFactory = KeyManagerFactory.getInstance("PKIX");
                        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

    //CONFIGURE SSL CONTEXT
    SSLContext          sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

    //SET SSL SOCKET FACTORY
    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

  }

}




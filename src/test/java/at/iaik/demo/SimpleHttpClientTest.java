package at.iaik.demo;

import iaik.security.provider.IAIK;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.Security;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleHttpClientTest {
    
    private static String defaultKeystoreType;
    
    @BeforeAll
    static void init() {
        printDebug();
        
        defaultKeystoreType = Security.getProperty("keystore.type");
        // because: http://openjdk.java.net/jeps/229
        
        IAIK.addAsProvider(true);
    }
    
    static void printDebug() {
        String version = System.getProperty("java.version", "NOT SET!");
        System.out.println("Java Version:   " + version);

//        String trustStore = System.getProperty("javax.net.ssl.trustStore", "NOT SET!");
//        String trustStoreType = System.getProperty("javax.net.ssl.trustStoreType", "NOT SET!");
//        System.out.println("trustStore:     " + trustStore);
//        System.out.println("trustStoreType: " + trustStoreType);
        
        String keystoreType = Security.getProperty("keystore.type");
        System.out.println("keystore.type:  " + keystoreType);
        
        String disabledAlgorithmsTLS = Security.getProperty("jdk.tls.disabledAlgorithms");
        System.out.println("jdk.tls.disabledAlgorithms:       " + disabledAlgorithmsTLS);
        
        String disabledAlgorithmsCert = Security.getProperty("jdk.certpath.disabledAlgorithms");
        System.out.println("jdk.certpath.disabledAlgorithms:  " + disabledAlgorithmsCert);
    }
    
    @BeforeEach
    void setUp() {
    
    }
    
    @Test
    @Disabled("This won't work on JAVA newer than 1.8 ...")
    void getIAIKviaDefault() throws IOException {
        Security.setProperty("keystore.type", defaultKeystoreType);
        
        SimpleHttpClient client = new SimpleHttpClient();
        String resp = client.get("https://www.iaik.tugraz.at");
        
        assertNotNull(resp);
        assertTrue(resp.contains("Institute of Applied Information Processing and Communications"));
    }
    
    @Test
    void getIAIKviaJKS() throws IOException {
        Security.setProperty("keystore.type", "jks");
        
        SimpleHttpClient client = new SimpleHttpClient();
        String resp = client.get("https://www.iaik.tugraz.at");
        
        assertNotNull(resp);
        assertTrue(resp.contains("Institute of Applied Information Processing and Communications"));
    }
    
    @Test
    @Disabled("This won't work on JAVA newer than 1.8 ...")
    void getRTRviaDefault() throws IOException {
        Security.setProperty("keystore.type", defaultKeystoreType);
        
        SimpleHttpClient client = new SimpleHttpClient();
        String resp = client.get("https://c01.netztest.at/RMBTControlServer/testRequest");
        
        assertNotNull(resp);
        assertTrue(resp.contains("Expected request is missing."));
    }
    
    @Test
    void getRTRviaJKS() throws IOException {
        Security.setProperty("keystore.type", "jks");
        
        SimpleHttpClient client = new SimpleHttpClient();
        String resp = client.get("https://c01.netztest.at/RMBTControlServer/testRequest");
        
        assertNotNull(resp);
        assertTrue(resp.contains("Expected request is missing."));
    }
}
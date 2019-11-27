package at.iaik.demo;

import iaik.security.provider.IAIK;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    }
    
    @BeforeEach
    void setUp() {
    
    }
    
    @Test
    void getViaDefault() throws IOException {
        Security.setProperty("keystore.type", defaultKeystoreType);
        
        SimpleHttpClient client = new SimpleHttpClient();
        String resp = client.get("https://www.iaik.tugraz.at");
        
        assertNotNull(resp);
        assertTrue(resp.contains("Institute of Applied Information Processing and Communications"));
    }
    
    @Test
    void getViaJKS() throws IOException {
        Security.setProperty("keystore.type", "jks");
        
        SimpleHttpClient client = new SimpleHttpClient();
        String resp = client.get("https://www.iaik.tugraz.at");
        
        assertNotNull(resp);
        assertTrue(resp.contains("Institute of Applied Information Processing and Communications"));
    }
}
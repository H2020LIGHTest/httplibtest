package at.iaik.demo;

import iaik.security.provider.IAIK;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleHttpClientTest {
    
    private SimpleHttpClient client;
    
    @BeforeAll
    static void init() {
        IAIK.addAsProvider(true);
    }
    
    @BeforeEach
    void setUp() {
        client = new SimpleHttpClient();
    }
    
    @Test
    void get() throws IOException {
        String resp = client.get("https://www.iaik.tugraz.at");
        
        assertNotNull(resp);
        assertTrue(resp.contains("Institute of Applied Information Processing and Communications"));
    }
}
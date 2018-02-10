package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CustomObjectsTest {

    private Ontraport ontraport;

    @Before
    public void setUp() {
        ontraport = new Ontraport("123", "123");
    }

    @Test
    public void testRetrieveOne() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        assertEquals("", obj.retrieveSingle(map));
    }

    @Test(expected = RequiredParamsException.class)
    public void testRetrieveOneBadParams() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        Map<String, String> map = new HashMap<>();
        map.put("bogus", "1");
        obj.retrieveSingle(map);
    }
}

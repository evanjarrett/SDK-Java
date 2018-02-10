package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ObjectsTest {

    private Ontraport ontraport;

    @Before
    public void setUp() {
        ontraport = new Ontraport("", "");
    }

    @Test
    public void testRetrieveOne() throws RequiredParamsException {
        Objects obj = new Objects(ontraport);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "970");
        map.put("objectID", "0");
        assertEquals("", obj.retrieveSingle(map));
    }

    @Test
    public void testRetrieveMultiplePaginated() throws RequiredParamsException {
        Objects obj = new Objects(ontraport);
        HashMap<String, String> map = new HashMap<>();
        map.put("objectID", "0");
        assertEquals("", obj.retrieveMultiplePaginated(map));
    }


    @Test(expected = RequiredParamsException.class)
    public void testRetrieveOneBadParams() throws RequiredParamsException {
        Objects obj = new Objects(ontraport);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("bogus", "0");
        obj.retrieveSingle(map);
    }
}

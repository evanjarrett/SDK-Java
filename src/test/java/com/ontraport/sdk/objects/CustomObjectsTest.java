package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.RequestParams;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomObjectsTest {

    private Ontraport ontraport;

    @Before
    public void setUp() {
        ontraport = new Ontraport("123", "123");
    }

    @Test
    public void testRetrieveOne() throws RequiredParamsException {
        CustomObjects obj = ontraport.custom(10000);
        RequestParams map = new RequestParams();
        map.put("id", "10000");
        assertEquals("", obj.retrieveSingle(map));
    }

    @Test
    public void testRetrieveMultiple() throws RequiredParamsException {
        CustomObjects obj = ontraport.custom(10000);
        RequestParams map = new RequestParams();
        assertEquals("", obj.retrieveMultiple(map));
    }

    @Test(expected = RequiredParamsException.class)
    public void testRetrieveOneBadParams() throws RequiredParamsException {
        CustomObjects obj = ontraport.custom(10000);
        RequestParams map = new RequestParams();
        map.put("bogus", "10000");
        obj.retrieveSingle(map);
    }
}

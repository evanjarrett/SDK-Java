package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.CurlClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObjectsTest {

    private Ontraport ontraport;

    @Before
    public void setUp() {
        CurlClient client = mock(CurlClient.class);
        when(client.httpRequest(any(RequestParams.class), anyString(), anyString())).thenReturn(
                "{\"code\": 0, \"data\": {\"id\": \"970\", \"owner\": \"1\", \"firstname\": \"first\", \"lastname\": \"last\", \"email\": \"email@email.com\",}, \"account_id\": \"123\"}");
        ontraport = new Ontraport("123", "123");
    }

    @Test
    public void testRetrieveOne() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("id", "970");
        map.put("objectID", "0");
        assertEquals(
                "{\"code\": 0, \"data\": {\"id\": \"970\", \"owner\": \"1\", \"firstname\": \"first\", \"lastname\": \"last\", \"email\": \"email@email.com\",}, \"account_id\": \"123\"}",
                obj.retrieveSingle(map));
    }

    @Test
    public void testRetrieveMultiplePaginated() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("objectID", "0");
        assertEquals("", obj.retrieveMultiplePaginated(map));
    }


    @Test(expected = RequiredParamsException.class)
    public void testRetrieveOneBadParams() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("id", "1");
        map.put("bogus", "0");
        assertEquals("", obj.retrieveSingle(map));
    }

    @Test
    public void testRetrieveCustomObjects() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        assertEquals("", obj.retrieveCustomObjects(map));
    }
}

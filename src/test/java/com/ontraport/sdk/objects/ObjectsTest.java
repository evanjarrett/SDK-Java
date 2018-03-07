package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.ListResponse;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.URLClient;
import com.ontraport.sdk.http.UpdateResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ObjectsTest {

    private Ontraport ontraport;

    @Before
    public void setUp() {
        //when(client.httpRequest(any(RequestParams.class), anyString(), anyString())).thenReturn();
        ontraport = new Ontraport("123", "123", new URLClient());
    }

    @Test
    public void testRetrieveOne() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("id", "970");
        map.put("objectID", "0");

        SingleResponse res = obj.retrieveSingle(map);
        System.out.println(res);
    }

    @Test
    public void testRetrieveMultiplePaginated() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("objectID", "0");
        ArrayList<ListResponse> res = obj.retrieveMultiplePaginated(map);
        System.out.println(res);
    }

    @Test(expected = RequiredParamsException.class)
    public void testRetrieveOneBadParams() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("id", "1");
        map.put("ObjectID", "0");
        assertEquals("", obj.retrieveSingle(map).toString());
    }

    @Test
    public void testUpdate() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("id", "974");
        map.put("objectID", "0");
        map.put("firstname", "test");
        UpdateResponse ur = obj.update(map);
        assertEquals("", ur.toString());
    }

    @Test
    public void testRetrieveCustomObjects() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        assertEquals("", obj.retrieveCustomObjects(map).toString());
    }

    @Test
    public void test() throws RequiredParamsException {
        Objects obj = ontraport.objects();
        RequestParams map = new RequestParams();
        map.put("ids", new String[]{"970"});
        map.put("objectID", "0");
        map.put("add_names", new String[]{"SDK-Java"});
        assertEquals("", obj.addTagByName(map).toString());
    }
}

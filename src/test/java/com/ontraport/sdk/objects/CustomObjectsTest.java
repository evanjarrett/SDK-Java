package com.ontraport.sdk.objects;

import com.google.gson.Gson;
import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.ObjectInfo;
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
    public void testRetrieveCollectionInfo() throws RequiredParamsException {
        Gson gson = new Gson();
        ObjectInfo info = gson.fromJson("{\"code\":0,\"data\":{\"listFields\":[\"\",\"f1509\",\"id\",\"f1500\",\"ip_addy\",\"owner\",\"f1582\"],\"listFieldSettings\":{\"0\":{\"name\":\"\",\"width\":\"30\",\"sortDir\":\"\"},\"viewMode\":\"1\"},\"cardViewSettings\":{\"columnDisplayField\":\"\",\"sortField\":\"\",\"sortDir\":\"asc\",\"colorField\":\"\",\"colorFilter\":[],\"fields\":[\"f1509\",\"id\",\"f1500\",\"ip_addy\",\"owner\",\"f1582\"],\"displaySize\":\"mini\",\"newSettings\":true},\"viewMode\":1,\"count\":\"1\"},\"account_id\":\"22634\"}", ObjectInfo.class);
info.getData().getListFieldSettings();
        CustomObjects obj = ontraport.custom(10000);
        RequestParams map = new RequestParams();
        map.put("id", "10000");
        assertEquals("", obj.retrieveCollectionInfo(map));
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

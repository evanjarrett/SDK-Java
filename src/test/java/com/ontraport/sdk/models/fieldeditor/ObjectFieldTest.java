package com.ontraport.sdk.models.fieldeditor;

import com.google.gson.Gson;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.objects.fields.FieldType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObjectFieldTest {

    private ObjectField text_field;

    @Before
    public void setUp() {
        text_field = new ObjectField("myField", FieldType.TEXT, true, false);
    }

    @Test
    public void testGetAlias() {
        assertEquals("myField", text_field.getAlias());
    }

    @Test
    public void testGetField() {
        ObjectField test = mock(ObjectField.class);
        when(test.getField()).thenReturn("f1234");
        assertEquals("f1234", test.getField());
    }

    @Test
    public void testGetId() {
        ObjectField test = mock(ObjectField.class);
        when(test.getId()).thenReturn(1234);
        assertEquals(1234, test.getId());
    }

    @Test
    public void testToRequestParams() {
        RequestParams field_params = text_field.toRequestParams();
        assertEquals("{\"unique\":false,\"alias\":\"myField\",\"type\":\"text\",\"required\":true}",
                new Gson().toJson(field_params));
    }

    @Test
    public void testToString() {
        assertEquals("{\"unique\":false,\"alias\":\"myField\",\"type\":\"text\",\"required\":true}", text_field.toString());
    }

}

package com.ontraport.sdk.models.fieldeditor;

import com.google.gson.Gson;
import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.SectionResponse;
import com.ontraport.sdk.http.URLClient;
import com.ontraport.sdk.objects.fields.FieldType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObjectSectionTest {

    private ObjectSection section;
    private ObjectField text_field;
    private Ontraport ontraport;

    @Before
    public void setUp() {
        ontraport = new Ontraport("2_22634_", "", new URLClient());
        text_field = new ObjectField("myField", FieldType.TEXT, true, false);
        ObjectSection.Column[] columns = {new ObjectSection.Column(), new ObjectSection.Column(), new ObjectSection.Column()};
        columns[1] = new ObjectSection.Column();
        columns[1].add(text_field);
        section = new ObjectSection("My Section", "this is a description", columns);
    }

    @Test
    public void testGetFieldByAlias() {
        assertEquals(text_field, section.getFieldByAlias("myField"));
    }

    @Test
    public void testCreateFromResponse() throws RequiredParamsException {
        RequestParams map = new RequestParams();
        map.put("section", "Contact Information");
        SectionResponse field_res = ontraport.contacts().retrieveSection(map);
        ObjectSection section = ObjectSection.createFromResponse(field_res.getData());
        System.out.println(section.toString());
    }

    @Test
    public void testToRequestParams() {
        RequestParams section_params = section.toRequestParams();
        assertEquals(
            "{\"name\":\"My Section\",\"description\":\"this is a description\",\"fields\":[[],[{\"unique\":false,\"alias\":\"myField\",\"type\":\"text\",\"required\":true}],[]]}",
            new Gson().toJson(section_params)
        );
    }

    @Test
    public void testToString() {
        assertEquals(
            "{\"name\":\"My Section\",\"description\":\"this is a description\",\"fields\":[[],[{\"unique\":false,\"alias\":\"myField\",\"type\":\"text\",\"required\":true}],[]]}",
            section.toString()
        );
    }

}

package com.ontraport.sdk.models.rules;

import java.util.HashMap;
import java.util.Map;

public enum Condition {

    // Customer Relationship Management (CRM)
    SUBSCRIBED_TO_CAMPAIGN_BEFORE_AFTER_DATE("campaignbuilder_subscription_date_is_val"),
    BEEN_ON_CAMPAIGN_FOR_TIMEFRAME("Been_on_campaign_for_timeframe"),
    OBJECT_PAUSED_RESUMED_ON_CAMPAIGN("paused_or_active_on_camp"),
    BEEN_ON_SEQUENCE_FOR_TIMEFRAME("been_on_campaignbuilder_for_timeframe"),
    SUBSCRIBED_TO_SEQUENCE_BEFORE_AFTER_DATE("Date_of_subscription_to_drip_is_datecondition_datevalue"),
    OBJECT_SUBSCRIBED_SEQUENCE("Is_subscribed_to_drip"),
    OBJECT_NOT_SUBSCRIBED_SEQUENCE("Is_not_subscribed_to_drip"),
    SEQUENCE_SUBSCRIPTION_PAUSED("Subscription_to_dripa_is_paused"),
    SEQUENCE_SUBSCRIPTION_RESUMED("Subscription_to_dripa_is_not_paused"),
    FIELD_HAS_VALUE("field_is_condition_fieldvalue"),
    OBJECT_HAS_TAG("Is_in_category"),
    OBJECT_NO_TAG("Is_not_in_category"),
    OBJECT_SUBSCRIBED_TO_FULFILLMENT("Is_on_fulfillment"),
    OBJECT_NOT_SUBSCRIBED_TO_FULFILLMENT("Is_not_on_fulfillment"),
    // Messages
    OPENED_EMAIL_N_TIMES("Has_opened_email_condition_n_times"),
    CLICKED_EMAIL_LINK_N_TIMES("Has_clicked_emailanchor_condition_n_times"),
    SMS_CONTAINS_EMAIL("sms_contains_email"),
    SMS_CONTAINS_NO_EMAIL("sms_does_not_contain_email"),
    SMS_CONTAINS_TEXT("sms_contains_text"),
    SMS_CONTAINS_NO_TEXT("sms_does_not_contain_text"),
    // Sales
    SPENT_N_AMOUNT_ON_PRODUCT("Has_spent_condition_N_on_product"),
    ORDERED_N_AMOUNT_OF_PRODUCT("Has_purchased_condition_n_product"),
    SUBSCRIBED_TO_PRODUCT("Is_subscribed_to_productsub"),
    SUBSCRIBED_TO_PRODUCT_FOR_TIMEFRAME("Has_been_subscribed_to_productsub_for_timeframe"),
    // Sites/Pages
    VISITED_WP_PAGE_N_TIMES("Has_visited_website_condition_n_times"),
    VISITED_LANDING_PAGE_N_TIMES("Has_visited_landingpage_splittest_condition_n_times"),
    HAS_ACCESS_TO_WPMEMBERSHIPLVL("Contact_has_access_to_wpintmembershiplevel"),
    NO_ACCESS_TO_WPMEMBERSHIPLVL("Contact_does_not_have_access_to_wpintmembershiplevel");


    private final String _rule;
    private static Map<String, Condition> _map = new HashMap<>();

    Condition(String rule) {
        _rule = rule;
    }

    static {
        for (Condition condition : Condition.values()) {
            _map.put(condition._rule, condition);
        }
    }

    public static Condition fromRule(String condition) {
        return _map.get(condition);
    }

    public String getrule() {
        return _rule;
    }

    public static String[] GetRequiredParams(Condition condition) {
        Map<Condition, String[]> requiredParams = new HashMap<>();
        // Conditions
        requiredParams.put(SUBSCRIBED_TO_CAMPAIGN_BEFORE_AFTER_DATE, new String[]{"campaign_id", "conditional", "date"});
        requiredParams.put(BEEN_ON_CAMPAIGN_FOR_TIMEFRAME, new String[]{"campaign_id", "number_units"});
        requiredParams.put(OBJECT_PAUSED_RESUMED_ON_CAMPAIGN, new String[]{"option", "campaign_id"});
        requiredParams.put(BEEN_ON_SEQUENCE_FOR_TIMEFRAME, new String[]{"sequence_id", "numberOf", "units"});
        requiredParams.put(SUBSCRIBED_TO_SEQUENCE_BEFORE_AFTER_DATE, new String[]{"sequence_id", "conditional", "date"});
        requiredParams.put(OBJECT_SUBSCRIBED_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(OBJECT_NOT_SUBSCRIBED_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(SEQUENCE_SUBSCRIPTION_PAUSED, new String[]{"sequence_id"});
        requiredParams.put(SEQUENCE_SUBSCRIPTION_RESUMED, new String[]{"sequence_id"});
        requiredParams.put(FIELD_HAS_VALUE, new String[]{"field_id", "conditional", "value"});
        requiredParams.put(OBJECT_HAS_TAG, new String[]{"tag_id"});
        requiredParams.put(OBJECT_NO_TAG, new String[]{"tag_id"});
        requiredParams.put(OBJECT_SUBSCRIBED_TO_FULFILLMENT, new String[]{"fulfillment_id"});
        requiredParams.put(OBJECT_NOT_SUBSCRIBED_TO_FULFILLMENT, new String[]{"fulfillment_id"});
        // Messages
        requiredParams.put(OPENED_EMAIL_N_TIMES, new String[]{"email_id", "conditional", "number"});
        requiredParams.put(CLICKED_EMAIL_LINK_N_TIMES, new String[]{"email_id_link_num", "conditional", "number"});
        requiredParams.put(SMS_CONTAINS_EMAIL, new String[0]);
        requiredParams.put(SMS_CONTAINS_NO_EMAIL, new String[0]);
        requiredParams.put(SMS_CONTAINS_TEXT, new String[]{"text"});
        requiredParams.put(SMS_CONTAINS_NO_TEXT, new String[]{"text"});
        // Sales
        requiredParams.put(SPENT_N_AMOUNT_ON_PRODUCT, new String[]{"conditional", "number", "product_id"});
        requiredParams.put(ORDERED_N_AMOUNT_OF_PRODUCT, new String[]{"conditional", "number", "product_id"});
        requiredParams.put(SUBSCRIBED_TO_PRODUCT, new String[]{"product_id"});
        requiredParams.put(SUBSCRIBED_TO_PRODUCT_FOR_TIMEFRAME, new String[]{"product_id", "number_units"});
        // Sites/Pages
        requiredParams.put(VISITED_WP_PAGE_N_TIMES, new String[]{"wordpress_id", "conditional", "number"});
        requiredParams.put(VISITED_LANDING_PAGE_N_TIMES, new String[]{"landingPage_id", "object_type_id", "conditional", "number"});
        requiredParams.put(HAS_ACCESS_TO_WPMEMBERSHIPLVL, new String[]{"wpMembership_id"});
        requiredParams.put(NO_ACCESS_TO_WPMEMBERSHIPLVL, new String[]{"wpMembership_id"});
        return requiredParams.get(condition);
    }

    public static boolean isCheckRestricted(Condition condition) {
        switch (condition) {
            // conditions
            case SPENT_N_AMOUNT_ON_PRODUCT:
            case ORDERED_N_AMOUNT_OF_PRODUCT:
            case SUBSCRIBED_TO_PRODUCT:
            case SUBSCRIBED_TO_PRODUCT_FOR_TIMEFRAME:
                // Sites/Pages
            case VISITED_WP_PAGE_N_TIMES:
            case VISITED_LANDING_PAGE_N_TIMES:
            case HAS_ACCESS_TO_WPMEMBERSHIPLVL:
            case NO_ACCESS_TO_WPMEMBERSHIPLVL:
                return true;
            default:
                return false;
        }
    }
}

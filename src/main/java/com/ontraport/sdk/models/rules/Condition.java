package com.ontraport.sdk.models.rules;

import java.util.HashMap;
import java.util.Map;

public enum Condition implements RuleType {

    // Customer Relationship Management (CRM)
    SUBSCRIBED_TO_CAMPAIGN_BEFORE_AFTER_DATE("campaignbuilder_subscription_date_is_val", new String[]{"campaign_id", "conditional", "date"}),
    BEEN_ON_CAMPAIGN_FOR_TIMEFRAME("Been_on_campaign_for_timeframe", new String[]{"campaign_id", "number_units"}),
    OBJECT_PAUSED_RESUMED_ON_CAMPAIGN("paused_or_active_on_camp", new String[]{"option", "campaign_id"}),
    BEEN_ON_SEQUENCE_FOR_TIMEFRAME("been_on_campaignbuilder_for_timeframe", new String[]{"sequence_id", "numberOf", "units"}),
    SUBSCRIBED_TO_SEQUENCE_BEFORE_AFTER_DATE("Date_of_subscription_to_drip_is_datecondition_datevalue", new String[]{"sequence_id", "conditional", "date"}),
    OBJECT_SUBSCRIBED_SEQUENCE("Is_subscribed_to_drip", new String[]{"sequence_id"}),
    OBJECT_NOT_SUBSCRIBED_SEQUENCE("Is_not_subscribed_to_drip", new String[]{"sequence_id"}),
    SEQUENCE_SUBSCRIPTION_PAUSED("Subscription_to_dripa_is_paused", new String[]{"sequence_id"}),
    SEQUENCE_SUBSCRIPTION_RESUMED("Subscription_to_dripa_is_not_paused", new String[]{"sequence_id"}),
    FIELD_HAS_VALUE("field_is_condition_fieldvalue", new String[]{"field_id", "conditional", "value"}),
    OBJECT_HAS_TAG("Is_in_category", new String[]{"tag_id"}),
    OBJECT_NO_TAG("Is_not_in_category", new String[]{"tag_id"}),
    OBJECT_SUBSCRIBED_TO_FULFILLMENT("Is_on_fulfillment", new String[]{"fulfillment_id"}),
    OBJECT_NOT_SUBSCRIBED_TO_FULFILLMENT("Is_not_on_fulfillment", new String[]{"fulfillment_id"}),
    // Messages
    OPENED_EMAIL_N_TIMES("Has_opened_email_condition_n_times", new String[]{"email_id", "conditional", "number"}),
    CLICKED_EMAIL_LINK_N_TIMES("Has_clicked_emailanchor_condition_n_times", new String[]{"email_id_link_num", "conditional", "number"}),
    SMS_CONTAINS_EMAIL("sms_contains_email", new String[0]),
    SMS_CONTAINS_NO_EMAIL("sms_does_not_contain_email", new String[0]),
    SMS_CONTAINS_TEXT("sms_contains_text", new String[]{"text"}),
    SMS_CONTAINS_NO_TEXT("sms_does_not_contain_text", new String[]{"text"}),
    // Sales
    SPENT_N_AMOUNT_ON_PRODUCT("Has_spent_condition_N_on_product", new String[]{"conditional", "number", "product_id"}),
    ORDERED_N_AMOUNT_OF_PRODUCT("Has_purchased_condition_n_product", new String[]{"conditional", "number", "product_id"}),
    SUBSCRIBED_TO_PRODUCT("Is_subscribed_to_productsub", new String[]{"product_id"}),
    SUBSCRIBED_TO_PRODUCT_FOR_TIMEFRAME("Has_been_subscribed_to_productsub_for_timeframe", new String[]{"product_id", "number_units"}),
    // Sites/Pages
    VISITED_WP_PAGE_N_TIMES("Has_visited_website_condition_n_times", new String[]{"wordpress_id", "conditional", "number"}),
    VISITED_LANDING_PAGE_N_TIMES("Has_visited_landingpage_splittest_condition_n_times", new String[]{"landingPage_id", "object_type_id", "conditional", "number"}),
    HAS_ACCESS_TO_WPMEMBERSHIPLVL("Contact_has_access_to_wpintmembershiplevel", new String[]{"wpMembership_id"}),
    NO_ACCESS_TO_WPMEMBERSHIPLVL("Contact_does_not_have_access_to_wpintmembershiplevel", new String[]{"wpMembership_id"});

    private final String _rule;
    private final String[] _requiredParams;
    private static Map<String, Condition> _map = new HashMap<>();

    Condition(String rule, String[] requiredParam) {
        _rule = rule;
        _requiredParams = requiredParam;
    }

    static {
        for (Condition condition : Condition.values()) {
            _map.put(condition._rule, condition);
        }
    }

    public static Condition fromRule(String condition) {
        return _map.get(condition);
    }

    @Override
    public String getRule() {
        return _rule;
    }

    @Override
    public String getFormattedRule(String value) {
        return getRule() + "(" + value + ")";
    }

    @Override
    public String[] getRequiredParams() {
        return _requiredParams;
    }

    @Override
    public boolean isRestricted() {
        switch (this) {
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

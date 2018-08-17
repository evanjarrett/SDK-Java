package com.ontraport.sdk.models.rules;


import java.util.HashMap;
import java.util.Map;

public enum Event implements RuleType {

    // Customer Relationship Management (CRM)
    OBJECT_CREATED("Contact_added_to_my_database",new String[]{}),
    FIELD_UPDATED("field_is_updated",new String[]{"field_id"}),
    OBJECT_ADDED_TO_SEQUENCE("Contact_added_to_campaign",new String[]{"sequence_id"}),
    OBJECT_REMOVED_FROM_SEQUENCE("Contact_leaves_campaign",new String[]{"sequence_id"}),
    SUBSCRIPTION_TO_SEQUENCE_PAUSED("Subscription_to_drip_is_paused",new String[]{"sequence_id"}),
    SUBSCRIPTION_TO_SEQUENCE_RESUMED("Subscription_to_drip_is_unpaused",new String[]{"sequence_id"}),
    OBJECT_PAUSED_ON_CAMPAIGN("pause_campaign",new String[]{"campaign_id"}),
    OBJECT_RESUMED_ON_CAMPAIGN("unpause_campaign",new String[]{"campaign_id"}),
    OBJECT_ADDED_TO_TAG("Contact_added_to_category",new String[]{"tag_id"}),
    OBJECT_REMOVED_FROM_TAG("Contact_removed_from_category",new String[]{"tag_id"}),
    OBJECT_ADDED_TO_FULFILLMENT("Contact_subscribed_to_fulfillment",new String[]{"fulfillment_id"}),
    OBJECT_REMOVED_FROM_FULFILLMENT("Contact_unsubscribed_from_fulfillment",new String[]{"fulfillment_id"}),
    TODAY_MATCHES_OBJECT_DATE("on_date_field",new String[]{"date_field"}),
    TODAY_RELATIVE_TO_OBJECT_DATE("relative_date_field",new String[]{"numberOf", "units", "option", "date_field"}),
    OBJECT_ADDED_OR_REMOVED_FROM_CAMPAIGN("campaign_builder_object_change",new String[]{"option", "campaign_id"}),
    TASK_COMPLETED("object_completed_task",new String[]{"task_id"}),
    OBJECT_SUBMITS_FORM("object_submits_form",new String[]{"form_id", "outcome"}),
    // External Events
    OBJECT_OPENS_EMAIL("Contact_opens_email",new String[]{"email_id"}),
    OBJECT_CLICKS_EMAIL_LINK("Contact_clicks_emailanchor",new String[]{"email_id", "link_num"}),
    OBJECT_SENDS_YOU_EMAIL("Contact_sends_Email",new String[0]),
    SMS_RECEIVED("sms_message_received",new String[]{"number_id"}),
    // Sales
    OBJECT_PURCHASES_PRODUCT("Contact_purchases_product",new String[]{"product_id"}),
    OBJECT_RECEIVES_REFUND("Contact_receives_refund_on_product",new String[]{"product_id"}),
    OBJECT_VISITS_LANDINGPAGE("Contact_visits_landingpage_splittest",new String[]{"landingPage_id"}),
    OBJECT_VISITS_PURL("Contact_visits_purl_splittest",new String[]{"PURL_id"}),
    OPEN_ORDER_CREATED("Contact_is_subscribed_to_productsub",new String[]{"product_id"}),
    OPEN_ORDER_CHARGED_UPDATED("Contact_subscription_to_productsub_is_subevent",new String[]{"order_id", "option"}),
    CARD_CHARGED_DECLINED("Contact_credit_card_is_ccstatus",new String[]{"option"}),
    // Sites/Pages
    OBJECT_VISITS_URL("contact_visits_url",new String[]{"url"}),
    TRACKED_LINKED_CLICKED("clicks_tracked_link",new String[]{"trackedLink_id"}),
    ACCESS_TO_WPMEMBERSHIPLVL_GIVEN("Contact_given_access_to_wpintmembershiplevel",new String[]{"wpMembership_id"}),
    LOSES_ACCESS_TO_WPMEMBERSHIPLVL("Contact_removed_from_access_to_wpintmembershiplevel",new String[]{"wpMembership_id"});

    private final String _rule;
    private final String[] _requiredParams;
    private static Map<String, Event> _map = new HashMap<>();

    Event(String rule, String[] requiredParams) {
        _rule = rule;
        _requiredParams = requiredParams;
    }

    static {
        for (Event event : Event.values()) {
            _map.put(event._rule, event);
        }
    }

    public static Event fromRule(String event) {
        return _map.get(event);
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
            // events
            case OBJECT_PURCHASES_PRODUCT:
            case OBJECT_RECEIVES_REFUND:
            case OBJECT_VISITS_LANDINGPAGE:
            case OBJECT_VISITS_PURL:
            case OPEN_ORDER_CREATED:
            case OPEN_ORDER_CHARGED_UPDATED:
            case CARD_CHARGED_DECLINED:
                // Sites/Pages
            case OBJECT_VISITS_URL:
            case TRACKED_LINKED_CLICKED:
            case ACCESS_TO_WPMEMBERSHIPLVL_GIVEN:
            case LOSES_ACCESS_TO_WPMEMBERSHIPLVL:
                return true;
            default:
                return false;
        }
    }
}

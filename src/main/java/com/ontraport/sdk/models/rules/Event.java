package com.ontraport.sdk.models.rules;


import java.util.HashMap;
import java.util.Map;

public enum Event {

    // Customer Relationship Management (CRM)
    OBJECT_CREATED("Contact_added_to_my_database"),
    FIELD_UPDATED("field_is_updated"),
    OBJECT_ADDED_TO_SEQUENCE("Contact_added_to_campaign"),
    OBJECT_REMOVED_FROM_SEQUENCE("Contact_leaves_campaign"),
    SUBSCRIPTION_TO_SEQUENCE_PAUSED("Subscription_to_drip_is_paused"),
    SUBSCRIPTION_TO_SEQUENCE_RESUMED("Subscription_to_drip_is_unpaused"),
    OBJECT_PAUSED_ON_CAMPAIGN("pause_campaign"),
    OBJECT_RESUMED_ON_CAMPAIGN("unpause_campaign"),
    OBJECT_ADDED_TO_TAG("Contact_added_to_category"),
    OBJECT_REMOVED_FROM_TAG("Contact_removed_from_category"),
    OBJECT_ADDED_TO_FULFILLMENT("Contact_subscribed_to_fulfillment"),
    OBJECT_REMOVED_FROM_FULFILLMENT("Contact_unsubscribed_from_fulfillment"),
    TODAY_MATCHES_OBJECT_DATE("on_date_field"),
    TODAY_RELATIVE_TO_OBJECT_DATE("relative_date_field"),
    OBJECT_ADDED_OR_REMOVED_FROM_CAMPAIGN("campaign_builder_object_change"),
    TASK_COMPLETED("object_completed_task"),
    OBJECT_SUBMITS_FORM("object_submits_form"),
    // External Events
    OBJECT_OPENS_EMAIL("Contact_opens_email"),
    OBJECT_CLICKS_EMAIL_LINK("Contact_clicks_emailanchor"),
    OBJECT_SENDS_YOU_EMAIL("Contact_sends_Email"),
    SMS_RECEIVED("sms_message_received"),
    // Sales
    OBJECT_PURCHASES_PRODUCT("Contact_purchases_product"),
    OBJECT_RECEIVES_REFUND("Contact_receives_refund_on_product"),
    OBJECT_VISITS_LANDINGPAGE("Contact_visits_landingpage_splittest"),
    OBJECT_VISITS_PURL("Contact_visits_purl_splittest"),
    OPEN_ORDER_CREATED("Contact_is_subscribed_to_productsub"),
    OPEN_ORDER_CHARGED_UPDATED("Contact_subscription_to_productsub_is_subevent"),
    CARD_CHARGED_DECLINED("Contact_credit_card_is_ccstatus"),
    // Sites/Pages
    OBJECT_VISITS_URL("contact_visits_url"),
    TRACKED_LINKED_CLICKED("clicks_tracked_link"),
    ACCESS_TO_WPMEMBERSHIPLVL_GIVEN("Contact_given_access_to_wpintmembershiplevel"),
    LOSES_ACCESS_TO_WPMEMBERSHIPLVL("Contact_removed_from_access_to_wpintmembershiplevel");

    private final String _rule;
    private static Map<String, Event> _map = new HashMap<>();

    Event(String rule) {
        _rule = rule;
    }

    static {
        for (Event event : Event.values()) {
            _map.put(event._rule, event);
        }
    }

    public static Event fromRule(String event) {
        return _map.get(event);
    }

    public String getrule() {
        return _rule;
    }

    public static String[] GetRequiredParams(Event event) {
        Map<Event, String[]> requiredParams = new HashMap<>();
        // Events (i.e. Triggers)
        requiredParams.put(OBJECT_CREATED, new String[]{});
        requiredParams.put(FIELD_UPDATED, new String[]{"field_id"});
        requiredParams.put(OBJECT_ADDED_TO_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(OBJECT_REMOVED_FROM_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(SUBSCRIPTION_TO_SEQUENCE_PAUSED, new String[]{"sequence_id"});
        requiredParams.put(SUBSCRIPTION_TO_SEQUENCE_RESUMED, new String[]{"sequence_id"});
        requiredParams.put(OBJECT_PAUSED_ON_CAMPAIGN, new String[]{"campaign_id"});
        requiredParams.put(OBJECT_RESUMED_ON_CAMPAIGN, new String[]{"campaign_id"});
        requiredParams.put(OBJECT_ADDED_TO_TAG, new String[]{"tag_id"});
        requiredParams.put(OBJECT_REMOVED_FROM_TAG, new String[]{"tag_id"});
        requiredParams.put(OBJECT_ADDED_TO_FULFILLMENT, new String[]{"fulfillment_id"});
        requiredParams.put(OBJECT_REMOVED_FROM_FULFILLMENT, new String[]{"fulfillment_id"});
        requiredParams.put(TODAY_MATCHES_OBJECT_DATE, new String[]{"date_field"});
        requiredParams.put(TODAY_RELATIVE_TO_OBJECT_DATE, new String[]{"numberOf", "units", "option", "date_field"});
        requiredParams.put(OBJECT_ADDED_OR_REMOVED_FROM_CAMPAIGN, new String[]{"option", "campaign_id"});
        requiredParams.put(TASK_COMPLETED, new String[]{"task_id"});
        requiredParams.put(OBJECT_SUBMITS_FORM, new String[]{"form_id", "outcome"});
        // External Events
        requiredParams.put(OBJECT_OPENS_EMAIL, new String[]{"email_id"});
        requiredParams.put(OBJECT_CLICKS_EMAIL_LINK, new String[]{"email_id", "link_num"});
        requiredParams.put(OBJECT_SENDS_YOU_EMAIL, new String[0]);
        requiredParams.put(SMS_RECEIVED, new String[]{"number_id"});
        // Sales
        requiredParams.put(OBJECT_PURCHASES_PRODUCT, new String[]{"product_id"});
        requiredParams.put(OBJECT_RECEIVES_REFUND, new String[]{"product_id"});
        requiredParams.put(OBJECT_VISITS_LANDINGPAGE, new String[]{"landingPage_id"});
        requiredParams.put(OBJECT_VISITS_PURL, new String[]{"PURL_id"});
        requiredParams.put(OPEN_ORDER_CREATED, new String[]{"product_id"});
        requiredParams.put(OPEN_ORDER_CHARGED_UPDATED, new String[]{"order_id", "option"});
        requiredParams.put(CARD_CHARGED_DECLINED, new String[]{"option"});
        // Sites/Pages
        requiredParams.put(OBJECT_VISITS_URL, new String[]{"url"});
        requiredParams.put(TRACKED_LINKED_CLICKED, new String[]{"trackedLink_id"});
        requiredParams.put(ACCESS_TO_WPMEMBERSHIPLVL_GIVEN, new String[]{"wpMembership_id"});
        requiredParams.put(LOSES_ACCESS_TO_WPMEMBERSHIPLVL, new String[]{"wpMembership_id"});
        return requiredParams.get(event);
    }

    public static boolean isCheckRestricted(Event event) {
        switch (event) {
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

package com.ontraport.sdk.models.rules;


import java.util.HashMap;
import java.util.Map;

public enum Action {

    // Customer Relationship Management (CRM)
    ADD_OBJECT_TO_TAG("Add_contact_to_category"),
    REMOVE_OBJECT_FROM_TAG("Remove_contact_from_category"),
    ADD_REMOVE_OBJECT_FROM_CAMPAIGN("campaign_builder_action_change"),
    PAUSE_UNPAUSE_OBJECT_ON_CAMPAIGN("pause_or_unpause_on_camp"),
    ADD_OBJECT_TO_SEQUENCE("Assign_contact_to_drip"),
    REMOVE_OBJECT_FROM_SEQUENCE("Remove_contact_from_drip"),
    REMOVE_OBJECT_FROM_ALL_SEQUENCES("Remove_contact_from_all_sequences"),
    PAUSE_SUBSCRIPTION_TO_SEQUENCE("Pause_subscription_to_dripa"),
    UNPAUSE_SUBSCRIPTION_TO_SEQUENCE("Unpause_subscription_to_dripa"),
    CHANGE_FIELD_VALUE("Change_field_to_fieldvalue"),
    ADD_LEAD_ROUTER("Add_to_leadrouter"),
    ADD_OBJECT_TO_FULFILLMENT("Assign_to_fulfillment"),
    REMOVE_OBJECT_FROM_FULFILLMENT("Remove_from_fulfillment"),
    REMOVE_FROM_ALL_FULFILLMENTS("Remove_from_all_fulfillments"),
    // Sales
    RECHARGE_ALL_TRANSACTIONS_IN_COLLECTIONS("Recharge_all_declined_transactions()"),
    ADD_PRODUCT_TO_PURCHASE_HISTORY("Add_product_to_purchase_history"),
    CANCEL_OPEN_ORDER("Cancel_open_orders_with_product"),
    // Messages
    NOTIFY_WITH_EMAIL("Notify_someone_with_emailmbs"),
    SEND_EMAIL("Send_contact_an_emailmbs"),
    SEND_POSTCARD("Send_contact_a_postcard"),
    ADD_TASK("Send_contact_a_task"),
    SEND_SMS("send_contact_an_sms"),
    // Sites/Pages
    PING_URL("Ping_APIURL"),
    // only contacts
    GIVE_WPMEMBERSHIPLVL_ACCESS("Add_access_for_contact_to_wpintmembershiplevel"),
    REMOVE_WPMEMBERSHIPLVL_ACCESS("Remove_access_for_contact_to_wpintmembershiplevel"),
    // Social
    UPDATE_FB_CUSTOM_AUDIENCE("facebook_audience_action"),
    NOTIFY_WITH_SMS("Notify_someone_with_sms");

    private final String _rule;
    private static Map<String, Action> _map = new HashMap<>();

    Action(String rule) {
        _rule = rule;
    }

    static {
        for (Action action : Action.values()) {
            _map.put(action._rule, action);
        }
    }

    public static Action fromRule(String action) {
        return _map.get(action);
    }

    public String getrule() {
        return _rule;
    }

    public static String[] GetRequiredParams(Action action) {
        Map<Action, String[]> requiredParams = new HashMap<>();
        // Actions
        requiredParams.put(ADD_OBJECT_TO_TAG, new String[]{"tag_id"});
        requiredParams.put(REMOVE_OBJECT_FROM_TAG, new String[]{"tag_id"});
        requiredParams.put(ADD_REMOVE_OBJECT_FROM_CAMPAIGN, new String[]{"option", "campaign_id"});
        requiredParams.put(PAUSE_UNPAUSE_OBJECT_ON_CAMPAIGN, new String[]{"option", "campaign_id"});
        requiredParams.put(ADD_OBJECT_TO_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(REMOVE_OBJECT_FROM_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(REMOVE_OBJECT_FROM_ALL_SEQUENCES, new String[0]);
        requiredParams.put(PAUSE_SUBSCRIPTION_TO_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(UNPAUSE_SUBSCRIPTION_TO_SEQUENCE, new String[]{"sequence_id"});
        requiredParams.put(CHANGE_FIELD_VALUE, new String[]{"field_id", "value", "field_option"});
        requiredParams.put(ADD_LEAD_ROUTER, new String[]{"leadRouter_id"});
        requiredParams.put(ADD_OBJECT_TO_FULFILLMENT, new String[]{"fulfillment_id"});
        requiredParams.put(REMOVE_OBJECT_FROM_FULFILLMENT, new String[]{"fulfillment_id"});
        requiredParams.put(REMOVE_FROM_ALL_FULFILLMENTS, new String[0]);
        // Sales
        requiredParams.put(RECHARGE_ALL_TRANSACTIONS_IN_COLLECTIONS, new String[0]);
        requiredParams.put(ADD_PRODUCT_TO_PURCHASE_HISTORY, new String[]{"product_id"});
        requiredParams.put(CANCEL_OPEN_ORDER, new String[]{"product_id"});
        // Messages
        requiredParams.put(NOTIFY_WITH_EMAIL, new String[]{"user_id", "email_id"});
        requiredParams.put(SEND_EMAIL, new String[]{"email_id"});
        requiredParams.put(SEND_POSTCARD, new String[]{"postcard_id"});
        requiredParams.put(ADD_TASK, new String[]{"task_id"});
        requiredParams.put(SEND_SMS, new String[]{"sms_id", "number_id"});
        requiredParams.put(PING_URL, new String[]{"url", "post_data", "json"});
        requiredParams.put(GIVE_WPMEMBERSHIPLVL_ACCESS, new String[]{"wpMembership_id"});
        requiredParams.put(REMOVE_WPMEMBERSHIPLVL_ACCESS, new String[]{"wpMembership_id"});
        requiredParams.put(UPDATE_FB_CUSTOM_AUDIENCE, new String[]{"add_remove", "custom_audience_id"});
        requiredParams.put(NOTIFY_WITH_SMS, new String[]{"user_id", "sms_id", "number_id"});
        return requiredParams.get(action);
    }

    public static boolean isCheckRestricted(Action action) {
        switch (action) {
            case ADD_LEAD_ROUTER:
            case RECHARGE_ALL_TRANSACTIONS_IN_COLLECTIONS:
            case ADD_PRODUCT_TO_PURCHASE_HISTORY:
            case CANCEL_OPEN_ORDER:
            case SEND_POSTCARD:
            case GIVE_WPMEMBERSHIPLVL_ACCESS:
            case REMOVE_WPMEMBERSHIPLVL_ACCESS:
                return true;
            default:
                return false;
        }
    }
}

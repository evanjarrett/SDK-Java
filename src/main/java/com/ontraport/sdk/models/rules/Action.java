package com.ontraport.sdk.models.rules;


import java.util.HashMap;
import java.util.Map;

public enum Action implements RuleType {

    // Customer Relationship Management (CRM)
    ADD_OBJECT_TO_TAG("Add_contact_to_category", new String[]{"tag_id"}),
    REMOVE_OBJECT_FROM_TAG("Remove_contact_from_category", new String[]{"tag_id"}),
    ADD_REMOVE_OBJECT_FROM_CAMPAIGN("campaign_builder_action_change", new String[]{"option", "campaign_id"}),
    PAUSE_UNPAUSE_OBJECT_ON_CAMPAIGN("pause_or_unpause_on_camp", new String[]{"option", "campaign_id"}),
    ADD_OBJECT_TO_SEQUENCE("Assign_contact_to_drip", new String[]{"sequence_id"}),
    REMOVE_OBJECT_FROM_SEQUENCE("Remove_contact_from_drip", new String[]{"sequence_id"}),
    REMOVE_OBJECT_FROM_ALL_SEQUENCES("Remove_contact_from_all_sequences", new String[0]),
    PAUSE_SUBSCRIPTION_TO_SEQUENCE("Pause_subscription_to_dripa", new String[]{"sequence_id"}),
    UNPAUSE_SUBSCRIPTION_TO_SEQUENCE("Unpause_subscription_to_dripa", new String[]{"sequence_id"}),
    CHANGE_FIELD_VALUE("Change_field_to_fieldvalue", new String[]{"field_id", "value", "field_option"}),
    ADD_LEAD_ROUTER("Add_to_leadrouter", new String[]{"leadRouter_id"}),
    ADD_OBJECT_TO_FULFILLMENT("Assign_to_fulfillment", new String[]{"fulfillment_id"}),
    REMOVE_OBJECT_FROM_FULFILLMENT("Remove_from_fulfillment", new String[]{"fulfillment_id"}),
    REMOVE_FROM_ALL_FULFILLMENTS("Remove_from_all_fulfillments", new String[0]),
    // Sales
    RECHARGE_ALL_TRANSACTIONS_IN_COLLECTIONS("Recharge_all_declined_transactions", new String[0]),
    ADD_PRODUCT_TO_PURCHASE_HISTORY("Add_product_to_purchase_history", new String[]{"product_id"}),
    CANCEL_OPEN_ORDER("Cancel_open_orders_with_product", new String[]{"product_id"}),
    // Messages
    NOTIFY_WITH_EMAIL("Notify_someone_with_emailmbs", new String[]{"user_id", "email_id"}),
    SEND_EMAIL("Send_contact_an_emailmbs", new String[]{"email_id"}),
    SEND_POSTCARD("Send_contact_a_postcard", new String[]{"postcard_id"}),
    ADD_TASK("Send_contact_a_task", new String[]{"task_id"}),
    SEND_SMS("send_contact_an_sms", new String[]{"sms_id", "number_id"}),
    // Sites/Pages
    PING_URL("Ping_APIURL", new String[]{"url", "post_data", "json"}),
    // only contacts
    GIVE_WPMEMBERSHIPLVL_ACCESS("Add_access_for_contact_to_wpintmembershiplevel", new String[]{"wpMembership_id"}),
    REMOVE_WPMEMBERSHIPLVL_ACCESS("Remove_access_for_contact_to_wpintmembershiplevel", new String[]{"wpMembership_id"}),
    // Social
    UPDATE_FB_CUSTOM_AUDIENCE("facebook_audience_action", new String[]{"add_remove", "custom_audience_id"}),
    NOTIFY_WITH_SMS("Notify_someone_with_sms", new String[]{"user_id", "sms_id", "number_id"});

    private final String _rule;
    private final String[] _requiredParams;
    private static Map<String, Action> _map = new HashMap<>();

    Action(String rule, String[] requiredParam) {
        _rule = rule;
        _requiredParams = requiredParam;
    }

    static {
        for (Action action : Action.values()) {
            _map.put(action._rule, action);
        }
    }

    public static Action fromRule(String action) {
        return _map.get(action);
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

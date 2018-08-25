package com.ontraport.sdk.models.rules;

public interface RuleType {

    static RuleType fromRule(String type) {
        RuleType t = Event.fromRule(type);
        if (t != null) {
            return t;
        }
        t = Condition.fromRule(type);
        if (t != null) {
            return t;
        }
        t = Action.fromRule(type);
        if (t != null) {
            return t;
        }
        return null;
    }

    String getRule();

    String getFormattedRule(String value);

    String[] getRequiredParams();

    boolean isRestricted();
}

package com.ontraport.sdk.models.rules;

public interface RuleType {

    String getRule();

    String getFormattedRule(String value);

    String[] getRequiredParams();

    boolean isRestricted();
}

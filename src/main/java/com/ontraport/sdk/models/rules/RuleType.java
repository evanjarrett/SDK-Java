package com.ontraport.sdk.models.rules;

public interface RuleType {

    String getRule();

    String[] getRequiredParams();

    boolean isRestricted();
}

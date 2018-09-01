package com.ontraport.sdk.models.rules;

import com.ontraport.sdk.exceptions.OntraportAPIRuntimeException;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.models.Requestable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleBuilder implements Requestable {

    private int _object_type_id;
    private String _name;
    private String _id;
    private List<RulePart<Event>> _events = new ArrayList<>();
    private List<RulePart<Condition>> _conditions = new ArrayList<>();
    private List<RulePart<Action>> _actions = new ArrayList<>();

    public static String DAYS = "0";
    public static String WEEKS = "1";
    public static String MONTHS = "2";

    public static String BEFORE_FIELD = "0";
    public static String AFTER_FIELD = "1";
    public static String CHARGED_AND_SUCCESSFUL = "0";
    public static String CANCELED = "1";
    public static String COMPLETED = "2";
    public static String CHARGED_BUT_DECLINED = "3";
    public static String CHARGED = "0";
    public static String DECLINED = "1";
    public static String RESUMED = "0";
    public static String PAUSE = "1";
    public static String UNPAUSE = "0";
    public static String ADD = "0";
    public static String REMOVE = "1";
    public static String SUCCESSFUL = "0";
    public static String FAILURE = "1";

    public static String EQUAL_TO = "Equal To";
    public static String NOT_EQUAL_TO = "Not Equal To";
    public static String GREATER_THAN = "Greater Than";
    public static String LESS_THAN = "Less Than";
    public static String GREATER_OR_EQUAL_TO = "Greater Than or Equal To";
    public static String LESS_OR_EQUAL_TO = "Less Than or Equal To";
    public static String CONTAINS = "Contains";
    public static String DOES_NOT_CONTAIN = "Does Not Contain";
    public static String STARTS_WITH = "Starts With";
    public static String ENDS_WITH = "Ends With";
    public static String ON = "1";
    public static String BEFORE = "2";
    public static String AFTER = "3";

    public static String TODAY = "TODAY";
    public static String YESTERDAY = "YESTERDAY";
    public static String LAST_SUNDAY = "LSUNDAY";
    public static String LAST_TWO_SUNDAYS = "L2SUNDAY";
    public static String FIRST_DAY_THIS_MONTH = "FDTMONTH";
    public static String FIRST_DAY_LAST_MONTH = "FDLMONTH";
    public static String THIS_DAY_LAST_MONTH = "TDLMONTH";
    public static String FIRST_DAY_THIS_YEAR = "FDTYEAR";
    public static String THIS_DAY_LAST_YEAR = "TDLYEAR";
    public static String SEVEN_DAYS_AGO = "S7DAYS";
    public static String THIRTY_DAYS_AGO = "S30DAYS";
    public static String NINETY_DAYS_AGO = "S90DAYS";
    public static String HUNDRED_TWENTY_DAYS_AGO = "S120DAYS";
    public static String HUNDRED_EIGHTY_DAYS_AGO = "S180DAYS";
    public static String TOMORROW = "TOMORROW";
    public static String FIRST_DAY_NEXT_MONTH = "FDNMONTH";
    public static String THIS_DAY_NEXT_MONTH = "TDNMONTH";
    public static String FIRST_DAY_NEXT_YEAR = "FDNYEAR";
    public static String THIS_DAY_NEXT_YEAR = "TDNYEAR";
    public static String SEVEN_DAYS_FROM_NOW = "7DFNOW";
    public static String FOURTEEN_DAYS_FROM_NOW = "14DFNOW";
    public static String THIRTY_DAYS_FROM_NOW = "30DFNOW";
    public static String SIXTY_DAYS_FROM_NOW = "60DFNOW";
    public static String NINETY_DAYS_FROM_NOW = "90DFNOW";
    public static String HUNDRED_TWENTY_DAYS_FROM_NOW = "120DFNOW";
    public static String HUNDRED_EIGHTY_DAYS_FROM_NOW = "180DFNOW";

    public RuleBuilder(String name, int object_type_id) {
        this(null, name, object_type_id);
    }

    public RuleBuilder(String id, String name, int object_type_id) {
        _id = id;
        _name = name;
        _object_type_id = object_type_id;
    }

    @Override
    public RequestParams toRequestParams() {

        String[] event_list = new String[_events.size()];
        for (int i = 0; i < _events.size(); i++) {
            event_list[i] = _events.get(i).getType().getFormattedRule(_events.get(i).getValue());
        }
        String event = join(event_list, ";");

        String[] action_list = new String[_actions.size()];
        for (int i = 0; i < _actions.size(); i++) {
            action_list[i] = _actions.get(i).getType().getFormattedRule(_actions.get(i).getValue());
        }
        String action = join(event_list, ";");

        String condition = "";
        for (int i = 0; i < _conditions.size(); i++) {
            Operator op = _conditions.get(i).getOperator();
            condition += _conditions.get(i).getType().getFormattedRule(_conditions.get(i).getValue()) + op;
        }

        RequestParams params = new RequestParams();
        params.put("object_type_id", _object_type_id);
        params.put("name", _name);
        params.put("events", event);
        params.put("conditions", condition);
        params.put("actions", action);

        return params;
    }

    public static RuleBuilder CreateFromResponse(SingleResponse response) {
        String id = response.getData().get("id");
        String name = response.getData().get("name");
        int object_type_id = Integer.parseInt(response.getData().get("object_type_id"));

        RuleBuilder ruleBuilder = new RuleBuilder(id, name, object_type_id);

        String[] events = _splitRule(response.getData().get("events"));
        for (String event : events) {
            Map<String, String> parsed = _parseParams(event);
            Event type = Event.fromRule(parsed.get("name"));
            ruleBuilder.addEvent(type, parsed.get("params").split(","));
        }

        String[] actions = _splitRule(response.getData().get("actions"));
        for (String action : actions) {
            Map<String, String> parsed = _parseParams(action);
            Action type = Action.fromRule(parsed.get("name"));
            ruleBuilder.addAction(type, parsed.get("params").split(","));
        }

        String[] conditions = _splitRule(response.getData().get("conditions"));
        if (conditions.length > 0) {
            for (String condition : conditions) {
                Map<String, String> parsed = _parseParams(condition);
                String operator = condition.substring(condition.length() - 1);
                Condition type = Condition.fromRule(parsed.get("name"));
                ruleBuilder.addCondition(type, parsed.get("params").split(","), Operator.fromOperator(operator));
            }
        }

        return ruleBuilder;
    }

    public RuleBuilder addEvent(Event event, String[] params) {
        return add(event, _events, params, null);
    }

    public RuleBuilder addAction(Action action, String[] params) {
        return add(action, _actions, params, null);
    }

    public RuleBuilder addCondition(Condition condition, String[] params, Operator operator) {
        // determine operator
        if (!_conditions.isEmpty() && operator == null) {
            throw new OntraportAPIRuntimeException("Invalid operator. Must be AND or OR.");
        }
        return add(condition, _conditions, params, operator);
    }

    public <T extends RuleType> RuleBuilder add(T type, List<RulePart<T>> list, String[] params, Operator operator) {
        if (validateRule(type) && _checkParams(type.getRequiredParams(), params)) {
            String value = join(params, ",");
            if (type instanceof Action && type == Action.PING_URL) {
                value = join(params, "::");
            }
            list.add(new RulePart<>(type, value, operator));
        }
        list.clear();
        return this;
    }

    public void clearEvents() {
        _events.clear();
    }

    public void clearConditions() {
        _conditions.clear();
    }

    public void clearActions() {
        _actions.clear();
    }

    public boolean validateRule(RuleType type) {
        if (type.isRestricted() && _object_type_id != 0) {
            throw new OntraportAPIRuntimeException(type.getRule() + " can only be used with Contacts object.");
        }
        return true;
    }

    private boolean _checkParams(String[] requiredParams, String[] params) {
        if (params.length == 0 || requiredParams.length != params.length) {
            throw new OntraportAPIRuntimeException("Invalid number of parameters for rule. " +
                    "Refer to the API Doc to make sure you have the correct inputs.");
        }
        List<String> invalid_params = new ArrayList<>();

        List<String> units = Arrays.asList(DAYS, WEEKS, MONTHS);
        List<String> conditional = Arrays.asList(
                EQUAL_TO,
                NOT_EQUAL_TO,
                GREATER_THAN,
                LESS_THAN,
                GREATER_OR_EQUAL_TO,
                LESS_OR_EQUAL_TO,
                CONTAINS,
                DOES_NOT_CONTAIN,
                STARTS_WITH,
                ENDS_WITH,
                ON,
                BEFORE,
                AFTER
        );

        for (int i = 0; i < requiredParams.length; i++) {
            String param = requiredParams[i];
            String value = params[i];

            if (param.equals("conditional") && conditional.contains(value)) {
                invalid_params.add(param);
            }
            else if (param.equals("units") && !units.contains(value)) {
                invalid_params.add(param);
            }
            else if (param.equals("option")) {
                invalid_params.add(param);
            }
            else if (param.equals("outcome")) {
                invalid_params.add(param);
            }

        }

        if (invalid_params.isEmpty()) {
            throw new OntraportAPIRuntimeException("Invalid inputs for " + invalid_params.toString() + ". " +
                    "Refer to the API Doc to make sure your rule parameters are valid and in the correct order.");
        }
        return true;
    }

    public static String join(String[] params, String delimiter) {
        if (params == null)
            return "";

        int iMax = params.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < iMax; i++) {
            b.append(String.valueOf(params[i]));
            b.append(delimiter);
        }
        return b.toString();
    }

    private static Map<String, String> _parseParams(String rule) {
        Map<String, String> parsedParams = new HashMap<>();
        String[] split = rule.split("[\\(\\)]");
        String name = split[0];
        String params = split[1].replace(")", "").trim();
        parsedParams.put("params", params);
        parsedParams.put("name", name);
        return parsedParams;
    }

    private static String[] _splitRule(String rules_string) {
        if (rules_string == null) {
            return new String[0];
        }

        String[] rules = rules_string.split("(?<=;)|(?<=\\|)");
        for (int i = 0; i < rules.length; i++) {
            rules[i] = rules[i].trim();
        }
        return rules;
    }
}

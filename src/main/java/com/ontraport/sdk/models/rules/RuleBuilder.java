package com.ontraport.sdk.models.rules;

import com.ontraport.sdk.exceptions.OntraportAPIException;
import com.ontraport.sdk.exceptions.OntraportAPIRuntimeException;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.models.Requestable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public RuleBuilder(String name, int object_type_id, String id) {
        _name = name;
        _object_type_id = object_type_id;
        _id = id;
    }

    @Override
    public RequestParams toRequestParams() {
        /*
        if (empty(_events) || empty(_actions))
        {
            throw new OntraportAPIException("Events and Actions must be added to create rule.");
        }

        Event[] events = implode(";", _events);
        Action[] actions = implode(";", _actions);

        if (!empty(_conditions))
        {
            conditions = implode(_conditions);
            conditions = trim(conditions, ";");
            conditions = trim(conditions, "|");
        }
        else if (empty(_conditions))
        {
            conditions = "";
        }
        requestParams = array(
            "object_type_id" => _object_type_id,
            "name" => _name,
            "events" =>  events,
            "conditions" => conditions,
            "actions" => actions
        );
        if (!empty(_id))
        {
            requestParams["id"] = _id;
        }
        return requestParams;
         */
        return null;
    }

    public static void CreateFromResponse() {
        /*
        $name = $data["name"];
        $object_type_id = $data["object_type_id"];
        $id = $data["id"];

        $builder = new RuleBuilder($name, $object_type_id, $id);

        $events = self::_splitRule($data["events"]);
        $conditions = array();
        if ($data["conditions"] != null)
        {
            $conditions = self::_splitRule($data["conditions"]);
        }
        $actions = self::_splitRule($data["actions"]);

        foreach($events as $event)
        {
            // separate rule and params
            $parsed = self::_parseParams($event);
            $builder->addEvent($parsed["name"], $parsed["params"]);
        }
        foreach($actions as $action)
        {
            // separate rule and params
            $parsed = self::_parseParams($action);
            $builder->addAction($parsed["name"], $parsed["params"]);
        }
        if (!empty($conditions))
        {
            foreach($conditions as $condition)
            {
                // determine operators
                $operators = self::_operatorClassifier($data["conditions"]);
                $or_rule = $operators["or_rules"];
                $and_rule = $operators["and_rules"];
                $end_rule = $operators["end_rule"];
                // separate rule and param
                $parsed = self::_parseParams($condition);

                if (in_array($condition, $end_rule))
                {
                    $builder->addCondition($parsed["name"], $parsed["params"]);
                }
                else if (in_array($condition, $or_rule))
                {
                    $builder->addCondition($parsed["name"], $parsed["params"], "OR");
                }
                else if (in_array($condition, $and_rule))
                {
                    $builder->addCondition($parsed["name"], $parsed["params"], "AND");
                }
            }
        }
        return $builder;
         */
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
        if (validateRule(type) && _checkParams(type.getRequiredParams(), params))
        {
            String value = _formatParams(params);
            if (type instanceof Action && type == Action.PING_URL)
            {
                value = _formatParams(params, "::");
            }
            list.add(new RulePart<>(type, value, operator));
        }
        list.clear();
        return this;
    }

    public void clearEvents()
    {
        _events.clear();
    }

    public void clearConditions()
    {
        _conditions.clear();
    }

    public void clearActions()
    {
        _actions.clear();
    }

    public boolean validateRule(RuleType type)
    {
        if (type.isRestricted() && _object_type_id != 0) {
            throw new OntraportAPIRuntimeException(type.getRule() + " can only be used with Contacts object.");
        }
        return true;
    }

    private boolean _checkParams(String[] requiredParams, String[] params) {
        if (params.length == 0 || requiredParams.length != params.length)
        {
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

            if(param.equals("conditional") && conditional.contains(value))
            {
                invalid_params.add(param);
            }
            else if(param.equals("units") && !units.contains(value))
            {
                invalid_params.add(param);
            }
            else if(param.equals("option"))
            {
                invalid_params.add(param);
            }
            else if(param.equals("outcome"))
            {
                invalid_params.add(param);
            }

        }

        if (invalid_params.isEmpty())
        {
            throw new OntraportAPIRuntimeException("Invalid inputs for " + invalid_params.toString() + ". " +
            "Refer to the API Doc to make sure your rule parameters are valid and in the correct order.");
        }
        return true;
    }

    private String _formatParams(String[] params) {
        return _formatParams(params, ",");
    }

    private String _formatParams(String[] params, String delimiter)
    {
        return Arrays.toString(params).replace(", ", delimiter).replaceAll("[\\[\\]]", "");
    }

    private String _parseParams(String rule) {
        /*
        $parsed = array();

        $split = explode("(", $rule);
        $name = $split[0];
        $str_params = rtrim($split[1], ")");
        // if empty string
        if ($str_params == '')
        {
            $parsed_params = array();
        }
        else
        {
            $parsed_params = explode(",", $str_params);
        }
        $parsed["params"] = $parsed_params;
        $parsed["name"] = $name;
         */
        return null;
    }

    private String[] _splitRule(String rule) {
        /*
        $rules = array();
        $init_rule = str_replace("|", ";", $init_rule);
        $rules = explode(";", $init_rule);
        foreach($rules as $key => $rule)
        {
            $rules[$key] = trim($rule);
        }
         */
        return new String[0];
    }

    private Operator[] _operatorClassifier(String conditions) {
        /*
        $or_rules = array();
        $and_rules = array();
        $end_rule = array();
        $strlen = strlen($init_conditions);
        $counter = 0;

        for($i = 0; $i <= $strlen; $i++)
        {
            $char = substr($init_conditions, $i, 1);

            if ($char == "|" || $char == ";"|| $i == ($strlen - 1))
            {
                $rule = substr($init_conditions, $counter, $i - $counter);
                $rule = trim($rule);
                if ($char == "|")
                {
                    $or_rules[] = $rule;
                }
                else if ($char == ";")
                {
                    $and_rules[] = $rule;
                }
                else if ($i == ($strlen - 1))
                {
                    $rule = substr($init_conditions, $counter, $strlen - $counter);
                    $end_rule[] = $rule;
                }
                $counter = $i + 1;
            }
        }
        $operators = array(
            "or_rules" => $or_rules,
            "and_rules" => $and_rules,
            "end_rule" => $end_rule
        );
         */
        return new Operator[0];
    }

    private class RulePart<T extends RuleType> {
        private T _type;
        private String _value;
        private Operator _operator;

        RulePart(T type, String value) {
            this(type, value, null);
        }

        RulePart(T type, String value, Operator operator) {
            _type = type;
            _value = value;
            _operator = operator;
        }
    }
}

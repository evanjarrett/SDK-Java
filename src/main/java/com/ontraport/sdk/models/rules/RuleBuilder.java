package com.ontraport.sdk.models.rules;

public class RuleBuilder { //implements Requestable {

    private String _object_type_id;
    private String _name;
    private String _id;
    private String _events;
    private String _conditions;
    private String _actions;

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

    public RuleBuilder(String name, String object_type_id, String id) {
        _name = _name;
        _object_type_id = object_type_id;
        _id = id;
    }

    public void addEvent(String event, String[] params) {

    }

    public void addAction(String action, String[] params) {

    }

    public void addCondition(String condition, String[] params, String operator) {

    }
}


/*

    public void addEvent($event, $eventParams)
    {
        // check if valid rule usage for object_type_id
        $this->validateRule("Events", $event);
        // get required parameters for specific rule
        $requiredParams = Events::GetRequiredParams($event);
        // checking for missing and invalid types for each parameter
        $check_params = $this->_checkParams($requiredParams, $eventParams);
        // if no missing or invalid rule parameters
        if ($check_params)
        {
            $value = $this->_formatParams($eventParams);
            $rule = $event . "(" . $value . ")";
            $this->_events[] = $rule;

            return $this->_events;
        }
        return false;
    }

    public void addCondition($condition, $conditionParams, $operator = NULL)
    {
        // check if valid rule usage for object_type_id
        $this->validateRule("Conditions", $condition);
        // get required parameters for specific rule
        $requiredParams = Conditions::GetRequiredParams($condition);
        // checking for missing and invalid parameters
        $check_params = $this->_checkParams($requiredParams, $conditionParams);

        // determine operator
        if (empty($this->_conditions))
        {
            $operator = NULL;
        }
        else if (!empty($this->_conditions))
        {
            if ($operator == "AND")
            {
                $operator = ";";
            }
            else if ($operator == "OR")
            {
                $operator = "|";
            }
            else if ($operator == NULL)
            {
                throw new Exceptions\RequiredParamsException(array("operator"));
            }
            else
            {
                throw new Exceptions\OntraportAPIException("Invalid operator. Must be AND or OR.");
            }
        }
        $rule = $operator . $condition;

        // if no missing or invalid rule parameters
        if ($check_params)
        {
            $value = $this->_formatParams($conditionParams);
            $formatted = "(" . $value . ")";
            $this->_conditions[] = $rule . $formatted;

            return $this->_conditions;
        }
        return false;
    }

    public void addAction($action, $actionParams)
    {
        // check if valid rule usage for object_type_id
        $this->validateRule("Actions", $action);
        // get required parameters for specific rule
        $requiredParams = Actions::GetRequiredParams($action);
        // checking for missing and invalid parameters
        $exception = false;
        if ($action == Actions::PING_URL)
        {
            $exception = true;
        }
        $check_params = $this->_checkParams($requiredParams, $actionParams, $exception);
        // if no missing or invalid rule parameters
        if ($check_params)
        {
            $rule = $action;
            // special formatting for ping_url
            if ($action == Actions::PING_URL)
            {
                $formatted = $this->_formatParams($actionParams, "::");
                $this->_actions[] = $action . "(" . $formatted . ")";

                return $this->_actions;
            }
            // general formatting for actions
            $value = $this->_formatParams($actionParams);
            $formatted = "(" . $value . ")";
            $this->_actions[] = $rule . $formatted;

            return $this->_actions;
        }
        return false;
    }

    public void clearEvents()
    {
        $this->_events = array();
        return $this->_events;
    }

    public void clearConditions()
    {
        $this->_conditions = array();
        return $this->_conditions;
    }

    public void clearActions()
    {
        $this->_actions = array();
        return $this->_actions;
    }

    public void removeEventByName($event_name)
    {
        foreach($this->_events as $key => $event)
        {
            if(strpos($event, $event_name) !== false)
            {
                array_splice($this->_events, $key, 1);
            }
        }
        return $this->_events;
    }
     public void removeConditionByName($condition_name)
     {
         foreach($this->_conditions as $key => $condition)
         {
             if(strpos($condition, $condition_name) !== false)
             {
                 array_splice($this->_conditions, $key, 1);
             }
         }
         return $this->_conditions;
     }

    public void removeActionByName($action_name)
    {
        foreach($this->_actions as $key => $action)
        {
            if(strpos($action, $action_name) !== false)
            {
                array_splice($this->_actions, $key, 1);
            }
        }
        return $this->_actions;
    }

    public static void CreateFromResponse(array $data)
    {
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
    }

    public void toRequestParams()
    {
        if (empty($this->_events) || empty($this->_actions))
        {
            throw new Exceptions\OntraportAPIException("Events and Actions must be added to create rule.");
        }

        $events = implode(";", $this->_events);
        $actions = implode(";", $this->_actions);

        if (!empty($this->_conditions))
        {
            $conditions = implode($this->_conditions);
            $conditions = trim($conditions, ";");
            $conditions = trim($conditions, "|");
        }
        else if (empty($this->_conditions))
        {
            $conditions = "";
        }
        $requestParams = array(
            "object_type_id" => $this->_object_type_id,
            "name" => $this->_name,
            "events" =>  $events,
            "conditions" => $conditions,
            "actions" => $actions
        );
        if (!empty($this->_id))
        {
            $requestParams["id"] = $this->_id;
        }
        return $requestParams;
    }

    public void validateRule($type, $rule)
    {
        $requiredParams = call_user_func(__NAMESPACE__ . "\\" . $type . "::GetRequiredParams", $rule);
        // check if rule is valid
        if ($requiredParams == null && !is_array($requiredParams))
        {
            throw new Exceptions\OntraportAPIException($rule . " is not a valid rule type.");
        }
        // validate rule is used for correct object
        if (call_user_func(__NAMESPACE__ . "\\" . $type . "::CheckRestricted", $rule) && ($this->_object_type_id != 0))
        {
            throw new Exceptions\OntraportAPIException($rule . " can only be used with Contacts object.");
        }
        return true;
    }
    private void _checkParams($requiredParams, $requestParams, $exception = false)
    {
        // exceptions for parameter length for ping url
        if ($exception == true)
        {
            if (count($requestParams) == 0)
            {
                throw new Exceptions\OntraportAPIException("Invalid number of parameters for rule. " .
                "Refer to the API Doc to make sure you have the correct inputs.");
                return false;
            }
        }
        else if (count($requiredParams) != count($requestParams))
        {
            throw new Exceptions\OntraportAPIException("Invalid number of parameters for rule. " .
            "Refer to the API Doc to make sure you have the correct inputs.");
            return false;
        }
        $invalid_params = array();
        $units = array(self::DAYS, self::WEEKS, self::MONTHS);
        $conditional = array(
            self::EQUAL_TO,
            self::NOT_EQUAL_TO,
            self::GREATER_THAN,
            self::LESS_THAN,
            self::GREATER_OR_EQUAL_TO,
            self::LESS_OR_EQUAL_TO,
            self::CONTAINS,
            self::DOES_NOT_CONTAIN,
            self::STARTS_WITH,
            self::ENDS_WITH,
            self::ON,
            self::BEFORE,
            self::AFTER
        );

        $i = 0;
        foreach($requiredParams as $param)
        {
            $value = $requestParams[$i];
            if(($param == "conditional") && !in_array($value, $conditional))
            {
                $invalid_params[] = $param;
            }
            if(($param == "units") && !in_array($value, $units))
            {
                $invalid_params[] = $param;
            }
            if(($param == "option") && (!is_numeric($value) || (($value < 0) || ($value > 3))))
            {
                $invalid_params[] = $param;
            }
            if(($param == "outcome") && (!is_numeric($value) || (($value < 0) || ($value > 1))))
            {
                $invalid_params[] = $param;
            }
            $i++;
        }
        if (!empty($invalid_params))
        {
            $invalid_params = implode(", ", $invalid_params);
            throw new Exceptions\OntraportAPIException("Invalid inputs for $invalid_params. " .
            "Refer to the API Doc to make sure your rule parameters are valid and in the correct order.");
        }
        return true;
    }

    private void _formatParams($requestParams, $delimiter = ",")
    {
        $formatted = "";
        foreach($requestParams as $param)
        {
            $formatted = $formatted . $param . $delimiter;
        }
        $formatted = rtrim($formatted, $delimiter);
        return $formatted;
    }

    private void _parseParams($rule)
    {
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

        return $parsed;
    }
    private void _splitRule($init_rule)
    {
        $rules = array();
        $init_rule = str_replace("|", ";", $init_rule);
        $rules = explode(";", $init_rule);
        foreach($rules as $key => $rule)
        {
            $rules[$key] = trim($rule);
        }
        return $rules;
    }

    private void _operatorClassifier($init_conditions)
    {
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
        return $operators;
    }
}

*/

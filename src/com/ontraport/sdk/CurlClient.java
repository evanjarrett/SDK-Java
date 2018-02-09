package com.ontraport.sdk;

import com.ontraport.sdk.exceptions.HttpMethodException;
import com.ontraport.sdk.exceptions.RequiredParamsException;

import java.util.HashMap;
import java.util.HashSet;

public class CurlClient {

    public static final int HTTP_OK = 200;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_RATE_LIMIT = 429;
    public static final int HTTP_ERROR = 500;

    public static final String RATE_LIMIT_RESET = "x-rate-limit-reset";

    private HashMap<String, String> _requestHeaders;

    private int _lastStatusCode;

    public CurlClient(String apiKey, String siteID) {
        setRequestHeader("Api-Key", apiKey);
        setRequestHeader("Api-Appid", siteID);
    }

    public void setRequestHeader(String header, String value) {
        _requestHeaders.put(header, header + ": " + value);
    }

    public HashMap getRequestHeaders() {
        return _requestHeaders;
    }

    private HashSet<String> getWhitelistedRequestTypes() {
        HashSet<String> types = new HashSet<>();
        types.add("get");
        types.add("post");
        types.add("put");
        types.add("delete");
        return types;
    }

    public int getLastStatusCode() {
        return _lastStatusCode;
    }

    public void setLastStatusCode(int statusCode) {
        _lastStatusCode = statusCode;
    }

    private boolean validateRequest(HashMap<String, String> requestParams, HashSet<String> requiredParams, String method) throws HttpMethodException, RequiredParamsException {
        HashSet<String> allowedMethods = getWhitelistedRequestTypes();

        if (!allowedMethods.contains(method)) {
            throw new HttpMethodException(method);
        }

        HashSet<String> missingParams = checkRequiredParams(requestParams, requiredParams);
        if (missingParams.size() > 0) {
            String params = String.join(",", missingParams);
            throw new RequiredParamsException(params);
        }

        return true;
    }

    private HashSet<String> checkRequiredParams(HashMap<String, String> requestParams, HashSet<String> requiredParams) {
        HashSet<String> missingParams = new HashSet<>();

        for (String requiredParam : requiredParams) {
            if (!requestParams.containsValue(requiredParam)) {
                // Covers special case: when ids is required, group_ids can be substituted
                if (requiredParam.equals("ids")) {
                    if (!requestParams.containsKey("group_ids")) {
                        missingParams.add(requiredParam);
                    }
                }
                else {
                    missingParams.add(requiredParam);
                }
            }
        }

        return missingParams;
    }

    public String httpRequest(String[] requestParams, String url, String method, String[] requiredParams, String[] options) {
        return "";
    }

   /* public void httpRequest(String[] requestParams, String url, String method, String[] requiredParams, String[] options) {
        if (!_validateRequest(requestParams, requiredParams, method)) {
            return false;
        }

        if (options && is_array(options)) {
            if (array_key_exists("headers", options)) {
                foreach(options["headers"]as header = > value)
                {
                    _setRequestHeader(header, value);
                }
            }
        }

        if (array_key_exists("Content-Type", _requestHeaders) && _requestHeaders["Content-Type"] == "Content-Type: application/json") {
            requestParams = json_encode(requestParams);
        }

        if (is_array(requestParams)) {
            requestParams = http_build_query(requestParams);
        }

        curlHandle = curl_init();
        headers = array();
        switch (strtolower(method)) {
            case "post":
                curl_setopt(curlHandle, CURLOPT_POST, 1);
                curl_setopt(curlHandle, CURLOPT_POSTFIELDS, requestParams);
                break;

            case "get":
                curl_setopt(curlHandle, CURLOPT_HTTPGET, 1);
                url = url. "?".requestParams;
                break;

            case "put":
                curl_setopt(curlHandle, CURLOPT_CUSTOMREQUEST, "PUT");
                curl_setopt(curlHandle, CURLOPT_POSTFIELDS, requestParams);
                break;

            case "delete":
                curl_setopt(curlHandle, CURLOPT_CUSTOMREQUEST, "DELETE");
                if (array_key_exists("Content-Type", _requestHeaders) && _requestHeaders["Content-Type"] == "Content-Type: application/json") {
                    curl_setopt(curlHandle, CURLOPT_POSTFIELDS, requestParams);
                }
                else {
                    url = url. "?".requestParams;
                }
                break;
        }

        curl_setopt(curlHandle, CURLOPT_URL, url);
        curl_setopt(curlHandle, CURLOPT_HTTPHEADER, _requestHeaders);
        curl_setopt(curlHandle, CURLOPT_RETURNTRANSFER, true);
        curl_setopt(curlHandle, CURLOPT_HEADER, false);
        curl_setopt(curlHandle, CURLOPT_HEADERFUNCTION,
                function(curl, header)use( & headers)
        {
            len = strlen(header);
            header = explode(":", header, 2);
            if (count(header) < 2) {
                return len;
            }

            name = strtolower(trim(header[0]));
            headers[name] = trim(header[1]);

            return len;
        }
        );
        curl_setopt(curlHandle, CURLOPT_TIMEOUT, 60);

        result = curl_exec(curlHandle);

        _lastStatusCode = curl_getinfo(curlHandle, CURLINFO_HTTP_CODE);
        if (_lastStatusCode == CurlClient::HTTP_RATE_LIMIT) {
            result = retry(headers[self::RATE_LIMIT_RESET], curlHandle);
        }

        curl_close(curlHandle);

        unset(_requestHeaders["Content-Type"]);
        return result;
    }


   public String retry(wait, curlHandle) {
        sleep(wait);
        result = curl_exec(curlHandle);
        _lastStatusCode = curl_getinfo(curlHandle, CURLINFO_HTTP_CODE);
        return result;
    }
*/
}

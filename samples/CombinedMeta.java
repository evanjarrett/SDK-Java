import com.ontraport.sdk.http.Meta;

public class CombinedMeta {
    private Meta _meta;
    private CustomObjectResponse _co_res;

    public CombinedMeta() {
    }

    public CombinedMeta(Meta meta, CustomObjectResponse co_res) {
        _meta = meta;
        _co_res = co_res;
    }

    public Meta getMeta() {
        return meta;
    }

    public CustomObjectResponse getCustomObjectResponse() {
        return co_res;
    }
}

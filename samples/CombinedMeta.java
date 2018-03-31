import com.ontraport.sdk.http.Meta;

public class CombinedMeta {
    private Meta meta;
    private CustomObjectResponse co_res;

    public CombinedMeta() {
    }

    public CombinedMeta(Meta meta, CustomObjectResponse co_res) {
        this.meta = meta;
        this.co_res = co_res;
    }

    public Meta getMeta() {
        return meta;
    }

    public CustomObjectResponse getCustomObjectResponse() {
        return co_res;
    }
}

package net.devmobility.lolloader;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import net.devmobility.lolloader.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LolCatTotalRequest extends Request<JSONObject> {

    private Response.Listener<String> listener;

    public LolCatTotalRequest(String searchTerm,
                              Response.Listener<String> reponseListener,
                              Response.ErrorListener errorListener) {
        super(Request.Method.GET,
                SearchRequestBuilder.builder().searchTag(searchTerm).page(1).build().toString(),
                errorListener);
        this.listener = reponseListener;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        try {
            JSONObject j = response != null ? response.getJSONObject(Constants.PHOTOS) : null;
            listener.onResponse(j != null ? j.getString(Constants.TOTAL) : null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


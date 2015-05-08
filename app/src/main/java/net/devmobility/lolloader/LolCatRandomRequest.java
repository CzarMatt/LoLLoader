package net.devmobility.lolloader;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import net.devmobility.lolloader.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LolCatRandomRequest extends Request<JSONObject> {

    String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&" +
            "api_key=ee2b515275ae5273017da4a6a069f925&tags=lolcat&per_page=5&page=1&format=json&nojsoncallback=1";

    private Response.Listener<String> listener;

    public LolCatRandomRequest(String url,
                              Response.Listener<String> reponseListener,
                              Response.ErrorListener errorListener) {
        super(Request.Method.GET, url, errorListener);
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
            Log.i("VOLLEY", "response = " + response);
            JSONObject j = response != null ? response.getJSONObject("photos") : null;
            listener.onResponse(j.getString(Constants.TOTAL));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


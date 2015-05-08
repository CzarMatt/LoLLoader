package net.devmobility.lolloader;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import net.devmobility.lolloader.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LolCatRandomRequest extends Request<JSONObject> {

    String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&" +
            "api_key=ee2b515275ae5273017da4a6a069f925&tags=lolcat&per_page=5&page=1&format=json&nojsoncallback=1";

    private Response.Listener<Photo> listener;

    public LolCatRandomRequest(String searchTerm, int seed,
                              Response.Listener<Photo> reponseListener,
                              Response.ErrorListener errorListener) {
        super(Request.Method.GET,
                SearchRequestBuilder.builder().searchTag(searchTerm).perPage(1).page(seed).build().toString(),
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
            JSONObject j = response != null ? response.getJSONObject("photos") : null;
            JSONArray data = j.getJSONArray("photo");
            JSONObject photo = data.getJSONObject(0);
            Photo p = new Photo(photo.getString("farm"), photo.getString("server"), photo.getString("id"), photo.getString("secret"));
            listener.onResponse(p);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


package net.devmobility.lolloader;

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

    private final String title = "title";
    private final String farm = "farm";
    private final String server = "server";
    private final String id = "is";
    private final String secret = "secret";

    private Response.Listener<Photo> listener;

    public LolCatRandomRequest(String searchTerm, int page,
                              Response.Listener<Photo> reponseListener,
                              Response.ErrorListener errorListener) {
        super(Request.Method.GET,
                SearchRequestBuilder.builder().searchTag(searchTerm).page(page).build().toString(),
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
            JSONArray data = j != null ? j.getJSONArray(Constants.PHOTO) : null;
            JSONObject photo = data != null ? data.getJSONObject(generateRandom(1, 100)) : null; // 100 is default per page
            assert photo != null;
            Photo p = new Photo(photo.getString(farm), photo.getString(server), photo.getString(id), photo.getString(secret));
            p.setTitle(photo.getString(title));
            listener.onResponse(p);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int generateRandom(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

}
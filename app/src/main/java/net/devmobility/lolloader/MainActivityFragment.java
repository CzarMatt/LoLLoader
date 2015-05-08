package net.devmobility.lolloader;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private static final String key = "ee2b515275ae5273017da4a6a069f925";
    private static final String secret = "b6cb6abce3e927c4";

    // farm id, server id, id, secret
    private static final String BASE_PHOTO_URL = "https://farm%s.staticflickr.com/7784/17394172705_c18ffe913d.jpg";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageButton b = (ImageButton) view.findViewById(R.id.imagebutton);
        b.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagebutton:
                Toast.makeText(getActivity(), "Meow!", Toast.LENGTH_SHORT).show();

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&" +
                        "api_key=ee2b515275ae5273017da4a6a069f925&tags=lolcat&per_page=5&page=1";

                // id, owner, secret, server, farm
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("VOLLEY", "Response is: " + response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("VOLLEY", "That didn't work!", error);
                    }
                });

                queue.add(stringRequest);

                // TODO: refresh image here
                break;
        }
    }

    private String buildPhotoUri(String farmId, String serverId, String id, String secret) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(String.format("farm%s.staticflickr.com", farmId))
                .appendPath(serverId)
                .appendPath(String.format("%s_%s", id, secret));
        return builder.build().toString();
    }

}

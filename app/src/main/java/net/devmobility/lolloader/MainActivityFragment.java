package net.devmobility.lolloader;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class MainActivityFragment extends Fragment implements View.OnClickListener,
        Response.ErrorListener {

    private final String searchTerm = "lolcat";

    // farm id, server id, id, secret
    private static final String BASE_PHOTO_URL = "https://farm%s.staticflickr.com/7784/17394172705_c18ffe913d.jpg";

    private static int TOTAL = 0;

    private ProgressDialog progressDialog;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = ProgressDialog.show(getActivity(), "Initializing", "Herding cats...", true);
        initialize();
    }

    private void initialize() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TOTAL = Integer.parseInt(response);
                Log.i("VOLLEY", "response TOTAL = " + TOTAL);
                progressDialog.dismiss();
            }
        };
        LolCatTotalRequest r = new LolCatTotalRequest(searchTerm, listener, this);
        queue.add(r);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagebutton:
                Toast.makeText(getActivity(), "Meow!", Toast.LENGTH_SHORT).show();

                // Instantiate the RequestQueue.
                // NO JSONCALLBACK!!#%$@#$!#~
                //RequestQueue queue = Volley.newRequestQueue(getActivity());
                //String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&" +
                //        "api_key=ee2b515275ae5273017da4a6a069f925&tags=lolcat&per_page=5&page=1&format=json&nojsoncallback=1";

                // id, owner, secret, server, farm
                //StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this, this);

                //queue.add(stringRequest);

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


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("VOLLEY", "That didn't work!", error);
        progressDialog.dismiss();
    }

}

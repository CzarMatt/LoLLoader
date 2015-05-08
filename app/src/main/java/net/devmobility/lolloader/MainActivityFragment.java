package net.devmobility.lolloader;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class MainActivityFragment extends Fragment implements View.OnClickListener,
        Response.ErrorListener, UiListener {

    private final String searchTerm = "lolcat";

    private static int TOTAL = 0;

    private ProgressDialog progressDialog;
    private ImageButton catButton;
    private TextView catText;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        catButton = (ImageButton) view.findViewById(R.id.imagebutton);
        catButton.setOnClickListener(this);
        catText = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Initializing");
        progressDialog.show();
        initialize();
    }

    private void initialize() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TOTAL = Integer.parseInt(response);
                Log.d("VOLLEY", "response TOTAL = " + TOTAL);
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
                catButton.setImageDrawable(null);
                catText.setText("");

                progressDialog.setMessage("Cat incoming...");
                progressDialog.show();

                RequestQueue queue = Volley.newRequestQueue(getActivity());
                Response.Listener<Photo> listener = new Response.Listener<Photo>() {
                    @Override
                    public void onResponse(Photo p) {
                        // retrieve photo data here
                        Log.d("onClick", "Retrived photo!  :  " + p.getId());
                        new Downloader(getActivity(), progressDialog, MainActivityFragment.this).execute(
                                String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg",
                                        p.getFarmId(),
                                        p.getServerId(),
                                        p.getId(),
                                        p.getSecret()), p.getTitle());

                    }
                };

                int page = generateRandom(1, TOTAL);
                Log.d("onClick", "randomNum: " + page);
                // Generate a random number representing a single result from
                // the previously gathered total number of json objects and
                //make a request for photo data.
                queue.add(new LolCatRandomRequest(searchTerm, page, listener, this));

                break;
        }
    }

    private int generateRandom(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("VOLLEY", "That didn't work!", error);
        progressDialog.dismiss();
    }

    @Override
    public void updateUi(Bitmap b, String s) {
        progressDialog.dismiss();
        catButton.setImageBitmap(b);
        catText.setText(s);
    }

}

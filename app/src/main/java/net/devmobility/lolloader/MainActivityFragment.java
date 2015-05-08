package net.devmobility.lolloader;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

    private static int TOTAL = -1;

    private ProgressDialog progressDialog;
    private Bundle bundle;
    private ImageButton catButton;
    private TextView catText;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        catButton = (ImageButton) view.findViewById(R.id.imagebutton);
        catText = (TextView) view.findViewById(R.id.textview);
        if (savedInstanceState != null) {
            catButton.setImageBitmap((Bitmap) savedInstanceState.get("image"));
            catText.setText((String) savedInstanceState.get("text"));
            TOTAL = (int) savedInstanceState.get("total");
        }
        catButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (TOTAL == -1) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Initializing");
            progressDialog.show();
            initialize();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("image", ((BitmapDrawable) catButton.getDrawable()).getBitmap());
        outState.putString("text", catText.getText().toString());
        outState.putInt("total", TOTAL);
        super.onSaveInstanceState(outState);
    }

    private void initialize() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TOTAL = Integer.parseInt(response);
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
                        new Downloader(getActivity(), progressDialog, MainActivityFragment.this).execute(
                                String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg",
                                        p.getFarmId(),
                                        p.getServerId(),
                                        p.getId(),
                                        p.getSecret()), p.getTitle());

                    }
                };

                // Generate a random number representing a single result from
                // the previously gathered total number of json objects and
                //make a request for photo data.
                queue.add(new LolCatRandomRequest(searchTerm, generateRandom(1, TOTAL), listener, this));

                break;
        }
    }

    private int generateRandom(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.dismiss();
    }

    @Override
    public void updateUi(Bitmap b, String s) {
        progressDialog.dismiss();
        catButton.setImageBitmap(b);
        catText.setText(s);
    }

}
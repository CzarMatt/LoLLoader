package net.devmobility.lolloader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.REST;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import org.json.JSONException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private static final String key = "ee2b515275ae5273017da4a6a069f925";
    private static final String secret = "b6cb6abce3e927c4";

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
                Flickr flickr = null;
                try {
                    flickr = new Flickr(key, secret, new REST());
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

                SearchParameters searchParameters = new SearchParameters();
                searchParameters.setTags(new String[]{"lolcat"});

                List<Photo> list = null;
                try {
                    list = flickr.getPhotosInterface().search(searchParameters, 100, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FlickrException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("REMOVE", "list = " + list);

                // TODO: refresh image here
                break;
        }
    }

}

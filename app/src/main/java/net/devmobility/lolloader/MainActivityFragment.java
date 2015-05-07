package net.devmobility.lolloader;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

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

                // TODO: refresh image here
                break;
        }
    }

}

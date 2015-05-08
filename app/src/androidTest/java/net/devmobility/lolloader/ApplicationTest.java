package net.devmobility.lolloader;

import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ImageButton;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivityFragment fragment;
    private MainActivity activity;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

        fragment = new MainActivityFragment();

        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, "main");
        transaction.commit();

    }

    // Validate the intent is fired on button press with correct result from
    // text field
    @SmallTest
    public void testJsonResponse() {

        final ImageButton button = (ImageButton) activity.findViewById(R.id.imagebutton);
        assertNotNull("This image button shouldn't be null", button);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });


    }

}


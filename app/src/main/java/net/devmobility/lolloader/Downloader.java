package net.devmobility.lolloader;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

public class Downloader extends AsyncTask<String, String, Bitmap> {
    private Context context;
    private Bitmap catImage;
    private String title;
    private WeakReference<Bitmap> mBitmapReference;
    private UiListener uiListener;

    public Downloader(Context c, ProgressDialog p, UiListener l) {
        this.context = c;
        this.uiListener = l;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Bitmap doInBackground(String... args) {
        try {
            catImage = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            mBitmapReference = new WeakReference<>(catImage);
            title = args[1];
        } catch (Exception e) {
            mBitmapReference = new WeakReference<>(BitmapFactory.decodeResource(context.getResources(), R.drawable.temp));
        }
        return catImage;
    }

    protected void onPostExecute(Bitmap image) {
        Toast.makeText(context, "Meow!", Toast.LENGTH_SHORT).show();
        if (image != null) {
            if (uiListener != null) {
                uiListener.updateUi(mBitmapReference.get(), title);
            }
        } else {
            if (uiListener != null) {
                uiListener.updateUi(mBitmapReference.get(), "Me-owth! There's been an error!");
            }
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

        }
    }
}

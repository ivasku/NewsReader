package com.tms.template;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Adrian on 2/15/2016.
 */

public class ListGenericActivity extends AppCompatActivity {

    TextView dynamicText;
    Utils util = new Utils();
    ProgressBarHandler mProgressBarHandler;
    private Document doc;
    List<String> list;
    public static ListGenericActivity _Instance;

    public ListGenericActivity(){
        // I should make this constructor to take parameters, eg links even layput changes.... but to use this class for all news pages.
        // create new object of this class with diferent parameters and get the news I need
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _Instance = this;
        setContentView(R.layout.list_generic_layout);
        mProgressBarHandler = new ProgressBarHandler(this);
    }

    protected  void onStart() {
        super.onStart();
        new Task().execute();
    }

    protected void onPause(){
        super.onPause();
    }

    protected void onResume(){
        super.onResume();

    }

    private class Task extends AsyncTask<Void, Void, Void>{
        String linkText;
        String img1Link1;
        String img1Link11;
        String img1Link1Clean;
        ImageView imagev;

        @Override
        protected Void doInBackground(Void... params) {
            list = TabFragment1._Instance.getDocumentLinks();
            doc = util.GetDocument("http://www.istinomer.rs/" + list.get(0));
            linkText = util.GetElement("div.text", MainActivity._Instance.mainLink + list.get(0), 0 , doc);
            imagev = (ImageView) findViewById(R.id.imageView2);

            //this is a little crappy, needs optimization
            img1Link1 = util.GetElement(
                    "img.thumb:nth-child(1)", "http://www.istinomer.rs/" + list.get(0), 4, doc);
            img1Link11 = "http://www.istinomer.rs/" + img1Link1;
            img1Link1Clean = img1Link11.replaceAll("\\s+","");

            dynamicText = (TextView) findViewById(R.id.textView2);
            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            dynamicText.setMovementMethod(new ScrollingMovementMethod());
            dynamicText.setText(linkText);
            Picasso.with(getBaseContext()).load(img1Link1Clean).into(imagev);
            mProgressBarHandler.hide();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBarHandler.show();
        }
    }

}

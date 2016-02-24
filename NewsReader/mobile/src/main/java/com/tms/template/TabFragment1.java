package com.tms.template;

/**
 * Created by Adrian on 2/14/2016.
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.List;


public class TabFragment1 extends Fragment {
    private View inflatedView = null;
    private ProgressBarHandler mProgressBarHandler;

    public static TabFragment1 _Instance;
    Utils util = new Utils();

    private Document doc;
    private List<Bitmap> images;
    private String[] HeadlinesDescription;
    private String[] Headlines = {};

    public List getDocumentLinks(){
        List<String> links = new ArrayList<String>();
        String link = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > h1:nth-child(3) > a:nth-child(1)", "http://www.istinomer.rs/", 3, doc);
        links.add(link);
        return links;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _Instance = this;
        inflatedView = inflater.inflate(R.layout.tab_fragment_1, container, false);
        mProgressBarHandler = new ProgressBarHandler(getActivity());

        Task asyncTask1 = new Task();
        StartAsyncTaskInParallel(asyncTask1);
        Task asyncTask2 = new Task();
        StartAsyncTaskInParallel(asyncTask2);

        ListView list = (ListView) inflatedView.findViewById(R.id.listView1);
        CustomAdapter cus = new CustomAdapter(getActivity(), Headlines, HeadlinesDescription, images);
        list.setAdapter(cus);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.v("TMS", "List Item Click" + position);
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), ListGenericActivity.class);
                    startActivity(intent);
                }
            }
        });

        return inflatedView;
    }

    // starts AsyncTask in parallel
    private void StartAsyncTaskInParallel(Task task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }

    //redraw listView after the fragment is loaded via AsyncTask
    public void redraw() {
        ListView list = (ListView) inflatedView.findViewById(R.id.listView1);
        CustomAdapter cus = new CustomAdapter(getActivity(), Headlines, HeadlinesDescription, images);
        list.setAdapter(cus);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //Log.v("TMS", "List Item Click" + position);
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), ListGenericActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /*
    populate all variables and lists with data from CSS selector passed to GetElement method
    */
    private void Initdata(){
        loadImages();

        //Basic array initialization and the population of those lists
        Headlines = new String[] {util.GetElement("div.item-2:first-child", "http://www.istinomer.rs/", 0, doc),
                util.GetElement("div.item-2:nth-child(2)", "http://www.istinomer.rs/", 0 , doc),
                util.GetElement("div.item-2:nth-child(3)", "http://www.istinomer.rs/", 0, doc),
                util.GetElement("div.item-2:nth-child(4)", "http://www.istinomer.rs/",0 , doc),
                util.GetElement("div.item-2:nth-child(5)", "http://www.istinomer.rs/",0 , doc),
                util.GetElement("div.item-2:nth-child(6)", "http://www.istinomer.rs/",0, doc),
                util.GetElement("div.item-2:nth-child(7)", "http://www.istinomer.rs/",0, doc),
                util.GetElement("div.item-2:nth-child(8)", "http://www.istinomer.rs/",0, doc),
                util.GetElement("div.item-2:nth-child(9)", "http://www.istinomer.rs/",0, doc),
                util.GetElement("div.item-2:nth-child(10)", "http://www.istinomer.rs/",0, doc)
        };

        String vestiDescription1 = util.GetElement("div.item-big h2", "http://www.istinomer.rs/", 2 , doc) + System.getProperty("line.separator")
                + util.GetElement("div.item-big h3","http://www.istinomer.rs/",2, doc);

        String vestiDescription2 = util.GetElement("div.grid-8 h2 a", "http://www.istinomer.rs/", 2, doc) + System.getProperty("line.separator")
                + util.GetElement("div.grid-8 h3", "http://www.istinomer.rs/", 2, doc);

        String vestiDescription3 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(4) > div:nth-child(2) > h3:nth-child(2)", "http://www.istinomer.rs/", 0, doc);

        String vestiDescription4 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(5) > div:nth-child(2) > h3:nth-child(2)", "http://www.istinomer.rs/", 0, doc);

        String vestiDescription5 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(6) > div:nth-child(2) > h3:nth-child(2)", "http://www.istinomer.rs/", 0, doc);

        String vestiDescription6 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(2) > h2:nth-child(4)", "http://www.istinomer.rs/", 0, doc) + System.getProperty("line.separator")
                + util.GetElement("div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(2) > h3:nth-child(5)", "http://www.istinomer.rs/", 2, doc);

        String vestiDescription7 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > h3:nth-child(2)", "http://www.istinomer.rs/", 0, doc);

        String vestiDescription8 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(4) > div:nth-child(2) > h3:nth-child(2)", "http://www.istinomer.rs/", 0, doc);

        String vestiDescription9 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(5) > div:nth-child(2) > h3:nth-child(2)", "http://www.istinomer.rs/", 0, doc);

        String vestiDescription10 = util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(6) > div:nth-child(2) > h3:nth-child(2)", "http://www.istinomer.rs/", 0, doc);

        HeadlinesDescription = new String[]{
                vestiDescription1,
                vestiDescription2,
                vestiDescription3,
                vestiDescription4,
                vestiDescription5,
                vestiDescription6,
                vestiDescription7,
                vestiDescription8,
                vestiDescription9,
                vestiDescription10
        };
    }


    private void loadImages(){

        String img1Link1 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > img:nth-child(1)", "http://www.istinomer.rs/", 4, doc);

        String img1Link2 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        String img1Link3 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        String img1Link4 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        String img1Link5 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        String img1Link6 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > img:nth-child(1)", "http://www.istinomer.rs/", 4, doc);

        String img1Link7 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        String img1Link8 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        String img1Link9 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        String img1Link10 = MainActivity._Instance.mainLink + util.GetElement(
                "div.gd-container-1:nth-child(6) > div:nth-child(5) > div:nth-child(1) > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)", "http://www.istinomer.rs/", 4, doc);

        //Log.d("TMS","Image is: " + img1Link2);
       /* images = new Bitmap[]{util.getImageBitmap(img1Link1),
                util.getImageBitmap(img1Link2),
                util.getImageBitmap(img1Link3),
                util.getImageBitmap(img1Link4),
                util.getImageBitmap(img1Link5),
                util.getImageBitmap(img1Link6),
                util.getImageBitmap(img1Link7),
                util.getImageBitmap(img1Link8),
                util.getImageBitmap(img1Link9),
                util.getImageBitmap(img1Link10)};*/

        images = new ArrayList<Bitmap>();
        images.add(0,util.getImageBitmap(img1Link1));
        images.add(1,util.getImageBitmap(img1Link2));
        images.add(2,util.getImageBitmap(img1Link3));
        images.add(3,util.getImageBitmap(img1Link4));
        images.add(4,util.getImageBitmap(img1Link5));
        images.add(5,util.getImageBitmap(img1Link6));
        images.add(6,util.getImageBitmap(img1Link7));
        images.add(7,util.getImageBitmap(img1Link8));
        images.add(8,util.getImageBitmap(img1Link9));
        images.add(9,util.getImageBitmap(img1Link10));

    }

    /*
    Async task for loading data from the WEB
    in a async task so the UI thread will not be blocked
    while data is beign fetched
     */
    private class Task extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            doc = util.GetDocument("http://www.istinomer.rs/");
            Initdata();
            getDocumentLinks();
            return null;
        }
        @Override
        protected void onPostExecute(Void param) {
            mProgressBarHandler.hide();
            redraw();
            inflatedView.invalidate();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBarHandler.show();
        }
    }
}

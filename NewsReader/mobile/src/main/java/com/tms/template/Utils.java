package com.tms.template;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Adrian on 2/21/2016.
 */
public class Utils {
    private String TAG = "TMS";

    /*
    returns bitmap of an image URL
    url = image link location
     */
    public Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            // See what we are getting
            //Log.i(TAG, "" + url);
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);

            bis.close();
            is.close();
        } catch (Exception e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }

    /*
    Element = CSS selector
    site = Web page you need data from
    mode 0 = get standard element
    mode 1 = get standard element in String form without deleting the HTML tags
    mode 2 = get standard FIRST element from array
    mode 3 = using attribute HREF to extract link path from HTML element
    mode 4 = get image SRC , just provide css selector directly to id
    doc = Document object that you can get from GetDocument()
    */
    public String GetElement(String Element, String site, int mode , Document doc) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String returnData = null;
            Elements newsHeadlines = null;
            org.jsoup.nodes.Element newsHeadlines3 = null;
            Elements newsHeadlines2 = null;

            if (mode == 0) {
                newsHeadlines = doc.select(Element);
                returnData = html2text(newsHeadlines.toString());
            }
            //1 gets link from class
            else if (mode == 1) {
                String link = doc.select(Element).toString();
                return link;
            }
            else if (mode == 2) {
                newsHeadlines3 = doc.select(Element).first();
                returnData = html2text(newsHeadlines3.toString());
            }
            else if (mode == 3) {
                newsHeadlines2 = doc.select(Element);
                String data5 = newsHeadlines2.attr("href");
                return data5;
            }
            else if (mode == 4) {
                Elements newsHeadlines4 = doc.select(Element + "img[src]");
                String data7 = newsHeadlines4.attr("src");
                return data7;
            }
            return returnData;
        }
        catch (Exception e) {
            Log.d("TMS", "EXCEPTION GetElement: " + Element);
            e.printStackTrace();
            return "Error";
        }
    }

    /*
    GetDocument returns the Document object which contains the data from the whole webPage you connected to.
    All queries you do, you should iterate thought this document object.
    If you want to refresh the site data, just retrieve the new Document object.
     */
    public Document GetDocument(String site) {
        try {
           /* StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);*/
            Elements newsHeadlines = null;
            Document doc = Jsoup.connect(site).timeout(600000).get();
            return doc;
        }
        catch (Exception e) {
            Log.d("TMS", "Error parsing page " + site);
            e.printStackTrace();
            return null;
        }
    }

    /*
    Delete html tags aroung the text
     */
    private static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}

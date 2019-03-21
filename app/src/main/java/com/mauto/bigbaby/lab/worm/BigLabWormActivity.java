package com.mauto.bigbaby.lab.worm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.tools.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BigLabWormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_worm_main_activity);
    }

    /////////////////////////////////////////--> 18-5-18 下午3:13 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> check version from GP <-- ↓↓↓/////////////////////////////////////
    public interface Callback{
        void exc(String version);
    }

    private boolean checkDone = true;
    public void checkVersion(final String marketSite, final Callback action){
        if (!checkDone)
            return;
        checkDone = false;
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return checkVersion(marketSite);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                action.exc(s);
                checkDone = true;
            }

            @Override
            public void onError(Throwable e) {
                action.exc("");
                checkDone = true;
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0";

    public String checkVersion(String marketSite) throws IOException {
        long mil = System.currentTimeMillis();
        StringBuilder response = new StringBuilder();
        URL url = new URL(marketSite);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        BufferedReader in = null;

        con.setReadTimeout(30 * 1000);// 30s
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        String latestVersion = "";
        while( ( line = in.readLine() ) != null ){
            response.append(line);
        }
        Document doc = Jsoup.parse(response.toString(), marketSite);
        Element versionBlock = doc.select("div[class*=\"xyOfqd\"]").first();
        if (versionBlock != null){
            Element versionPanel = versionBlock.child(2);
            if (versionPanel != null){
                Element versionItem = versionPanel.select("span[class*=\"htlgb\"]").last();
                if (versionItem != null)
                    latestVersion = versionItem.text();
            }
        }
        Log.e("--> version checker <--", "" + Utils.formatTime((System.currentTimeMillis() - mil), "ss"));
        return latestVersion;
    }
    /////////////////////////////////////↑↑↑ --> check version from GP <-- ↑↑↑/////////////////////////////////////

}

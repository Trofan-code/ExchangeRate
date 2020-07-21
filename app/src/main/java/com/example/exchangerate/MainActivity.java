package com.example.exchangerate;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Document doc;
    private  Thread secThread;
    private  Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init(){
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();

    }
    private void getWeb(){
        try {
            doc = Jsoup.connect("https://www.777.com.pl/").get();
            Elements tables =doc.getElementsByTag("tbody");
            Element our_table = tables.get(1);
            Elements elements_from_table = our_table.children();
            Element table_title = elements_from_table.get(0);
            Elements table_title_elements = table_title.children();

            Log.d("MyLog","AAAAAAAAAAAAAAAAAAA    :    " + table_title_elements.get(3).text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    Button button_diag;
    Button button_fa;

    TextView textView_shortWeather;
    TextView helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_diag = (Button) findViewById(R.id.button_diagnosis);
        button_fa = (Button) findViewById(R.id.button_first_aid);

        button_diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DiagnosisActivity.class);
                startActivity(intent);
            }
        });

        button_fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FirstAidListActivity.class);
                startActivity(intent);
            }
        });

        textView_shortWeather = (TextView)findViewById(R.id.shortWeather);
        helper = findViewById(R.id.helper);

        new ReceiveShortWeather().execute();
    }

    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {

        ArrayList<ShortWeather> shortWeathers = new ArrayList<ShortWeather>();

        protected Long doInBackground(URL... urls) {

            String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159068000";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
                parseXML(response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Long result) {
            String data = "";

//            for(int i=0; i<shortWeathers.size(); i++) {
//                data += shortWeathers.get(i).getDay() + "일 " +
//                        shortWeathers.get(i).getHour() + "시 " +
//                        shortWeathers.get(i).getTemp() + "도 " +
//                        shortWeathers.get(i).getWfKor() + " " +
//                        shortWeathers.get(i).getPop() + "\n";
//            }

            String rain = "";
            if (shortWeathers.get(0).getPty() == "0"){
                rain = "없음";
            }
            else if(shortWeathers.get(0).getPty() == "1"){
                rain = "비";
            }
            else if(shortWeathers.get(0).getPty() == "2"){
                rain = "비/눈";
            }
            else if(shortWeathers.get(0).getPty() == "3"){
                rain = "눈";
            }
            else{
                rain = "없음";
            }
            data += String.format("%20s  %10s\n","기온",shortWeathers.get(0).getTemp())+
                    String.format("%20s  %10s\n","강수 확률",shortWeathers.get(0).getPop())+
                    String.format("%20s  %10s\n","습도",shortWeathers.get(0).getReh())+
                    String.format("%20s  %10s\n","구름",shortWeathers.get(0).getWfKor())+
                    String.format("%20s  %10s\n","강수상태",rain)+
                    String.format("%20s  %10s\n","미세먼지","")+
                    String.format("%20s  %10s","초미세먼지","");

            String info = "";
            if(Double.parseDouble(shortWeathers.get(0).getTemp()) >= 40){
                info += "날이 매우 덥습니다. 열사병을 조심하세요.\n";
            }
            if(Integer.parseInt(shortWeathers.get(0).getPop()) >= 0){
                info += "비 올 확률이 높아요! 우산을 챙겨주세요.\n";
            }

            String sky = shortWeathers.get(0).getWfKor();
            if(sky == "맑음"){
                textView_shortWeather.setBackgroundResource(R.drawable.sunny);
            }
            else if(sky == "구름 조금"){
                textView_shortWeather.setBackgroundResource(R.drawable.s_cloud);
            }
            else if(sky == "구름 많음" || sky == "흐림"){
                textView_shortWeather.setBackgroundResource(R.drawable.m_cloud);
            }
            else{
                textView_shortWeather.setBackgroundResource(R.drawable.sunny);
            }

            if (shortWeathers.get(0).getPty() == "1"){
                textView_shortWeather.setBackgroundResource(R.drawable.rain);
            }
            helper.setText(info);
            textView_shortWeather.setText(data);
        }

        void parseXML(String xml) {
            try {
                String tagName = "";
                boolean onHour = false;
                boolean onDay = false;
                boolean onTem = false;
                boolean onWfKor = false;
                boolean onPop = false;
                boolean onEnd = false;
                boolean isItemTag1 = false;
                boolean onPty = false;
                int i = 0;

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        tagName = parser.getName();
                        if (tagName.equals("data")) {
                            shortWeathers.add(new ShortWeather());
                            onEnd = false;
                            isItemTag1 = true;
                        }
                    } else if (eventType == XmlPullParser.TEXT && isItemTag1) {
                        if (tagName.equals("hour") && !onHour) {
                            shortWeathers.get(i).setHour(parser.getText());
                            onHour = true;
                        }
                        if (tagName.equals("day") && !onDay) {
                            shortWeathers.get(i).setDay(parser.getText());
                            onDay = true;
                        }
                        if (tagName.equals("temp") && !onTem) {
                            shortWeathers.get(i).setTemp(parser.getText());
                            onTem = true;
                        }
                        if (tagName.equals("wfKor") && !onWfKor) {
                            shortWeathers.get(i).setWfKor(parser.getText());
                            onWfKor = true;
                        }
                        if (tagName.equals("pop") && !onPop) {
                            shortWeathers.get(i).setPop(parser.getText());
                            onPop = true;
                        }
                        if (tagName.equals("pty") && !onPty) {
                            shortWeathers.get(i).setPty(parser.getText());
                            onPty = true;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (tagName.equals("s06") && onEnd == false) {
                            i++;
                            onHour = false;
                            onDay = false;
                            onTem = false;
                            onWfKor = false;
                            onPop = false;
                            isItemTag1 = false;
                            onEnd = true;
                            onPty = true;
                        }
                    }

                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

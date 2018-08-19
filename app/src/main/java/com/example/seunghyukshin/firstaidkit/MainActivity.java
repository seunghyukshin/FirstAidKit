package com.example.seunghyukshin.firstaidkit;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button_diag;
    Button button_fa;

    TextView textView_shortWeather;
    TextView helper;

    ImageView weather_background;

    private String dataTemp;
    private String dataPop;
    private String dataReh;
    private String dataWfKor;

    private String weather_data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 액션바 설정
        getSupportActionBar().setTitle("MainActivity");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                intent.putExtra("기온",dataTemp);
                intent.putExtra("강수 확률",dataPop);
                intent.putExtra("습도",dataReh);
                intent.putExtra("구름",dataWfKor);
                startActivity(intent);
            }
        });

        weather_background = findViewById(R.id.weather_background);
        textView_shortWeather = (TextView)findViewById(R.id.shortWeather);
        helper = findViewById(R.id.helper);



        new ReceiveShortWeather().execute();
        new ReceiveFineDust().execute();
    }

    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //액션버튼을 클릭했을때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Toast.makeText(this, "홈", Toast.LENGTH_SHORT).show();

            return true;
        }
        if (id == R.id.action_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class ReceiveFineDust extends AsyncTask<URL, Integer, Long>{
        String pm10 = "";
        String pm25 = "";
        String pm10value = "";
        String pm25value = "";

        protected Long doInBackground(URL... urls){
            String service_key = "tuRZOtYGn%2FJEHn9eJqUvaSv9zB5c2%2F53MTDYHIlNdaL%2BKenmHNrGc2ofsIsEytSgVs%2BZLhpkqlVsXsru%2BrfN6Q%3D%3D";

            String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="
                    + "종로구"
                    + "&dataTerm=daily"
                    + "&pageNo=1&numOfRows=10&ServiceKey="
                    + service_key + "&ver=1.3";

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
            String gradePm10 = getGrade(pm10);
            String gradePm25 = getGrade(pm25);

            String text = weather_data +
                    String.format("%s  %s\n","미세먼지",pm10value+"㎍/㎥ / "+gradePm10)+
                    String.format("%s  %s","초미세먼지",pm25value+"㎍/㎥ / "+gradePm25);

            textView_shortWeather.setText(text);
        }

        String getGrade(String pm){
            if(pm.equals("1")){
                return "좋음";
            }
            else if(pm.equals("2")){
                return "보통";
            }
            else if(pm.equals("3")){
                return "나쁨";
            }
            else if(pm.equals("4")){
                return "매우 나쁨";
            }
            return pm;
        }
        void parseXML(String xml) {
            try {
                String tagName = "";

                boolean onPm10 = true;
                boolean onPm25 = true;
                boolean onPm10value = true;
                boolean onPm25value = true;

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        tagName = parser.getName();
                        Log.i("FINE DUST TAG NAME :", tagName);
                    }
                    else if (eventType == XmlPullParser.TEXT) {
                        if (tagName.equals("pm10Grade1h") && onPm10) {
                            pm10 = parser.getText();
                            onPm10 = false;
                        }
                        if (tagName.equals("pm25Grade1h") && onPm25) {
                            pm25 = parser.getText();
                            onPm25 = false;
                        }
                        if (tagName.equals("pm10Value") && onPm10value) {
                            pm10value = parser.getText();
                            onPm10value = false;
                        }
                        if (tagName.equals("pm25Value") && onPm25value) {
                            pm25value = parser.getText();
                            onPm25value = false;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (tagName.equals("item") && onPm10 == false && onPm25 == false && onPm10value == false && onPm25value == false) {
                            break;
                        }
                    }

                    eventType = parser.next();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {

        ArrayList<ShortWeather> shortWeathers = new ArrayList<ShortWeather>();

        protected Long doInBackground(URL... urls) {

            String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3020054000";

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
            setWeather();
            setShowSky();
            setWeatherData();
        }

        void setShowSky(){
            String info = "";
            if(Double.parseDouble(shortWeathers.get(0).getTemp()) >= 40){
                info += "날이 매우 덥습니다. 열사병을 조심하세요.\n";
            }
            else if(Integer.parseInt(shortWeathers.get(0).getPop()) >= 50){
                info += "비 올 확률이 높아요! 우산을 챙겨주세요.\n";
            }
            else{
                info += "놀러나가기 좋은 날씨네요!\n";
            }


            String sky = shortWeathers.get(0).getWfKor();
            if(sky == "맑음"){
                weather_background.setBackgroundResource(R.drawable.sunny);
            }
            else if(sky == "구름 조금"){
                weather_background.setBackgroundResource(R.drawable.s_cloud);
            }
            else if(sky == "구름 많음" || sky == "흐림"){
                weather_background.setBackgroundResource(R.drawable.m_cloud);
            }
            else{
                weather_background.setBackgroundResource(R.drawable.sunny);
            }

            if (shortWeathers.get(0).getPty() == "1"){
                weather_background.setBackgroundResource(R.drawable.rain);
            }
            helper.setText(info);
        }

        void setWeather(){
            String data;
            String rain;

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

            data = String.format("%20s  %10s도\n","기온",shortWeathers.get(0).getTemp())+
                    String.format("%20s  %10s\n","강수 확률",shortWeathers.get(0).getPop() + "%")+
                    String.format("%20s  %10s\n","습도",shortWeathers.get(0).getReh() + "%")+
                    String.format("%20s  %10s\n","구름",shortWeathers.get(0).getWfKor())+
                    String.format("%20s  %10s\n","강수상태",rain);

            weather_data = data;
//            textView_shortWeather.setText(data);
        }
        //intent넘어갈때 넘겨줄 날씨정보
        void setWeatherData(){
            dataTemp = shortWeathers.get(0).getTemp();
            dataPop = shortWeathers.get(0).getPop();
            dataReh = shortWeathers.get(0).getReh();
            dataWfKor = shortWeathers.get(0).getWfKor();
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
                boolean onReh = false;
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
                        Log.i("WEATHER TAG NAME :", tagName);
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
                        if (tagName.equals("reh") && !onReh) {
                            shortWeathers.get(i).setReh(parser.getText());
                            onReh = true;
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

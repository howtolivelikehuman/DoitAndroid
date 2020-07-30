package org.techtown.doitmission_20;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class XMLParsing extends AsyncTask<Object, Object, ArrayList<News>> {

    //final String sturl = "https://media.daum.net/syndication/today_sisa.rss";
    String URLst;
    //https://junuda.tistory.com/25

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    public XMLParsing(String s){
        this.URLst = s;
    }

    // AsyncTask 실행하여 RSS 의 내용을 읽어오는 함수
    public ArrayList<News> getData() {
        ArrayList<News> list = new ArrayList<News>();
        try {
            list = execute().get();
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {}
        return list;
    }

    @Override
    protected ArrayList<News> doInBackground(Object... objects) {
        ArrayList<News> list = new ArrayList<News>();
        try {
            URL url = new URL(URLst);

            // RSS의 값들을 XmlParser에 연결.
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            parser.setInput(bis, null); //InputStream과 String을 인자로 받음.
            String tag="";
            String title="";
            String link="";
            String description="";
            String time="";
            boolean isItemTag = false;

            // getEventType()으로 문서의 시작과 끝 확인가능
            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT) { //문서가 끝날때까지 동작해라.

                switch (parserEvent) {
                    case XmlPullParser.START_TAG: // TAG 시작. 예시) <title> 태그
                        tag = parser.getName();
                        if(tag.equals("item")){ // news 들만 가져오기 위한 boolean 확인
                            isItemTag = true;
                        }
                        break;

                    case XmlPullParser.TEXT: // TAG 안의 문자열. 예시) <title> 과 </title> 사이의 문자열
                        if(isItemTag) {
                            if (tag.equals("title")) { // title TAG 확인
                                title = parser.getText();
                            }
                            if (tag.equals("link")) { // link TAG 확인
                                link = parser.getText();
                            }
                            if (tag.equals("description")) { // description TAG 확인
                                description = parser.getText();
                            }
                            if (tag.equals("dc:date")) { // dc:date TAG 확인
                                time = parser.getText();
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG: // TAG 종료. 예시) </title> 태그
                        tag = parser.getName();
                        if(tag.equals("item")){ // news 태그 종료확인
                            list.add(new News(title, description, link, time)); // 한개의 news 를 저장
                            title ="";
                            link ="";
                            description ="";
                            time ="";
                            isItemTag = false;
                        }
                        tag = ""; // tag 값 초기화 하자
                        break;
                }
                // 다음 TAG 로 이동
                // 예시) </title>의 다음 Tag 인 <link>로 이동.
                parserEvent = parser.next();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

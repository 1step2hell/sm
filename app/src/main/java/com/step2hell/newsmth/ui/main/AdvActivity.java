package com.step2hell.newsmth.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.step2hell.newsmth.R;
import com.step2hell.newsmth.ui.BaseActivity;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv);

        new Task().execute();
    }

    class Task extends AsyncTask<Void,String,String> {
        @Override
        protected String doInBackground(Void... params) {
            String sm="";
            try {
                sm=getHTML("http://www.newsmth.net/");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getSubUtilSimple(sm,PREIMG_REG);
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            final Bean bean = gson.fromJson(s,Bean.class);
            Log.e("Bob",bean.toString());
            RequestOptions options = new RequestOptions()
                    .fitCenter()
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(new RoundedCorners(8));
            Glide.with(AdvActivity.this)
                    .load("http://images.newsmth.net/nForum"+bean.getFile())
                    .apply(options)
                    .into((ImageView) findViewById(R.id.adv));
            findViewById(R.id.adv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AdvActivity.this, bean.getUrl(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String getHTML(String url) throws Exception {
        URL uri = new URL(url);
        URLConnection connection = uri.openConnection();
        InputStream in = connection.getInputStream();
        byte[] buf = new byte[1024];
        int length = 0;
        StringBuffer sb = new StringBuffer();
        while ((length = in.read(buf, 0, buf.length)) > 0) {
            sb.append(new String(buf,"GBK"));
        }
        in.close();
        return sb.toString();
    }


//    private static final String PREIMG_REG = "var preimg=(.*?);;";
    private static final String PREIMG_REG = "preimg=\\[(.*?)\\]";

    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

    class Bean {
        String url;
        String file;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "url='" + url + '\'' +
                    ", file='" + file + '\'' +
                    '}';
        }
    }

}

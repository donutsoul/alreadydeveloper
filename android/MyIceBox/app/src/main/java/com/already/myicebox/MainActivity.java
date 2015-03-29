package com.already.myicebox;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button btnSend;
    private TextView tvRecvData;


    ArrayList<MyDessert> arDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGet = (Button) findViewById(R.id.btn_sendData);
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        HttpClient httpClient = new DefaultHttpClient();

                        String urlString = "http://192.168.0.10:3000/icebox/recipe";
                        String param="?item=계란";
                        try {
                            URI url = new URI(urlString + param);

                            HttpGet httpGet = new HttpGet();
                            httpGet.setURI(url);


                            HttpResponse response = httpClient.execute(httpGet);
                            String responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

                            JSONObject myObject = new JSONObject(responseString);
                            Log.d("icebox", "json response");
                            Log.d("icebox", myObject.toString());


                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        } catch (ClientProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                thread.start();
            }
        });


    }



}


// 리스트뷰에 출력할 항목 클래스
class MyDessert {

    int Icon;
    String Name;

    MyDessert(int aIcon, String aName) {
        Icon = aIcon;
        Name = aName;
    }
}

// 어댑터 클래스
class MyDessertAdapter extends BaseAdapter {

    Context con;
    LayoutInflater inflacter;
    ArrayList<MyDessert> arD;
    int layout;

    public MyDessertAdapter(Context context, int alayout, ArrayList<MyDessert> aarD) {
        con = context;
        inflacter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arD = aarD;
        layout = alayout;
    }

    // 어댑터에 몇 개의 항목이 있는지 조사
    @Override
    public int getCount() {
        return arD.size();
    }

    // position 위치의 항목 Name 반환
    @Override
    public Object getItem(int position) {
        return arD.get(position).Name;
    }

    // position 위치의 항목 ID 반환
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 각 항목의 뷰 생성 후 반환
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflacter.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        img.setImageResource(arD.get(position).Icon);

        TextView txt = (TextView) convertView.findViewById(R.id.txt);
        txt.setText(arD.get(position).Name);

        Button btn = (Button) convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String str = arD.get(position).Name + "를 주문합니다.";
                Toast.makeText(con, str, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}

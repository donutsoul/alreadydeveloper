package com.already.myicebox;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    ArrayList<MyDessert> arDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MyDessert 클래스 형태의 데이터 준비
        arDessert = new ArrayList<MyDessert>();
        MyDessert mydessert;
        mydessert = new MyDessert(R.drawable.test, "마카롱 아이스크림");
        arDessert.add(mydessert);
        mydessert = new MyDessert(R.drawable.test, "베이비슈");
        arDessert.add(mydessert);
        mydessert = new MyDessert(R.drawable.test, "크레페");
        arDessert.add(mydessert);
        mydessert = new MyDessert(R.drawable.test, "슈");
        arDessert.add(mydessert);
        mydessert = new MyDessert(R.drawable.test, "블루베리베이글");
        arDessert.add(mydessert);

        MyDessertAdapter adapter = new MyDessertAdapter(this, R.layout.item, arDessert);

        ListView list;
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);   

        System.out.print("test");
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

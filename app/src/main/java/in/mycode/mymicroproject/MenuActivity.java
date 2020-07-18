package in.mcode.mymicroproject;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final ArrayList<String> displayList=new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv);
        List<String> l = new ArrayList<String>();

        final String s = getIntent().getStringExtra("Category");
        final int tn = getIntent().getIntExtra("tableno",0);
       // Toast.makeText(MenuActivity.this,"category got "+s,Toast.LENGTH_SHORT).show();
        if(s.equals("Appetizers")) {
            l=(MySingleton.getInstance().getAppetizers());
            //Toast.makeText(MenuActivity.this,"Appetizers list "+l,Toast.LENGTH_SHORT).show();
            ArrayAdapter arrayAdapter = new ArrayAdapter(MenuActivity.this, android.R.layout.simple_list_item_1, l);
            listView.setAdapter(arrayAdapter);

        }
        if(s.equals("Starters")) {
            l=(MySingleton.getInstance().getStarters());
            ArrayAdapter arrayAdapter = new ArrayAdapter(MenuActivity.this, android.R.layout.simple_list_item_1, l);
            listView.setAdapter(arrayAdapter);
        }
        if(s.equals("Main Course")) {
            l=(MySingleton.getInstance().getMainCourse());
            ArrayAdapter arrayAdapter = new ArrayAdapter(MenuActivity.this, android.R.layout.simple_list_item_1, l);
            listView.setAdapter(arrayAdapter);
        }
        if(s.equals("Desserts")) {
            l=(MySingleton.getInstance().getDesserts());
            ArrayAdapter arrayAdapter = new ArrayAdapter(MenuActivity.this, android.R.layout.simple_list_item_1, l);
            listView.setAdapter(arrayAdapter);
        }
        if(s.equals("Drinks")) {
            l=(MySingleton.getInstance().getDrinks());
            ArrayAdapter arrayAdapter = new ArrayAdapter(MenuActivity.this, android.R.layout.simple_list_item_1, displayList);
            listView.setAdapter(arrayAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item=(String)listView.getItemAtPosition(position);
                Intent intent=new Intent(MenuActivity.this,OrderActivity1.class);
                intent.putExtra("tableno",tn);
                intent.putExtra("Selected item",item);
                //Toast.makeText(MenuActivity.this,"Selected Item sent "+item,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

}


package in.mcode.mymicroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class VieworderActivity extends AppCompatActivity {
    ArrayList<Integer> tablenolist=new ArrayList<>();
    Spinner tablenoSpinner;
    int currenttable;
    String item;
    Button viewButton;
    TextView tv_served,tv_tobeserved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworder);

        tablenolist.add(1);
        tablenolist.add(2);
        tablenolist.add(3);
        tablenolist.add(4);

        tablenoSpinner=(Spinner)findViewById(R.id.spinner);
        viewButton=(Button)findViewById(R.id.button);
        tv_served= (TextView)findViewById(R.id.textView4);
        tv_tobeserved=(TextView)findViewById(R.id.textView5);

        final ArrayAdapter aa1=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,tablenolist);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tablenoSpinner.setAdapter(aa1);

        tablenoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currenttable= (int) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table temp=MySingleton.getInstance().getTableArrayList().get(currenttable-1);
                String servedtemp="";
                String tobeservedtemp="";

                /*for(int i=0;i<temp.tobeserved.size();i++)
                {
                    //tobeservedtemp=tobeservedtemp+temp.tobeserved.get(i)+"\n";
                }
*/
                Iterator hmIterator = temp.tobeserved.entrySet().iterator();
                while (hmIterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hmIterator.next();
                    tobeservedtemp=tobeservedtemp+mapElement.getKey()+" - "+mapElement.getValue()+"\n";
                }

                /*for(int i=0;i<temp.served.size();i++)
                {
                    servedtemp=servedtemp+temp.served.get(i)+"\n";
                }*/

                Iterator hmIterator2 = temp.served.entrySet().iterator();
                while (hmIterator2.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hmIterator2.next();
                    servedtemp=servedtemp+mapElement.getKey()+" - "+mapElement.getValue()+"\n";
                }

                tv_served.setMovementMethod(new ScrollingMovementMethod());
                tv_served.setText(servedtemp);
                tv_tobeserved.setMovementMethod(new ScrollingMovementMethod());
                tv_tobeserved.setText(tobeservedtemp);

            }
        });


    }
}


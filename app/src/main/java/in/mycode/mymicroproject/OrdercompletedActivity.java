package in.mcode.mymicroproject;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OrdercompletedActivity extends AppCompatActivity {

    ArrayList<Integer> tablenolist=new ArrayList<>();
    ArrayList<String> servedorder=new ArrayList<>();
    Spinner tablenoSpinner;
    Spinner servedorderSpinner;
    int currenttable;
    String item;
    Button ordercompletedButton;
    TextView showbill;
    EditText q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercompleted);

        tablenoSpinner=(Spinner)findViewById(R.id.spinner);
        servedorderSpinner=(Spinner)findViewById(R.id.spinner2);
        ordercompletedButton=(Button)findViewById(R.id.buttoncompleted);
        showbill=(TextView)findViewById(R.id.textView6);
        q=(EditText)findViewById(R.id.editTextquan);

        tablenolist.add(1);
        tablenolist.add(2);
        tablenolist.add(3);
        tablenolist.add(4);

        final ArrayAdapter aa1=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,tablenolist);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tablenoSpinner.setAdapter(aa1);

        tablenoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                currenttable = (int) (adapterView.getItemAtPosition(i));
                //servedorder = MySingleton.getInstance().getTableArrayList().get(currenttable - 1).tobeserved;
                HashMap<String,Integer> h=MySingleton.getInstance().getTableArrayList().get(currenttable - 1).tobeserved;
                Iterator hmIterator = h.entrySet().iterator();
                while (hmIterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hmIterator.next();
                    String s2=mapElement.getKey().toString();
                    servedorder.add(s2);
                }

                ArrayAdapter dataAdapter = new ArrayAdapter(OrdercompletedActivity.this, android.R.layout.simple_spinner_item,servedorder);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                servedorderSpinner.setAdapter(dataAdapter);

                servedorderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        item = (String) adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ordercompletedButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                int iquantity=0;
                if (item != null) {
                    //MySingleton.getInstance().getTableArrayList().get(currenttable - 1).removeTobeserved(item);
                    try {
                        iquantity = Integer.parseInt(q.getText().toString());
                    }catch(Exception e){
                        Toast.makeText(OrdercompletedActivity.this, "Quantity not entered ", Toast.LENGTH_LONG).show();
                    }

                    if(item!=null) {

                        int flag=MySingleton.getInstance().getTableArrayList().get(currenttable - 1).removeTobeserved(item,iquantity);
                        if(flag==1) {
                            if(iquantity!=0) {
                                MySingleton.getInstance().getTableArrayList().get(currenttable - 1).addServed(item, iquantity);
                                Toast.makeText(OrdercompletedActivity.this, item + " served", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(OrdercompletedActivity.this, "Nothing to serve", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            int cquan=MySingleton.getInstance().getTableArrayList().get(currenttable - 1).tobeserved.get(item);
                            Toast.makeText(OrdercompletedActivity.this, "Not served , Current quantity " + cquan, Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(OrdercompletedActivity.this, "Please select an item", Toast.LENGTH_LONG).show();
                    }



                }
                else
                {
                    Toast.makeText(OrdercompletedActivity.this, "No order served", Toast.LENGTH_LONG).show();

                }
            }
        });





    }
}

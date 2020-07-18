package in.mcode.mymicroproject;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CancelorderActivity extends AppCompatActivity {

    ArrayList<Integer> tablenolist=new ArrayList<>();
    ArrayList<String> tobecancelled=new ArrayList<>();
    Spinner tablenoSpinner;
    Spinner tobecancelledSpinner;
    int currenttable;
    String item;
    Button cancelButton;
    EditText q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelorder);

        tablenoSpinner=(Spinner)findViewById(R.id.spinner);
        tobecancelledSpinner=(Spinner)findViewById(R.id.spinner2);
        cancelButton=(Button)findViewById(R.id.button);
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

                HashMap<String,Integer> h=MySingleton.getInstance().getTableArrayList().get(currenttable - 1).tobeserved;
                Iterator hmIterator = h.entrySet().iterator();
                while (hmIterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hmIterator.next();
                    String s2=mapElement.getKey().toString();
                    tobecancelled.add(s2);
                }

                ArrayAdapter dataAdapter = new ArrayAdapter(CancelorderActivity.this, android.R.layout.simple_spinner_item, tobecancelled);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                tobecancelledSpinner.setAdapter(dataAdapter);

                tobecancelledSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                int iquantity=0;
                try {
                    iquantity = Integer.parseInt(q.getText().toString());
                }catch(Exception e){
                    Toast.makeText(CancelorderActivity.this, "Quantity not entered ", Toast.LENGTH_LONG).show();
                }

                if(item!=null) {

                    int flag=MySingleton.getInstance().getTableArrayList().get(currenttable - 1).removeTobeserved(item,iquantity);
                    if(flag==1) {
                        if(iquantity!=0)
                            Toast.makeText(CancelorderActivity.this, item + " cancelled", Toast.LENGTH_LONG).show();
                        else if(iquantity==0)
                            Toast.makeText(CancelorderActivity.this,  "Nothing to cancel", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(CancelorderActivity.this, item + " Current quantity "+MySingleton.getInstance().getTableArrayList().get(currenttable - 1).tobeserved.get(item), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(CancelorderActivity.this, "Please select an item", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}

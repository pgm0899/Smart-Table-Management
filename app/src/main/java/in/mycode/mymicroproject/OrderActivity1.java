package in.mcode.mymicroproject;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity1 extends AppCompatActivity {

    ArrayList<Integer> tablenolist=new ArrayList<>();
    ArrayList<String> menu=new ArrayList<>();
    Spinner tablenoSpinner;
    Spinner tobeservedSpinner;
    int currenttable;
    String item;
    Button orderButton,viewButton,doneButton;
    EditText quantity;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order1);

        tablenolist.add(1);
        tablenolist.add(2);
        tablenolist.add(3);
        tablenolist.add(4);

        menu.add("Appetizers");
        menu.add("Starters");
        menu.add("Main Course");
        menu.add("Desserts");
        menu.add("Drinks");

        tablenoSpinner=(Spinner)findViewById(R.id.spinner);
        tobeservedSpinner=(Spinner)findViewById(R.id.spinner2);
        orderButton=(Button)findViewById(R.id.button);
        viewButton= (Button)findViewById(R.id.bt_view);
        quantity= (EditText)findViewById(R.id.editTextquan);
        doneButton=(Button)findViewById(R.id.buttonDone);

        final ArrayAdapter aa1=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,tablenolist);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tablenoSpinner.setAdapter(aa1);

        final ArrayAdapter aa2=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,menu);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tobeservedSpinner.setAdapter(aa2);

        tablenoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currenttable = (int)(adapterView.getItemAtPosition(i));
            //    Toast.makeText(OrderActivity1.this,currenttable+" selected",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        tobeservedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item= (String) adapterView.getItemAtPosition(i);

                //orderButton.setEnabled(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(OrderActivity1.this,MenuActivity.class);
                ii.putExtra("tableno",currenttable);
                ii.putExtra("Category",item);
                //Toast.makeText(OrderActivity1.this,"category sent "+item,Toast.LENGTH_SHORT).show();
                startActivity(ii);
            }
        });
        orderButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String s = getIntent().getStringExtra("Selected item");
                currenttable= getIntent().getIntExtra("tableno",0);
                tablenoSpinner.setSelection(currenttable-1);
                int iquantity=0;

                try {
                     iquantity = Integer.parseInt(quantity.getText().toString());
                }catch(Exception e){
                    Toast.makeText(OrderActivity1.this, "Quantity not entered ", Toast.LENGTH_LONG).show();
                }

                if(s!=null) {


                    if(iquantity!=0) {
                        Toast.makeText(OrderActivity1.this, "Order placed for " + s+ " for "+currenttable, Toast.LENGTH_LONG).show();
                        MySingleton.getInstance().getTableArrayList().get(currenttable - 1).addTobeserved(s,iquantity);
                    }
                    if(iquantity==0)
                        Toast.makeText(OrderActivity1.this, "You have 0 quantity for "+s, Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(OrderActivity1.this, "Please select an item", Toast.LENGTH_LONG).show();
                }

            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OrderActivity1.this,MainActivity.class);
                startActivity(i);
            }
        });



    }
}

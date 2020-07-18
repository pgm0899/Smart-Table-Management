package in.mcode.mymicroproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PaybillActivity extends AppCompatActivity {

    TextView tv_bill,tv_payable;
    EditText ed_coupon;
    Button bt_pay;
    Spinner sp_tableno;
    ArrayList<Integer> tablenolist=new ArrayList<>();
    int currenttable;
    float bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybill);

        tv_bill=(TextView)findViewById(R.id.textView6);
        tv_payable=(TextView)findViewById(R.id.textView5);
        ed_coupon=(EditText)findViewById(R.id.EditText);
        bt_pay=(Button)findViewById(R.id.button);
        sp_tableno=(Spinner)findViewById(R.id.spinnertableno);

        tablenolist.add(1);
        tablenolist.add(2);
        tablenolist.add(3);
        tablenolist.add(4);

        final ArrayAdapter aa1=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,tablenolist);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tableno.setAdapter(aa1);

        sp_tableno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currenttable= (int) adapterView.getItemAtPosition(i);

                HashMap<String,Integer> servedList=MySingleton.getInstance().getTableArrayList().get(currenttable-1).served;
                bill=0;

                Iterator hmIterator = servedList.entrySet().iterator();
                while (hmIterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hmIterator.next();
                    String key=mapElement.getKey().toString();
                    int quan= (int) mapElement.getValue();
                    bill=bill+(MySingleton.getInstance().getHashMap().get(key)*quan);
                }
                tv_bill.setText(String.valueOf(bill));
                ed_coupon.setText(String.valueOf(0));

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*float discount= (float) Float.parseFloat(String.valueOf(ed_coupon.getText()));
                float pay=bill-discount;*/
                int discount =Integer.parseInt(ed_coupon.getText().toString());
                int pay = (int) (bill-discount);
                tv_payable.setText(String.valueOf(pay));

                MySingleton.getInstance().getTableArrayList().get(currenttable-1).served.clear();
                MySingleton.getInstance().getTableArrayList().get(currenttable-1).tobeserved.clear();

            }
        });

    }
}

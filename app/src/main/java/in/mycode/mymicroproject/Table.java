package in.mcode.mymicroproject;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Table implements Serializable {
    int tableno;
    HashMap<String,Integer> served;
    HashMap<String,Integer> tobeserved;


    public Table(int tableno) {
        this.tableno = tableno;
        this.tobeserved=new HashMap<>();
        this.served=new HashMap<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addTobeserved(String item, int q)
    {
        if(tobeserved.containsKey(item))
            tobeserved.replace(item,q+tobeserved.get(item));
        else
            tobeserved.put(item,q);
        System.out.println("ITEM(s) ADDED TO TO BE SERVED LIST"+tobeserved);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int removeTobeserved(String item, int q)
    {
        int temp=tobeserved.get(item);
        if(temp>q)
        {
            temp=temp-q;
            tobeserved.replace(item,temp);
            System.out.println("ITEM(s) REMOVED FROM TO BE SERVED LIST"+tobeserved);
            return 1;
        }
        else if(temp==q)
        {
            tobeserved.remove(item);
            System.out.println("ITEM(s) REMOVED FROM TO BE SERVED LIST"+tobeserved);
            return 1;
        }
        else
        {
            System.out.println("ITEM(s) NOT REMOVED FROM TO BE SERVED LIST"+tobeserved);
            return 0;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addServed(String item, int q)
    {
        if(served.containsKey(item))
            served.replace(item,served.get(item)+q);
        else
            served.put(item,q);
        System.out.println("ITEM(s) ADDED TO SERVED LIST"+served);
    }

    public void removeAllServed(String item)
    {
        served.clear();
        System.out.println("ITEMS REMOVED FROM SERVED LIST"+served);
    }

    public int getTableno() {
        return tableno;
    }

    public void setTableno(int tableno) {
        this.tableno = tableno;
    }

    public HashMap<String,Integer> getservedlist() {
        return served;
    }

    public void setservedlist(HashMap<String,Integer> h) {
        this.served = h;
    }

    public HashMap<String,Integer> gettobeservedlist() {
        return tobeserved;
    }

    public void settobeservedlist(HashMap<String,Integer> h) {
        this.tobeserved = h;
    }

}

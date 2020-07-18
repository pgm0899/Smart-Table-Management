package in.mcode.mymicroproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class MySingleton {
    private static final MySingleton SELF = new MySingleton();

    private List<Table> tableArrayList = new ArrayList<Table>();
    private HashMap<String,Integer> hm=new HashMap<>(30);
    private List<String> Starters = new ArrayList<String>();
    private List<String> Appetizers = new ArrayList<String>();
    private List<String> MainCourse = new ArrayList<String>();
    private List<String> Desserts = new ArrayList<String>();
    private List<String> Drinks = new ArrayList<String>();


    public List<String> getStarters() {
        return Starters;
    }

    public List<String> getAppetizers() {
        return Appetizers;
    }

    public List<String> getMainCourse() {
        return MainCourse;
    }

    public List<String> getDesserts() {
        return Desserts;
    }

    public List<String> getDrinks() {
        return Drinks;
    }

    private MySingleton() {
        // Don't want anyone else constructing the singleton.
        Starters.add("Falafel");
        Starters.add("Egg yolkshire");
        Starters.add("Scramble");
        Appetizers.add("Bean Soup");
        Appetizers.add("Nachos");
        MainCourse.add("Tiramisu");
        MainCourse.add("Arrabiata");
        MainCourse.add("Exotica Pizza");
        Desserts.add("Pana Cotta");
        Drinks.add("Pinacolada");

    }

    public void createMenuPrice()
    {
        hm.put("Falafel",320);
        hm.put("Tiramisu",400);
        hm.put("Bean Soup",200);
        hm.put("Egg yolkshire",160);
        hm.put("Scramble",135);
        hm.put("Pinacolada",185);
        hm.put("Nachos",300);
        hm.put("Arrabiata",365);
        hm.put("Exotica Pizza",490);
        hm.put("Pana Cotta",345);
    }

    public static MySingleton getInstance() {
        return SELF;
    }

    public List<Table> getTableArrayList() {
        return tableArrayList;
    }

    public HashMap<String,Integer> getHashMap()
    {
        return hm;
    }

    public void addTable(Table t)
    {
        tableArrayList.add(t);
    }

}
package com.timejh.runtimepermission;

import java.util.ArrayList;

/**
 * Created by tokijh on 2017. 2. 1..
 */

public class ContactData {

    private int id;
    private String name;
    private ArrayList<String> num;

    public ContactData(int id, String name, ArrayList<String> num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getNum() {
        return num;
    }

    public void setNum(ArrayList<String> num) {
        this.num = num;
    }

//    public void print() {
//        Log.d("ContactData", "------------------------Data----------------------");
//        Log.d("ContactData", "id : " + id);
//        Log.d("ContactData", "name : " + name);
//        Log.d("ContactData", "number : ");
//        for (int i = 0; i < num.size(); i++) {
//            Log.d("ContactData", "\t" + (i + 1) + " : " + num.get(i));
//        }
//        Log.d("ContactData", "---------------------------------------------------");
//    }
}

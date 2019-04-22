package com.example.maskhare;

import android.content.Context;
import android.database.Cursor;

import com.example.maskhare.Models.Category;
import com.example.maskhare.Models.Thing;

import java.util.ArrayList;
import java.util.List;

public class DBService {
    private List<Category> categories;
    private List<Thing> things;
    DatabaseAccess dbAccess;

    public DBService(Context context) {
        dbAccess = new DatabaseAccess(context);
        categories = new ArrayList<>();
        CollectCategories();
        things = new ArrayList<>();
        CollectThings();
    }

    private void CollectThings() {
        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM Thing", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Thing thing = new Thing(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3));
            things.add(thing);
        }
    }

    private void CollectCategories() {

    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Thing> getThings() {
        return things;
    }
}

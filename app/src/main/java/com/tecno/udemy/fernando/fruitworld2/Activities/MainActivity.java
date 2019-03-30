package com.tecno.udemy.fernando.fruitworld2.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tecno.udemy.fernando.fruitworld2.R;
import com.tecno.udemy.fernando.fruitworld2.adapters.FruitAdapter;
import com.tecno.udemy.fernando.fruitworld2.model.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits;
    private RecyclerView.LayoutManager layoutManager;
    private FruitAdapter adapter;
    private RecyclerView recyclerView;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initComponents();
        initData();
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void initComponents(){
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initData(){
        counter = 0;
        fruits = getDummyFruits();

        adapter = new FruitAdapter(this, fruits, R.layout.recycler_item, new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.incrementQuantity();
                adapter.notifyItemChanged(position);
            }
        });
    }

    public List<Fruit> getDummyFruits(){
        List fruits = new ArrayList<>();
        fruits.add(new Fruit(R.drawable.apple_bg, R.mipmap.apple_ic, "Apple", "Apple's Description", (byte)0));
        fruits.add(new Fruit(R.drawable.banana_bg, R.mipmap.banana_ic, "Banana", "Banana's Description", (byte)0));
        fruits.add(new Fruit(R.drawable.cherry_bg, R.mipmap.cherry_ic, "Cherry", "Cherry's Description", (byte)0));
        fruits.add(new Fruit(R.drawable.orange_bg, R.mipmap.orange_ic, "Orange", "Orange's Description", (byte)0));
        fruits.add(new Fruit(R.drawable.plum_bg, R.mipmap.plum_ic, "Plum", "Plum's Description", (byte)0));
        fruits.add(new Fruit(R.drawable.raspberry_bg, R.mipmap.raspberry_ic, "Raspberry", "Raspberry's Description", (byte)0));
        fruits.add(new Fruit(R.drawable.strawberry_bg, R.mipmap.strawberry_ic, "Strawberry", "Strawberry's Description", (byte)0));

        return fruits;
    }

    /*
    -----------------------------------------Menus----------------------------------------------------------------
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnu_addFruit:
                addFruit();

        }
        return super.onOptionsItemSelected(item);
    }

    private void addFruit(){
        int position = fruits.size();
        fruits.add(position, new Fruit(R.drawable.apple_bg, R.mipmap.apple_ic, "Apple " + (++counter),
                "Apple's Description", (byte)0));
        adapter.notifyItemInserted(position);
        recyclerView.scrollToPosition(position);
    }


}

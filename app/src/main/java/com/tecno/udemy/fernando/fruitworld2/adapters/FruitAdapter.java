package com.tecno.udemy.fernando.fruitworld2.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tecno.udemy.fernando.fruitworld2.R;
import com.tecno.udemy.fernando.fruitworld2.model.Fruit;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{

    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;

    public FruitAdapter(Activity activity, List<Fruit> fruits, int layout, OnItemClickListener listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(fruits.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewQuantity;
        public ImageView imageViewBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            imageViewBackground = itemView.findViewById(R.id.imageViewBackground);

        }

        public void bind(final Fruit fruit, final OnItemClickListener listener){

            this.textViewName.setText(fruit.getName());
            this.textViewQuantity.setText(fruit.getQuantity()+"");
            this.textViewDescription.setText(fruit.getDescription());
            Picasso.get().load(fruit.getBackground()).fit().into(this.imageViewBackground);

            this.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });

            if(fruit.getQuantity() == Fruit.LIMIT_QUANTITY){
                textViewQuantity.setTextColor(Color.RED);
                textViewQuantity.setTypeface(null, Typeface.BOLD);
            }else{
                textViewQuantity.setTextColor(Color.BLACK);
                textViewQuantity.setTypeface(null, Typeface.NORMAL);
            }

            /*
                -----------MENU CONTEXTUAL-----------------------------
             */

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            Fruit selectedFruit = fruits.get(this.getAdapterPosition());

            contextMenu.setHeaderTitle(selectedFruit.getName());
            contextMenu.setHeaderIcon(selectedFruit.getIcon());

            activity.getMenuInflater().inflate(R.menu.menu_context, contextMenu);

            for (int i = 0; i < contextMenu.size(); i++)
                contextMenu.getItem(i).setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){
                case R.id.mnu_delete:
                    deleteFruit();
                    break;

                case R.id.mnu_reset:
                    resetFruit();
                    break;
            }
            return false;
        }

        private void deleteFruit(){
            fruits.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }

        private void resetFruit(){
            fruits.get(getAdapterPosition()).resetQuantity();
            notifyItemChanged(getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Fruit fruit, int position);
    }

}

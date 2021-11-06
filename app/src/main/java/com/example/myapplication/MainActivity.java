package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<ShopItem> shopItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        RecyclerView mainRecycleView=findViewById(R.id.recycle_view_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecycleView.setLayoutManager(layoutManager);

        mainRecycleView.setAdapter(new MyRecyclerViewAdapter(shopItems));
    }


    public void initData(){
        shopItems=new ArrayList<ShopItem>();
        shopItems.add(new ShopItem("青菜",R.drawable.a1,5.6));
        shopItems.add(new ShopItem("萝卜",R.drawable.a2,8.6));
        shopItems.add(new ShopItem("西红柿",R.drawable.a3,4.6));
    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter {
        private List<ShopItem> shopItems;

        public MyRecyclerViewAdapter(List<ShopItem> shopItems) {
            this.shopItems=shopItems;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shop_item_holder, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder Holder, int position) {
            MyViewHolder holder= (MyViewHolder)Holder;

            holder.getImageView().setImageResource(shopItems.get(position).getPictureId());
            holder.getTextViewName().setText(shopItems.get(position).getName());
            holder.getTextViewPrice().setText(shopItems.get(position).getPrice()+"");
        }

        @Override
        public int getItemCount() {
            return shopItems.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
            private final ImageView imageView;
            private final TextView textViewName;
            private final TextView textViewPrice;

            public MyViewHolder(View itemView) {
                super(itemView);

                this.imageView=itemView.findViewById(R.id.image_view_shop_item);
                this.textViewName=itemView.findViewById(R.id.text_view_shop_item_name);
                this.textViewPrice=itemView.findViewById(R.id.text_view_shop_item_price);

                itemView.setOnCreateContextMenuListener(this);

            }

            public ImageView getImageView() {
                return imageView;
            }

            public TextView getTextViewName() {
                return textViewName;
            }

            public TextView getTextViewPrice() {
                return textViewPrice;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                int position=getAdapterPosition();
                MenuItem menuItemAdd=contextMenu.add(Menu.NONE,1,1,"Add"+position);
                MenuItem menuItemEdit=contextMenu.add(Menu.NONE,2,2,"Edit"+position);
                MenuItem menuItemelete=contextMenu.add(Menu.NONE,3,3,"Delete"+position);

                menuItemAdd.setOnMenuItemClickListener(this);
                menuItemEdit.setOnMenuItemClickListener(this);
                menuItemelete.setOnMenuItemClickListener(this);

            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int position= getAdapterPosition();
                switch(menuItem.getItemId())
                {
                    case 1:
                        View dialagueView= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialogue_input_item,null);
                        AlertDialog.Builder alertDialogBuiler = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuiler.setView(dialagueView);

                        alertDialogBuiler.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText editName=dialagueView.findViewById(R.id.edit_text_name);
                                EditText editPrice=dialagueView.findViewById(R.id.edit_text_price);
                                shopItems.add(position,new ShopItem(editName.getText().toString(),R.drawable.a2,Double.parseDouble(editPrice.getText().toString())));
                                MyRecyclerViewAdapter.this.notifyItemInserted(position);
                            }
                        });
                        alertDialogBuiler.setCancelable(false).setNegativeButton ("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialogBuiler.create().show();;

                        break;
                    case 2:
                        shopItems.get(position).setName("测试修改");
                        MyRecyclerViewAdapter.this.notifyItemChanged(position);
                        break;

                    case 3:
                        shopItems.remove(position);
                        MyRecyclerViewAdapter.this.notifyItemRemoved(position);
                        break;
                }

                Toast.makeText(MainActivity.this,"点击了"+menuItem.getItemId(), Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
}
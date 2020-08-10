package com.shashi.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    Context context;
    List<ProductsData> productsDataList;

    public ProductsAdapter(Context context, List<ProductsData> productsDataList) {
        this.context = context;
        this.productsDataList = productsDataList;
    }

    //Returns ViewHolder class
    //To return ViewHolder class we have to create ViewHolder object
    //But to create object, we have to pass view in ViewHolder's constructor

    //Now to create object of an xml file
    //we have to inflate the xml file
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Layout file name (jisko inflate karna hai)
        //Parent, jiske sath inflate karna hai
        View view = LayoutInflater.from(context).inflate(R.layout.fruit_product_layout, parent, false);

        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    //Bind the data with view object
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductsData productsData = productsDataList.get(position);

        String itemName = productsData.getItemName();
        double itemPrice = productsData.getItemPrice();

        holder.itemNameTextView.setText(itemName);
        holder.itemPriceTextView.setText("Price: INR " + String.valueOf(itemPrice));
        holder.imageView.setImageDrawable(context.getResources().getDrawable(productsData.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return productsDataList.size();
    }


    //Hold the data into the views that we have created
    //mplements View.OnClickListener for Buy and Add to cart button
    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView itemNameTextView, itemPriceTextView;
        Button buyButton, addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.fruit_image_view);
            itemNameTextView = itemView.findViewById(R.id.fruit_name_text_view);
            itemPriceTextView = itemView.findViewById(R.id.fruit_price_text_view);

            //onClickListner for buttons
            buyButton = itemView.findViewById(R.id.fruit_button_buy_now);
            addToCartButton = itemView.findViewById(R.id.fruit_button_add_to_card);
            buyButton.setOnClickListener(this);
            addToCartButton.setOnClickListener(this);

        }

        //onClickListner for buttons
        @Override
        public void onClick(View view) {
            String itemName = itemNameTextView.getText().toString();
            switch (view.getId()) {
                case R.id.fruit_button_buy_now:
                    Toast.makeText(context, itemName + " bought (1 kg)", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fruit_button_add_to_card:
                    Toast.makeText(context, "Added to card", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}

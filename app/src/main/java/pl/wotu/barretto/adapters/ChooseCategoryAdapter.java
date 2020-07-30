package pl.wotu.barretto.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import pl.wotu.barretto.R;
import pl.wotu.barretto.model.CategoryModel;

public class ChooseCategoryAdapter extends RecyclerView.Adapter<ChooseCategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;
    String idCategory,name;
    private String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private boolean isChosen;

    public ChooseCategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_category_item,parent,false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        categoryModelList.get(position).getIdCategory();

////            Drawable background = mView.getBackground();
        String hexColor = categoryModelList.get(position).getHexColor();
        int color = categoryModelList.get(position).getBgColor();
        final String categoryName = categoryModelList.get(position).getName();
        isChosen = categoryModelList.get(position).getChosen();
//        hexColor = "#"+hexColor.toUpperCase();
//        int bgColor = Color.parseColor(hexColor);
//        Log.d("changeLayoutColor hexColor",hexColor);
//        Log.d("nowy kolor bgColor",bgColor+"");
//        mView.setBackgroundColor(bgColor);


        holder.setName(categoryName,isChosen);

        if(isNullOrEmpty(hexColor)){
            holder.setBgColor(color);
        }else {
            holder.setBgColor(hexColor);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChosen=!isChosen;
                categoryModelList.get(position).setChosen(isChosen);
                holder.setName(categoryName,isChosen);
//                categoryModelList.notify();
            }
        });





        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final String koniec = categoryModelList.get(position).getName();
//                firebaseFirestore.collection("users").document(currentUser).collection("events").document(eventId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
                        categoryModelList.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(holder.mView.getContext(), "Usunięto pizdryk:\n"+koniec, Toast.LENGTH_SHORT).show();
//                    }
//                });
                return true;
            }
        });
    }


    private boolean isNullOrEmpty(String string) {
        if ((string==null)||(string.equals(""))){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView chooseCategoryNameItem,plusItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            chooseCategoryNameItem = mView.findViewById(R.id.chooseCategoryNameItem);
            plusItem = mView.findViewById(R.id.plusItem);
//            mView.setOnClickListener(view -> {
//
//            });
        }

        public void setName(String categoryName, boolean isChosen){
            chooseCategoryNameItem.setText(categoryName);
            if (isChosen){
                plusItem.setVisibility(View.VISIBLE);
            }else{
                plusItem.setVisibility(View.INVISIBLE);
            }

        }

        public void setBgColor(int bgColor){
            changeLayoutColor(plusItem,bgColor,null);
            changeLayoutColor(chooseCategoryNameItem,bgColor,null);
        }

        private void changeLayoutColor(View mView, int layoutColor, String colorHex) {
            Log.d("changeLayoutColor","layoutColor="+layoutColor+"colorHex="+colorHex);
            if ((colorHex !=null)||(layoutColor!=0)){

                Drawable background = mView.getBackground();
                Context mContext = mView.getContext();
                if (background instanceof ShapeDrawable) {
                    // cast to 'ShapeDrawable'
                    ShapeDrawable shapeDrawable = (ShapeDrawable) background;
                    shapeDrawable.getPaint().setColor(ContextCompat.getColor(mContext,layoutColor));
                } else if (background instanceof GradientDrawable) {
                    // cast to 'GradientDrawable'
                    GradientDrawable gradientDrawable = (GradientDrawable) background;
                    gradientDrawable.setColor(ContextCompat.getColor(mContext,layoutColor));
                } else if (background instanceof ColorDrawable) {
                    // alpha value may need to be set again after this call
                    ColorDrawable colorDrawable = (ColorDrawable) background;
                    colorDrawable.setColor(ContextCompat.getColor(mContext,layoutColor));
                }
            }


//    private void changeLayoutColorFromHex(String hexColor) {
////            Drawable background = mView.getBackground();
//        hexColor = "#"+hexColor.toUpperCase();
//        int bgColor = Color.parseColor(hexColor);
//        Log.d("changeLayoutColor hexColor",hexColor);
//        Log.d("nowy kolor bgColor",bgColor+"");
//        mView.setBackgroundColor(bgColor);
//    }


        }

        public void setBgColor(String hexColor) {
            hexColor = "#"+hexColor.toUpperCase();
            Log.d("hexColor",hexColor);
            int bgColor = Color.parseColor(hexColor);



            Drawable background = plusItem.getBackground();
//            Context mContext = plusItem.getContext();
            if (background instanceof GradientDrawable) {
                GradientDrawable gradientDrawable = (GradientDrawable) background;
                gradientDrawable.setColor(bgColor);
            }
            Drawable background2 = chooseCategoryNameItem.getBackground();
            if (background2 instanceof GradientDrawable) {
                GradientDrawable gradientDrawable2 = (GradientDrawable) background2;
                gradientDrawable2.setColor(bgColor);
            }


        }


    }

}

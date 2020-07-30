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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import pl.wotu.barretto.R;
import pl.wotu.barretto.model.ColorModel;

public class ColorPalletteAdapter extends RecyclerView.Adapter<ColorPalletteAdapter.ViewHolder>  {

    private TextView colorNameTV;
    private List<ColorModel> colorModelList;
    private FirebaseFirestore firebaseFirestore;
    String name;
    private Context context;
    private EditText newCategoryEditText;
    int colorId;
    private String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public ColorPalletteAdapter(ArrayList<ColorModel> colorsList, EditText newCategoryEditText, TextView colorNameTV) {
            this.colorModelList = colorsList;
            this.newCategoryEditText = newCategoryEditText;
            this.colorNameTV = colorNameTV;
    }


    @NonNull
    @Override
    public ColorPalletteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_item,parent,false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        context = parent.getContext();
        return new ColorPalletteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorPalletteAdapter.ViewHolder holder, int position) {
        String hexColor = colorModelList.get(position).getHexColor();
        int colorID = colorModelList.get(position).getColorID();
//        holder.setBgColor(colorModelList.get(position).getHexColor());
        holder.setBgColor(colorID);

//        holder.changeLayoutColor(mView,color);

        holder.colorButton.setOnClickListener(view -> {
            Drawable ETBackground = newCategoryEditText.getBackground();
            GradientDrawable gradientDrawable = (GradientDrawable) ETBackground;
            gradientDrawable.setColor(ContextCompat.getColor(context,colorModelList.get(position).getColorID()));
            colorNameTV.setText(colorModelList.get(position).getHexColor());
        });
    }

    @Override
    public int getItemCount() {
        return colorModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private final View mView;
        private final Button colorButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
//            categoryNameItem = mView.findViewById(R.id.categoryNameItem);
            colorButton = mView.findViewById(R.id.color_button);
        }

        public void setBgColor(int bgColor){
            changeLayoutColor(colorButton,bgColor);
        }

        private void changeLayoutColor(View view, int layoutColor) {
            Drawable background = view.getBackground();
//            layoutColor = R.color.cyan_A200;
//            Context mContext = view.getContext();
            if (background instanceof ShapeDrawable) {
                Log.d("G Ó W N O ShapeDrawable",layoutColor+"");
                // cast to 'ShapeDrawable'
                ShapeDrawable shapeDrawable = (ShapeDrawable) background;
                shapeDrawable.getPaint().setColor(ContextCompat.getColor(context,layoutColor));
            } else if (background instanceof GradientDrawable) {
                // cast to 'GradientDrawable'
                GradientDrawable gradientDrawable = (GradientDrawable) background;
                gradientDrawable.setColor(ContextCompat.getColor(context,layoutColor));
                Toast.makeText(context,layoutColor+"", Toast.LENGTH_LONG).show();
//                gradientDrawable.setColor(ContextCompat.getColor(context,colorModelList.get(position).getColorID()));
                Log.d("G Ó W N O GradientDrawable",layoutColor+"");

            } else if (background instanceof ColorDrawable) {
                // alpha value may need to be set again after this call
                ColorDrawable colorDrawable = (ColorDrawable) background;
                colorDrawable.setColor(ContextCompat.getColor(context,layoutColor));
                Log.d("G Ó W N O ColorDrawable",layoutColor+"");

            }

        }


//        public void setBgColor(int color){
//            changeLayoutColor(mView,color);

//        }

//        private void changeLayoutColor(View mView, int color) {
//            Drawable background = mView.getBackground();
//            GradientDrawable gradientDrawable = (GradientDrawable) background;
//            Log.d("adapter color",""+color);
////                GradientDrawable gradientDrawable = (GradientDrawable) background;
////            gradientDrawable.s
//            //TODO
//
//            gradientDrawable.setColor(ContextCompat.getColor(context,color));
//
////            hexColor = "#"+hexColor.toUpperCase();
////            int bgColor = Color.parseColor(hexColor);
////            Log.d("changeLayoutColor hexColor",hexColor);
////            Log.d("nowy kolor bgColor",bgColor+"");
////            mView.setBackgroundColor(bgColor);
//            //TODO
////            gradientDrawable.setColor(color);
////            gradientDrawable.setColor(ContextCompat.getColor(context,R.color.amber_500));
//
////            int color = Color.parseColor("#ff777777");
//
//
//
////            int deepColor = Color.parseColor("#FFF5EE");
//
//            // set the button background color
//            // setBackgroundColor() method require to pass an int color
////            gradientDrawable.set.setBackgroundColor(deepColor);
//
////            gradientDrawable.setColor(color);
////            Color.parseColor("#ffffff");
//
//        }

        private void changeLayoutColor(View mView, String hexColor) {
            Drawable background = mView.getBackground();
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            //TODO


            hexColor = "#"+hexColor.toUpperCase();
            int bgColor = Color.parseColor(hexColor);
//            Log.d("changeLayoutColor hexColor",hexColor);
//            Log.d("nowy kolor bgColor",bgColor+"");
//            mView.setBackgroundColor(bgColor);
            //TODO
            gradientDrawable.setColor(bgColor);


//            int color = Color.parseColor("#ff777777");



//            int deepColor = Color.parseColor("#FFF5EE");

            // set the button background color
            // setBackgroundColor() method require to pass an int color
//            gradientDrawable.set.setBackgroundColor(deepColor);

//            gradientDrawable.setColor(color);
//            Color.parseColor("#ffffff");

        }

    }


    private boolean isNullOrEmpty(String string) {
        if ((string==null)||(string.equals(""))){
            return true;
        }else{
            return false;
        }
    }
}

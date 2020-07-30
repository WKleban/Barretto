package pl.wotu.barretto.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.wotu.barretto.R;
import pl.wotu.barretto.model.NoteModel;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MainViewHolder> {
    private final List<NoteModel> mNoteList;
    private final Activity mActivity;
    NavController navController;

    public NoteAdapter(Activity activity, List<NoteModel> noteModel) {
        mActivity = activity;
        mNoteList = noteModel;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        private final View constraintLayout;
        private final ConstraintLayout note_adapter_item_view,user_layout,note_layout,info_layout;
        private final CardView note_card_view;
        private final ImageView image_listitem;
        private final CircleImageView civ_image_listitem;
        private final TextView username_listitem,title_listitem,note_listitem,url_listitem;
        private final TextView timestamp_listitem,place_listitem,location_listitem;
        private final TextView id_tv;
        private final ImageView image_icon;

        public MainViewHolder(@NonNull View v) {
            super(v);
            constraintLayout = v;



            id_tv = v.findViewById(R.id.id_tv);
            //Ramka
            note_adapter_item_view = v.findViewById(R.id.note_adapter_item_view);
            note_card_view = v.findViewById(R.id.note_card_view);

            image_listitem = v.findViewById(R.id.image_det);

            //Informacje użytkownika
            user_layout = v.findViewById(R.id.user_layout_det);
            civ_image_listitem = v.findViewById(R.id.civ_image_det);
            username_listitem = v.findViewById(R.id.username_det);

            //Notatka
            note_layout = v.findViewById(R.id.note_layout_det);
            title_listitem = v.findViewById(R.id.title_det);
            note_listitem = v.findViewById(R.id.note_det);
            url_listitem = v.findViewById(R.id.url_det);
//            youtube_title_listitem = v.findViewById(R.id.youtube_title_listitem);

            //Czas i miejsce
            info_layout = v.findViewById(R.id.info_layout_det);
            timestamp_listitem = v.findViewById(R.id.timestamp_det);
            place_listitem = v.findViewById(R.id.place_det);
            location_listitem = v.findViewById(R.id.location_det);

            image_icon = v.findViewById(R.id.image_icon);
//            niDate_listitem = v.findViewById(R.id.niDate_listitem);

        }
    }
    @NonNull
    @Override
    public NoteAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_adapter_layout, parent, false);
//        ...
        MainViewHolder vh = new MainViewHolder(v);
//        navController = Navigation.findNavController(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MainViewHolder holder, int position) {
//        //Ramka
//        note_adapter_item_view = v.findViewById(R.id.note_adapter_item_view);
//        note_card_view = v.findViewById(R.id.note_card_view);
//
//        image_listitem = v.findViewById(R.id.image_listitem);
//
//        //Informacje użytkownika
//        user_layout = v.findViewById(R.id.user_layout);
//        civ_image_listitem = v.findViewById(R.id.civ_image_listitem);
//        username_listitem = v.findViewById(R.id.username_listitem);
//
//        //Notatka
//        note_layout = v.findViewById(R.id.note_layout);
//        title_listitem = v.findViewById(R.id.title_listitem);
//        note_listitem = v.findViewById(R.id.note_listitem);
//        url_listitem = v.findViewById(R.id.url_listitem);
//        youtube_title_listitem = v.findViewById(R.id.youtube_title_listitem);
//

        holder.image_listitem.setVisibility(View.GONE);
        //

        holder.id_tv.setText(mNoteList.get(position).getId());

        holder.id_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                /Users/SjepnBmlCoV00CVOwclxOQZXJ0x2/Notes/DtelZzcS1dyN4CfrJfmJ
//                Toast.makeText(mActivity.getApplicationContext(),"/Users/"+mNoteList.get(position).getOwner_id()+"/Notes/"+mNoteList.get(position).getNoteID(),Toast.LENGTH_LONG).show();
//                Intent detailsIntent = new Intent(mActivity.getApplicationContext(), NoteDetailsActivity.class);
//                detailsIntent.putExtra("ownerID",mNoteList.get(position).getOwner_id());
//                detailsIntent.putExtra("noteID",mNoteList.get(position).getId());
//
//                mActivity.startActivity(detailsIntent);

//                navController.navigate(Note);


            }
        });
//TIMESTAMP
//        if (isNullOrEmpty(mNoteList.get(position).getNiDate())){
            //jeśli puste to wstaw normalną datę:
            if (mNoteList.get(position).getTimestamp()==null){
                holder.timestamp_listitem.setText("NIE MA CZASU");
//                Log.d("brak czasu",mNoteList.get(position).getNoteID());
            }else {
                Date timestamp_data = mNoteList.get(position).getTimestamp();
                long milliseconds = timestamp_data.getTime();
                String dateString = DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date(milliseconds)).toString();
                holder.timestamp_listitem.setText(dateString);
            }

//PLACE
        String place_string = mNoteList.get(position).getPlace();
        if (!isNullOrEmpty(place_string)) {
            holder.place_listitem.setText(mNoteList.get(position).getPlace());
            holder.place_listitem.setVisibility(View.VISIBLE);
        }else {
            holder.place_listitem.setVisibility(View.GONE);
        }

//LOCATION
        String location_string="";
        try {
            double locationLat = mNoteList.get(position).getLocation().getLatitude();
            double locationLong = mNoteList.get(position).getLocation().getLongitude();
            String letterLat = "";
            String letterLong = "";
            if (locationLat>0) {
                letterLat = "°N";
            } else if (locationLat < 0) {
                letterLat = "°S";
            }

            if (locationLong>0) {
                letterLong = "°E";
            } else if (locationLong < 0) {
                letterLong = "°W";
            }
            location_string = "("+locationLat+""+letterLat+","+locationLong+""+letterLong+")";

            holder.location_listitem.setText(location_string);
            holder.location_listitem.setVisibility(View.VISIBLE);

        }catch (NullPointerException e){
            holder.location_listitem.setVisibility(View.GONE);
        }

//TITLE
        String title_string = mNoteList.get(position).getTitle();
        if (!isNullOrEmpty(title_string)) {
            holder.title_listitem.setText(title_string);
            holder.title_listitem.setVisibility(View.VISIBLE);
        }else {
                holder.title_listitem.setVisibility(View.GONE);
        }

//URL
        String url_string = mNoteList.get(position).getUrl();
        if (!isNullOrEmpty(url_string)) {
            holder.url_listitem.setText(url_string);
            holder.url_listitem.setVisibility(View.VISIBLE);
        }else {
            holder.url_listitem.setVisibility(View.GONE);
        }

        holder.url_listitem.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_string));
            mActivity.startActivity(browserIntent);
        });

//NOTE
        String note_string = mNoteList.get(position).getNote();
        if (!isNullOrEmpty(note_string)) {
            if(note_string.equals(url_string)){
                holder.note_listitem.setVisibility(View.GONE);
            }else{
                holder.note_listitem.setText(note_string);
                holder.note_listitem.setVisibility(View.VISIBLE);
            }
        }else {
            holder.note_listitem.setVisibility(View.GONE);
        }

        //Ukrywanie informacji o użytkowniku który dodał tą notatkę
        holder.user_layout.setVisibility(View.GONE);




        String imageUrl = mNoteList.get(position).getImage_url();
        String url = mNoteList.get(position).getUrl();
        if (!isNullOrEmpty(holder.url_listitem.getText().toString())){
        }
        if (imageUrl==(null)||imageUrl.equals("")){
            holder.image_icon.setVisibility(View.GONE);
//            holder.image_listitem.setVisibility(View.GONE);
//            if (!(url==(null))&&!(url.equals(""))){
//                if (url.contains("https://youtu.be/")){ //https://www.youtube.com/watch?v=
//                    String[] tempString = url.split("youtu.be/");
//                    Picasso.get()
//                            .load("https://img.youtube.com/vi/"+tempString[1]+"/hqdefault.jpg")
//                            .placeholder(R.drawable.ic_launcher_background)
//                            /*                      .error(R.drawable.user_placeholder_error) */
//                            .into(image_det);
//                    image_det.setVisibility(View.VISIBLE);
//
//
//                }else {
//                    image_det.setVisibility(View.GONE);
//                }
//            }



            if (!(url==(null))&&!(url.equals(""))){

                if (url.contains("https://youtu.be/")){

                    String[] tempString = url.split("youtu.be/");


                    //Wstaw obrazek z yt
                    //                    https://img.youtube.com/vi/F9UC9DY-vIU/sddefault.jpg
                    Picasso.get()
                            .load("https://img.youtube.com/vi/"+tempString[1]+"/hqdefault.jpg")
                            .placeholder(R.drawable.ic_launcher_background)
                            /*                      .error(R.drawable.user_placeholder_error) */
                            .into(holder.image_listitem);
                    holder.image_listitem.setVisibility(View.VISIBLE);

                    Log.i("YOUTU.BE","https://img.youtube.com/vi/"+tempString[1].substring(0,11)+"/hqdefault.jpg" );
                }else if(url.contains(".youtube.com/")){
                    String[] tempString = url.split("v=");
                    try {
                        Picasso.get()
                                .load("https://img.youtube.com/vi/" + tempString[1].substring(0, 11) + "/hqdefault.jpg")
                                .placeholder(R.drawable.ic_launcher_background)
                                /*                      .error(R.drawable.user_placeholder_error) */
                                .into(holder.image_listitem);
                        holder.image_listitem.setVisibility(View.VISIBLE);
                        Log.i("YOUTUBE","https://img.youtube.com/vi/"+tempString[1].substring(0,11)+"/hqdefault.jpg" );
                    }catch (ArrayIndexOutOfBoundsException e){

                        Log.wtf("ArrayIndexOutOfBoundsException", tempString[0]);
                        holder.image_listitem.setVisibility(View.GONE);
                    }

                }else {
                    holder.image_listitem.setVisibility(View.GONE);
                }
            }
        }else{
            holder.image_icon.setVisibility(View.VISIBLE);
            holder.image_listitem.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(mNoteList.get(position).getImage_url())
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .error(R.drawable.common_full_open_on_phone)

//                .resize(128,128)
//                .centerCrop()
                    .into(holder.image_listitem);
        }



//        Date nitimestamp_data = mNoteList.get(position).getTimestamp();
//        long milliseconds = timestamp_data.getTime();
//        String dateString = DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date(milliseconds)).toString();
//        holder.timestamp_listitem.setText(dateString);


//        holder.place_listitem.setText(mNoteList.get(position).getYoutube_title());
//        holder.location_listitem.setText(mNoteList.get(position).getYoutube_title());
//        holder.niDate_listitem.setText(mNoteList.get(position).getYoutube_title());

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
        return  mNoteList.size();
    }

}

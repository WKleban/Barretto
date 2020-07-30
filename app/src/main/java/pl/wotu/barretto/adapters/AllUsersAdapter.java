package pl.wotu.barretto.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
//import pl.wotu.barretto.ChosenUserNotesActivity;
import pl.wotu.barretto.R;
import pl.wotu.barretto.model.AllUsersModel;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.MainViewHolder> {
    private final List<AllUsersModel> mAllUsersList;
    private final Activity mActivity;

    public AllUsersAdapter(Activity activity, List<AllUsersModel> myDataset) {
        mAllUsersList = myDataset;
        mActivity = activity;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
//        private final TextView tvDate;
        // each data item is just a string in this case
        public ConstraintLayout constraintLayout;

        public TextView all_users_id_TV,all_users_name_TV;
        public CircleImageView all_users_iv;
//        public CardView cardView;

        public MainViewHolder(ConstraintLayout v) {
            super(v);
            constraintLayout = v;
            all_users_name_TV = v.findViewById(R.id.all_users_name_TV);
            all_users_id_TV = v.findViewById(R.id.all_users_id_TV);
            all_users_iv = v.findViewById(R.id.all_users_iv);
//            cardView = v.findViewById(R.id.card_view);
        }
    }
    @NonNull
    @Override
    public AllUsersAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_users_adapter_layout, parent, false);
//        ...
        MainViewHolder vh = new MainViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.all_users_name_TV.setText(mAllUsersList.get(position).getName());
        holder.all_users_id_TV.setText(mAllUsersList.get(position).getId());
        String img_url = mAllUsersList.get(position).getImage();

        Picasso.get()
                .load(img_url)
                .placeholder(R.drawable.ic_launcher_background)
/*              .error(R.drawable.user_placeholder_error)

                .resize(128,128)
                .centerCrop()*/
                .into(holder.all_users_iv);

//        holder.constraintLayout.setOnClickListener(view -> {
//            Intent showNotesOfThis1Intent = new Intent(mActivity, ChosenUserNotesActivity.class);
//            showNotesOfThis1Intent.putExtra("userID",mAllUsersList.get(position).getId());
//            mActivity.startActivity(showNotesOfThis1Intent);
//        });

    }

    @Override
    public int getItemCount() {
        return  mAllUsersList.size();
    }

}

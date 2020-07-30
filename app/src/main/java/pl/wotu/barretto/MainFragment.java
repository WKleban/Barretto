package pl.wotu.barretto;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.wotu.barretto.adapters.AllUsersAdapter;
import pl.wotu.barretto.adapters.NoteAdapter;
import pl.wotu.barretto.model.AllUsersModel;
import pl.wotu.barretto.model.NoteModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {


    private List<NoteModel> list;

    //RECYCLER
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int collectionSize;

//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle actionBarDrawerToggle;

    Toolbar toolbar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private CollectionReference collectionReference;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(R.id.toolbar_list);
        toolbar.inflateMenu(R.menu.toolbar_main_menu);

        recyclerView = view.findViewById(R.id.rv_list);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

//        db.collection("Users").document(currentUser.getUid()).collection("Notes").orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()){
//                    int size = task.getResult().getDocuments().size();
////                    String timestamp = task.getResult().getDocuments().get(0).get("timestamp").toString();
////                    String note = Objects.requireNonNull(task.getResult().getDocuments().get(0).get("note")).toString();
//
//                    Toast.makeText(getActivity(),"Size: "+size,Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(getActivity(),task.getException().toString(),Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }


    @Override
    public void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String current_user_id = currentUser.getUid();

            list = new ArrayList<>();

            db.collection("Users").document(current_user_id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (!task.getResult().exists()) {
                        // TODO sprawdzanie pierwszego uruchomienia
                        // InitialSettingsFragment (?)

//                        Intent setupIntent = new Intent(MainActivityOLD.this, SimpleSettingsActivity.class);
//                        startActivity(setupIntent);
//                        finish();
                    }
                }

            });
            list = new ArrayList<>();
            collectionReference = db.collection("Users").document(current_user_id).collection("Notes");

            collectionReference
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .addSnapshotListener((queryDocumentSnapshots, e) -> {
                        try{
                            for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                                if (doc.getType() == DocumentChange.Type.ADDED) {
                                    String doc_id = doc.getDocument().getId();
                                    NoteModel noteModel = doc.getDocument().toObject(NoteModel.class);
                                    noteModel.setId(doc_id);
                                    list.add(noteModel);
                                    mAdapter.notifyDataSetChanged();
                                } else if (doc.getType() == DocumentChange.Type.MODIFIED) {
                                    mAdapter.notifyDataSetChanged();
                                } else if (doc.getType() == DocumentChange.Type.REMOVED) {
                                    list.remove(doc.getOldIndex());
                                    mAdapter.notifyItemRemoved(doc.getOldIndex());
                                }
                            }
                        }catch (NullPointerException e1){
                            Toast.makeText(getContext(),"Nie udało się pobrać danych :(",Toast.LENGTH_LONG).show();
                        }
                    });

            recyclerView.setAdapter(mAdapter);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new NoteAdapter(getActivity(),list);

        } else {
            // No user is signed in
//            sendToLogin();
        }
    }
}
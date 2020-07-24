package pl.wotu.barretto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private NavController navController;
    private Animation fadeInAnim, fadeOutAnim;
    private EditText emailET;
    private EditText passwordET1;
    private EditText passwordET2;
    private Button registerButton;
    private Button goToLoginButton;
    private ProgressBar regProgress;

    private FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        emailET = view.findViewById(R.id.login_et_register);
        passwordET1 = view.findViewById(R.id.password1_et_register);
        passwordET2 = view.findViewById(R.id.password2_et_register);
        registerButton = view.findViewById(R.id.button_register);
        goToLoginButton = view.findViewById(R.id.have_acc_button_register);
        regProgress = view.findViewById(R.id.progress_register);

        fadeInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        registerButton.setOnClickListener(this);
        goToLoginButton.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
//            Toast.makeText(getContext(), "Zalogowany użytkownik:\n" + currentUser.getEmail(), Toast.LENGTH_LONG).show();
            navController.navigate(R.id.action_registerFragment_to_fingerFragment);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register:
                registerAction();

                break;
            case R.id.have_acc_button_register:
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
                break;
        }
    }

    private void registerAction() {

        String regEmail = emailET.getText().toString();
        String regPassword = passwordET1.getText().toString();
        String regPassword2 = passwordET2.getText().toString();
        if (!TextUtils.isEmpty(regEmail)&&!TextUtils.isEmpty(regPassword)&&!TextUtils.isEmpty(regPassword2)){
            regProgress.setVisibility(View.VISIBLE);
            if (regPassword.equals(regPassword2)){
                mAuth.createUserWithEmailAndPassword(regEmail,regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                                navController.navigate(R.id.action_registerFragment_to_fingerFragment);
//                            sendToMain();
                        }
                        else {
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(getContext(),"Error: "+errorMessage, Toast.LENGTH_LONG).show();
                        }
                        regProgress.setVisibility(View.INVISIBLE);
                    }
                });
            }
            else {
                Toast.makeText(getContext(),"Wpisane hasła są różne!", Toast.LENGTH_LONG).show();
            }
        }
    }

}


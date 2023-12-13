package com.uasmobilev1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment {

    FirebaseAuth auth;
    Button logoutButton;
    TextView textView;
    FirebaseUser user;

    public AccountFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        logoutButton = view.findViewById(R.id.logoutButton);
        textView = view.findViewById(R.id.user_details);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null){
//            Intent intent = new Intent(requireContext(), LoginActivity.class);
//            startActivity(intent);
//            requireActivity().finish();
            textView.setText("NULL");
        }
        else {
            textView.setText(user.getEmail());
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish();
                Toast.makeText(requireContext(), "Anda Logout dari Aplikasi", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}
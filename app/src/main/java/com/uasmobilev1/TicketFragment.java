package com.uasmobilev1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TicketFragment extends Fragment {

    private TextView textViewDate;
    private TextView textViewTime;
    private TextView textViewRoute;
    private TextView textViewBusInfo;
    private TextView textViewPrice;
    private TextView textViewSeatInfo;

    private String date;
    private String time;
    private String route;
    private String busInfo;
    private String price;
    private String seatInfo;

    CardView tiket;

    // Other variables and methods if needed

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        // Retrieve ticket data from arguments

        tiket = view.findViewById(R.id.tiket1);
        textViewDate = view.findViewById(R.id.textViewDate);
        textViewTime = view.findViewById(R.id.textViewTime);
        textViewRoute = view.findViewById(R.id.textViewRoute);
        textViewBusInfo = view.findViewById(R.id.textViewBusInfo);
        textViewPrice = view.findViewById(R.id.textViewPrice);
        textViewSeatInfo = view.findViewById(R.id.textViewSeatInfo);

        Bundle args = getArguments();
        if (args != null) {

            date = args.getString("date", "");
            time = args.getString("time", "");
            route = args.getString("route", "");
            busInfo = args.getString("busInfo", "");
            price = args.getString("price", "");
            seatInfo = args.getString("seatInfo", "");

            // Set the text of TextViews in TicketFragment
            tiket.setVisibility(View.VISIBLE);
            textViewDate.setText(date);
            textViewTime.setText(time);
            textViewRoute.setText(route);
            textViewBusInfo.setText(busInfo);
            textViewPrice.setText(price);
            textViewSeatInfo.setText(seatInfo);

        }
        retrieveTicketData();

        // Other initialization code for TicketFragment
        // ...

        return view;
    }

    private void retrieveTicketData() {
        // Get current user ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Access Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a reference to the user's tickets collection
        CollectionReference ticketsCollectionRef = db.collection("users").document(userId).collection("tickets");

        // Query the tickets collection
        ticketsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Assume there is only one ticket for simplicity
                DocumentSnapshot document = task.getResult().getDocuments().get(0);

                if (document.exists()) {
                    // DocumentSnapshot data
                    date = document.getString("date");
                    time = document.getString("time");
                    route = document.getString("route");
                    busInfo = document.getString("busInfo");
                    price = document.getString("price");
                    seatInfo = document.getString("seatInfo");

                    // Update the TextViews with the retrieved data
                    tiket.setVisibility(View.VISIBLE);
                    textViewDate.setText(date);
                    textViewTime.setText(time);
                    textViewRoute.setText(route);
                    textViewBusInfo.setText(busInfo);
                    textViewPrice.setText(price);
                    textViewSeatInfo.setText(seatInfo);
                } else {
                    // Handle the case where the document does not exist
                }
            } else {
                // Handle failures
            }
        });
    }
}
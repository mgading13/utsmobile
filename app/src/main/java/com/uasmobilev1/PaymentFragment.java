package com.uasmobilev1;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentFragment extends Fragment {

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

    Button btnPay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        // Retrieve ticket data from arguments
        Bundle args = getArguments();
        if (args != null) {
            date = args.getString("date", "");
            time = args.getString("time", "");
            route = args.getString("route", "");
            busInfo = args.getString("busInfo", "");
            price = args.getString("price", "");
            seatInfo = args.getString("seatInfo", "");

            textViewDate = view.findViewById(R.id.textViewDate);
            textViewTime = view.findViewById(R.id.textViewTime);
            textViewRoute = view.findViewById(R.id.textViewRoute);
            textViewBusInfo = view.findViewById(R.id.textViewBusInfo);
            textViewPrice = view.findViewById(R.id.textViewPrice);
            textViewSeatInfo = view.findViewById(R.id.textViewSeatInfo);
            btnPay = view.findViewById(R.id.buttonPay);
            // Example: Set the text of TextViews in PaymentFragment
            textViewDate.setText(date);
            textViewTime.setText(time);
            textViewRoute.setText(route);
            textViewBusInfo.setText(busInfo);
            textViewPrice.setText(price);
            textViewSeatInfo.setText(seatInfo);
        }

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new TicketFragment instance
                TicketFragment ticketFragment = new TicketFragment();

                // Pass ticket data as arguments
                Bundle args = new Bundle();
                args.putString("date", date);
                args.putString("time", time);
                args.putString("route", route);
                args.putString("busInfo", busInfo);
                args.putString("price", price);
                args.putString("seatInfo", seatInfo);
                ticketFragment.setArguments(args);

                textViewDate.setText(date);
                textViewTime.setText(time);
                textViewRoute.setText(route);
                textViewBusInfo.setText(busInfo);
                textViewPrice.setText(price);
                textViewSeatInfo.setText(seatInfo);

                // Open the TicketFragment
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, ticketFragment)
                        .addToBackStack(null)
                        .commit();

                saveTicketToFirebase();

                // Set the selected item in the BottomNavigationView to navigation_ticket
                //if (getActivity() != null && getActivity() instanceof MainActivity) {
                //    ((MainActivity) getActivity()).setSelectedNavItem(R.id.navigation_ticket);
                //}
            }
        });

        // Other initialization code for PaymentFragment
        // ...

        return view;
    }

    private void saveTicketToFirebase() {
        // Get current user ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Access Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a tickets collection and document for the user
        String collectionPath = "users/" + userId + "/tickets"; // Adjust the path based on your database structure
        db.collection(collectionPath)
                .add(getTicketData()) // Create a method to get the ticket data as a Map
                .addOnSuccessListener(documentReference -> {
                    // Handle success, you can show a success message or perform additional actions
                    // For example, you can retrieve the document ID using documentReference.getId()
                })
                .addOnFailureListener(e -> {
                    // Handle failure, show an error message or log the error
                });
    }

    private Map<String, Object> getTicketData() {
        // Create a Map to store ticket data
        Map<String, Object> ticketData = new HashMap<>();
        ticketData.put("date", date);
        ticketData.put("time", time);
        ticketData.put("route", route);
        ticketData.put("busInfo", busInfo);
        ticketData.put("price", price);
        ticketData.put("seatInfo", seatInfo);

        return ticketData;
    }
}

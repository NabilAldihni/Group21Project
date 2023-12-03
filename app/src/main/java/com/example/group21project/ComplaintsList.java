package com.example.group21project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class ComplaintsList extends Fragment {
    private RecyclerView recyclerView;
    private ComplaintsAdapter adapter;
    private List<Complaint> complaintList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaints_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_complaints_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        complaintList = new ArrayList<>();
        adapter = new ComplaintsAdapter(complaintList);
        recyclerView.setAdapter(adapter);
        fetchData();
        return view;
    }

    private void fetchData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("complaints")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        complaintList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Complaint complaint = document.toObject(Complaint.class);
                            complaintList.add(complaint);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
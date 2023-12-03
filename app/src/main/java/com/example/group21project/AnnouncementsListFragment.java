package com.example.group21project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsListFragment extends Fragment {
    private RecyclerView recyclerView;
    private AnnouncementsAdapter adapter;
    private List<Announcement> announcementsList;
    public AnnouncementsListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_announcements_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_announcements_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        announcementsList = new ArrayList<>();
        adapter = new AnnouncementsAdapter(announcementsList);
        recyclerView.setAdapter(adapter);
        fetchData();
        return view;
    }

    private void fetchData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Announcements")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        announcementsList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Announcement announcement = document.toObject(Announcement.class);
                            announcementsList.add(announcement);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
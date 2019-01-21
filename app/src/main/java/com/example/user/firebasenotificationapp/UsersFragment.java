package com.example.user.firebasenotificationapp;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.firebasenotificationapp.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class UsersFragment extends android.support.v4.app.Fragment{
private List<Users> usersList;
private FirebaseFirestore mFirestore;

RecyclerView recyclerView;
UserRecyclerViewAdapter userRecyclerViewAdapter;
DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
    public UsersFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        usersList = new ArrayList<>();

        userRecyclerViewAdapter = new UserRecyclerViewAdapter(usersList,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        recyclerView.setAdapter(userRecyclerViewAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        usersList.clear();

        mFirestore.collection("Users").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        Users users = doc.getDocument().toObject(Users.class);
                        usersList.add(users);
                        userRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        //databaseReference.addValueEventListener(new ValueEventListener() {
            //@Override
            //public void onDataChange(DataSnapshot dataSnapshot) {
                //for(DataSnapshot UserList : dataSnapshot.getChildren()){

                    //String user_id = UserList.getKey();
                    //Users users = UserList.getValue(Users.class).WithId(user_id);
                    //usersList.add(users);
                    //userRecyclerViewAdapter.notifyDataSetChanged();
                //}
            //}

            //@Override
            //public void onCancelled(DatabaseError databaseError) {

            //}
        //});
    }
}

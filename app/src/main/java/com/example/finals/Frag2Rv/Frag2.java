package com.example.finals.Frag2Rv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.finals.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Frag2 extends Fragment {


    ArrayList<ModalClassNotes> notes;
    RecyclerView rv;
    RvAdapter adapter;

    RecyclerView.LayoutManager manager;

    FloatingActionButton fab;

    public Frag2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv= view.findViewById(R.id.myRV);
        fab= view.findViewById(R.id.floatingAddNote);
        manager = new LinearLayoutManager(requireContext());
        rv.setLayoutManager(manager);

        notes = new ArrayList<>();
        adapter = new RvAdapter(requireContext(), notes);
        rv.setAdapter(adapter);


        fab.setOnClickListener((v)-> {

            View dialogview = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setView(dialogview)
                    .setTitle("Add Note")
                    .setNeutralButton("Cancel", (dialog, which) -> {
                        //remove the dialog box
                        dialog.dismiss();
                    })
                    .setPositiveButton("Add", (dialog, which) -> {

                        EditText etTitle = dialogview.findViewById(R.id.etTitle);
                        EditText etDesc = dialogview.findViewById(R.id.etDescription);



                        String title = etTitle.getText().toString();
                        String description = etDesc.getText().toString();

                        if(!title.equals("") && !description.equals("")) {
                            ModalClassNotes note = new ModalClassNotes(title, description);
                            notes.add(note);
                            adapter.notifyDataSetChanged();
                        }

                    });

            builder.create().show();

        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag2, container, false);
    }
}
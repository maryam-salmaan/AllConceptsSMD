package com.example.finals.FragVolley;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.finals.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragvolly extends Fragment {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<Note> noteList;

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragvolly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton fab = view.findViewById(R.id.fabAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        noteList = new ArrayList<>();
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new NoteAdapter.OnNoteActionListener() {
            @Override
            public void onTap(Note note, int position) {
                updateNote(note, position);
            }

            @Override
            public void onLongPress(Note note, int position) {
                deleteNote(note, position);
            }
        });



        fetchNotes();

        fab.setOnClickListener(v ->
                createNote("New Note", "Created via Volley Singleton"));
    }

    // ---------------- GET ----------------
    private void fetchNotes() {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                BASE_URL,
                null,
                response -> {
                    noteList.clear();
                    for (int i = 0; i < 10; i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            noteList.add(new Note(
                                    obj.getInt("id"),
                                    obj.getString("title"),
                                    obj.getString("body")
                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(requireContext(),
                        "GET failed", Toast.LENGTH_SHORT).show()
        );

        VolleySingleton.getInstance(requireContext())
                .addToRequestQueue(request);
    }

    // ---------------- POST ----------------
    private void createNote(String title, String desc) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                BASE_URL,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        noteList.add(0, new Note(
                                obj.getInt("id"),
                                obj.getString("title"),
                                obj.getString("body")
                        ));
                        adapter.notifyItemInserted(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(requireContext(),
                        "POST failed", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("body", desc);
                params.put("userId", "1");
                return params;
            }
        };

        VolleySingleton.getInstance(requireContext())
                .addToRequestQueue(request);
    }

    // ---------------- PUT ----------------
// ---------------- PUT ----------------
    private void updateNote(Note note, int position) {
        String url = BASE_URL + "/" + note.getId();

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("title", note.getTitle() + " (Updated)");
            jsonBody.put("body", note.getDescription());
            jsonBody.put("userId", 1);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.PUT,
                    url,
                    jsonBody,
                    response -> {
                        // Update local data after server confirms success
                        note.setTitle(note.getTitle() + " (Updated)");
                        adapter.notifyItemChanged(position);
                        Toast.makeText(requireContext(), "UPDATE successful", Toast.LENGTH_SHORT).show();
                    },
                    error -> Toast.makeText(requireContext(),
                            "UPDATE failed", Toast.LENGTH_SHORT).show()
            );

            VolleySingleton.getInstance(requireContext())
                    .addToRequestQueue(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // ---------------- DELETE ----------------
    private void deleteNote(Note note, int position) {
        String url = BASE_URL + "/" + note.getId();

        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                url,
                response -> {
                    noteList.remove(position);
                    adapter.notifyItemRemoved(position);
                },
                error -> Toast.makeText(requireContext(),
                        "DELETE failed", Toast.LENGTH_SHORT).show()
        );

        VolleySingleton.getInstance(requireContext())
                .addToRequestQueue(request);
    }
}

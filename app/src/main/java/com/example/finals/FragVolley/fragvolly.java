package com.example.finals.FragVolley;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.finals.R;
import com.example.finals.firebaseRvCRUD.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class fragvolly extends Fragment {
    private TextView textViewResult;

    private RequestQueue requestQueue;
    private List<Post> postList;

    public fragvolly() {
        // Required empty public constructor
    }

    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewResult = view.findViewById(R.id.textViewResult);
        postList = new ArrayList<>();

        context= requireContext();
        requestQueue = Volley.newRequestQueue(context);

        fetchPosts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragvolly, container, false);
    }

    private void fetchPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        // Parse JSON Array
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject postObject = response.getJSONObject(i);
                            int userId = postObject.getInt("userId");
                            int id = postObject.getInt("id");
                            String title = postObject.getString("title");
                            String body = postObject.getString("body");
                            Post post = new Post(userId,String.valueOf( id), title, body);
                            postList.add(post);
                        }
                    // Display first 5 posts
                        displayPosts();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(context,"Error: " + error.getMessage(),Toast.LENGTH_SHORT).show()
        );
        // Add request to queue
        requestQueue.add(jsonArrayRequest);
    }

    private void displayPosts() {
        StringBuilder result = new StringBuilder();
        // Display first 5 posts only
        int limit = Math.min(5, postList.size());
        for (int i = 0; i < limit; i++) {
            Post post = postList.get(i);
            result.append("ID: ").append(post.getId()).append("\n");

            result.append("Title: ").append(post.getTitle()).append("\n");
            result.append("Body: ").append(post.getBody()).append("\n\n");
        }
        textViewResult.setText(result.toString());
    }


}
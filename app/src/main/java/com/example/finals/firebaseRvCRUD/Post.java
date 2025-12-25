package com.example.finals.firebaseRvCRUD;


    public class Post {
        private int userId;
        private String id; // Change to String
        private String title;
        private String body;

        public Post() { } // Required for Firebase

        public Post(int userId, String id, String title, String body) {
            this.userId = userId;
            this.id = id;
            this.title = title;
            this.body = body;
        }

        // Getters
        public int getUserId() { return userId; }
        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getBody() { return body; }

        // Setters
        public void setUserId(int userId) { this.userId = userId; }
        public void setId(String id) { this.id = id; }
        public void setTitle(String title) { this.title = title; }
        public void setBody(String body) { this.body = body; }
    }

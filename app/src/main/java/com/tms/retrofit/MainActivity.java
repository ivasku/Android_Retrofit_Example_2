package com.tms.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView result;

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.text_result);

        //log http requests to logcat
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        // we create retrifut object with static main URL
        // and we specify the JSON converter we use
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient) //add client to retrofit so we could see in the logcat http logs
                .build();

        //now we can create out API where the Retrofit will implement the functions found in this interface
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPosts();

        //getComments();

        //getPostsQuerry();

        //getPostsQuerryMul();

        //getPostsQuerryMulCustom();

        //getCommentsCustom();


        //createPost();

        //createPostEncArr();

        //updatePost();

        //patchPost();

    }


    private void getPosts() {

        // to execute our network request we use Call object
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        // we call enque to run on backround thread
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                // if response is not 404
                // response should be something between 200 and 300
                // we check if the response is ok, if it is not, simply return
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                // we get our list of posts, every Json object
                List<Post> post = response.body();

                for (Post data: post) {

                    String content = "";
                    content += "ID: " + data.getId() + "\n";
                    content += "User ID: " + data.getUserId() + "\n";
                    content += "Title: " + data.getTitle() + "\n";
                    content += "Text: " + data.getText() + "\n\n";

                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "Error in call enque" + t.getLocalizedMessage());
                Log.d(TAG, "Error in call enque" + t.getMessage());
                Log.d(TAG, "Error in call enque" + t.getCause());
            }
        });
    }

    private void getComments() {

        Call<List<Comments>> commentsCall = jsonPlaceHolderApi.getComments(3);

        commentsCall.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                List<Comments> comments = response.body();

                for (Comments com : comments) {
                    String content = "";
                    content += "ID: " + com.getId() + "\n";
                    content += "postId: " + com.getPostId()+ "\n";
                    content += "name: " + com.getName()+ "\n";
                    content += "mail: " + com.getMail()+ "\n";
                    content += "body: " + com.getBody()+ "\n\n";

                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Log.d(TAG, "Error in call enque comments" + t.getLocalizedMessage());
                Log.d(TAG, "Error in call enque comments" + t.getMessage());
                Log.d(TAG, "Error in call enque comments" + t.getCause());
            }
        });
    }

    private void getPostsQuerry() {

        // to execute our network request we use Call object
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsQuerry(4);

        // we call enque to run on backround thread
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                // if response is not 404
                // response should be something between 200 and 300
                // we check if the response is ok, if it is not, simply return
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                // we get our list of posts, every Json object
                List<Post> post = response.body();

                for (Post data: post) {

                    String content = "";
                    content += "ID: " + data.getId() + "\n";
                    content += "User ID: " + data.getUserId() + "\n";
                    content += "Title: " + data.getTitle() + "\n";
                    content += "Text: " + data.getText() + "\n\n";

                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "Error in call enque" + t.getLocalizedMessage());
                Log.d(TAG, "Error in call enque" + t.getMessage());
                Log.d(TAG, "Error in call enque" + t.getCause());
            }
        });
    }

    private void getPostsQuerryMul() {

        // to execute our network request we use Call object
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsQuerryMul(4, "id", "desc");

        // we call enque to run on backround thread
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                // if response is not 404
                // response should be something between 200 and 300
                // we check if the response is ok, if it is not, simply return
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                // we get our list of posts, every Json object
                List<Post> post = response.body();

                for (Post data: post) {

                    String content = "";
                    content += "ID: " + data.getId() + "\n";
                    content += "User ID: " + data.getUserId() + "\n";
                    content += "Title: " + data.getTitle() + "\n";
                    content += "Text: " + data.getText() + "\n\n";

                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "Error in call enque" + t.getLocalizedMessage());
                Log.d(TAG, "Error in call enque" + t.getMessage());
                Log.d(TAG, "Error in call enque" + t.getCause());
            }
        });
    }

    private void getPostsQuerryMulCustom() {

        Map<String, String> querryMap = new HashMap<>();
        querryMap.put("userId", "1");
        querryMap.put("_sort", "id");
        querryMap.put("_order", "desc");


        // to execute our network request we use Call object
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsQuerryMulCustom(querryMap);

        // we call enque to run on backround thread
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                // if response is not 404
                // response should be something between 200 and 300
                // we check if the response is ok, if it is not, simply return
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                // we get our list of posts, every Json object
                List<Post> post = response.body();

                for (Post data: post) {

                    String content = "";
                    content += "ID: " + data.getId() + "\n";
                    content += "User ID: " + data.getUserId() + "\n";
                    content += "Title: " + data.getTitle() + "\n";
                    content += "Text: " + data.getText() + "\n\n";

                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "Error in call enque" + t.getLocalizedMessage());
                Log.d(TAG, "Error in call enque" + t.getMessage());
                Log.d(TAG, "Error in call enque" + t.getCause());
            }
        });
    }

    private void getCommentsCustom() {

        Call<List<Comments>> commentsCall = jsonPlaceHolderApi.getCommentsCustom("posts/2/comments");

        commentsCall.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                List<Comments> comments = response.body();

                for (Comments com : comments) {
                    String content = "";
                    content += "ID: " + com.getId() + "\n";
                    content += "postId: " + com.getPostId()+ "\n";
                    content += "name: " + com.getName()+ "\n";
                    content += "mail: " + com.getMail()+ "\n";
                    content += "body: " + com.getBody()+ "\n\n";

                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Log.d(TAG, "Error in call enque comments" + t.getLocalizedMessage());
                Log.d(TAG, "Error in call enque comments" + t.getMessage());
                Log.d(TAG, "Error in call enque comments" + t.getCause());
            }
        });
    }

    private void createPost() {
        Post post = new Post (23, "NEw Title" , "New Text");

        Call <Post> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "postId: " + postResponse.getUserId()+ "\n";
                content += "name: " + postResponse.getTitle()+ "\n";
                content += "body: " + postResponse.getText()+ "\n\n";

                result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    //send values url encoded
    private void createPostEnc() {

        Call <Post> call = jsonPlaceHolderApi.createPostEncoded(23, "new title" , "new text");

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "postId: " + postResponse.getUserId()+ "\n";
                content += "name: " + postResponse.getTitle()+ "\n";
                content += "body: " + postResponse.getText()+ "\n\n";

                result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    //send values url encoded
    //use hashMap to send values to post
    private void createPostEncArr() {

        Map<String, String> fields = new HashMap<>();
        fields.put("userId" , "23");
        fields.put("title" , "new title");
        fields.put("body" , "nes body");

        Call <Post> call = jsonPlaceHolderApi.createPostEncodedArray(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "postId: " + postResponse.getUserId()+ "\n";
                content += "name: " + postResponse.getTitle()+ "\n";
                content += "body: " + postResponse.getText()+ "\n\n";

                result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void updatePost() {
        Post post = new Post(12, null, "NEw text Update");

        Call <Post> call = jsonPlaceHolderApi.putPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "postId: " + postResponse.getUserId()+ "\n";
                content += "name: " + postResponse.getTitle()+ "\n";
                content += "body: " + postResponse.getText()+ "\n\n";

                result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private void patchPost() {
        Post post = new Post(12, "aaaaaa", "NEw text Update");

        Call <Post> call = jsonPlaceHolderApi.patchPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "postId: " + postResponse.getUserId()+ "\n";
                content += "name: " + postResponse.getTitle()+ "\n";
                content += "body: " + postResponse.getText()+ "\n\n";

                result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private void deletePost() {
        Call <Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                result.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //result.setText("Code: " + response.code());
            }
        });
    }

}

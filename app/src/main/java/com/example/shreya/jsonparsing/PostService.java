package com.example.shreya.jsonparsing;

import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shreya on 29/11/17.
 */

public interface PostService {
    @GET("posts")
    retrofit2.Call<List<Post>> getPosts();

    @GET("posts")
    retrofit2.Call<List<Post>> getPosts(@Query("userId") Long userId);

    @POST("posts")
    @FormUrlEncoded
    retrofit2.Call<Post> createPost(@Field("title") String title,
            @Field("body") String body,
            @Field("userId") long userId);

    @PUT("posts/{id}")
    retrofit2.Call<Post> updatePost(@Path("id") Long id, @Body Post post);

    @DELETE("posts/{id}")
    retrofit2.Call<Void> deletePost(@Path("id") Long id);

}

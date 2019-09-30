package com.example.retrofit.data.model.remote;

import com.example.retrofit.data.model.Comments;
import com.example.retrofit.data.model.Post;
import retrofit2.Call;
import retrofit2.http.*;

public interface APIService {
    //Usando estilo formulario con campos individuales
    @POST("/posts")
    @FormUrlEncoded
    Call<Post> sendPost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);

    //Usando un solo parametro
    /*@POST("/post")
    @FormUrlEncoded
    Call<Post> sendPost(@Body Post post);*/

    //Método Put
    @PUT("/posts/{id}")
    @FormUrlEncoded
    Call<Post> updatePost(@Path("id") long id,
                          @Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") long userId);

    //Método delete
    @DELETE("/posts/{id}")
    Call<Post> deletePost(@Path("id") long id);

    //Servicios para los comentarios de Stackoverflow
    
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Comments> getComments();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Comments> getComments(@Query("tagged") String tags);
}

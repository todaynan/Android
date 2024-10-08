package com.example.todaynan.data.remote.post

import com.example.todaynan.data.entity.PostWrite
import com.example.todaynan.data.entity.ReplyWrite
import com.example.todaynan.data.remote.user.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostInterface {
    // 게시글 작성
    @POST("/post")
    fun post(
        @Header("authorization") accessToken: String,
        @Body post: PostWrite
    ): Call<PostResponse<Post>>

    // 구인 게시판 전체 검색
    @GET("/post/employ")
    fun getPostEmploy(
        @Header("authorization") accessToken: String,
        @Query("page") page: Int
    ): Call<PostResponse<GetPost>>

    // 잡담 게시판 전체 검색
    @GET("/post/chat")
    fun getChatEmploy(
        @Header("authorization") accessToken: String,
        @Query("page") page: Int
    ): Call<PostResponse<GetPost>>

    // 게시글 삭제
    @DELETE("/post/{post_id}")
    fun deletePost(
        @Header("authorization") accessToken: String,
        @Path("post_id") postId: Int
    ): Call<DeletePost>

    // 댓글 작성
    @POST("/post/comment/{post_id}")
    fun reply(
        @Header("authorization") accessToken: String,
        @Path("post_id") postId: Int,
        @Body comment: ReplyWrite
    ): Call<PostResponse<Reply>>

    // 게시글 세부사항 조회
    @GET("/post/detail/{post_id}")
    fun getReply(
        @Header("authorization") accessToken: String,
        @Path("post_id") postId: Int
    ): Call<PostResponse<GetReply>>

    // 게시글 좋아요
    @POST("/post/like/{post_id}")
    fun postLike(
        @Header("authorization") accessToken: String,
        @Path("post_id") postId: Int
    ): Call<PostResponse<PostLike>>

    // 댓글 좋아요
    @POST("/post/comment/like/{post_id}/{comment_id}")
    fun replyLike(
        @Header("authorization") accessToken: String,
        @Path("post_id") postId: Int,
        @Path("comment_id") commentId: Int
    ): Call<PostResponse<ReplyLike>>

    // 댓글 삭제
    @DELETE("/post/comment/{post_id}/{comment_id}")
    fun deleteReply(
        @Header("authorization") accessToken: String,
        @Path("post_id") postId: Int,
        @Path("comment_id") commentId: Int
    ): Call<DeleteReply>

    //HOT 게시판
    @GET("/post/hot")
    fun loadHotBoard(
        @Header("authorization") accessToken: String,
        @Query("page") page: Int
    ):Call<PostResponse<GetPost>>

    //내가 쓴 게시물
    @GET("/user/postlist/post")
    fun loadMyWritePost(
        @Header("authorization") accessToken: String,
        @Query("page") page: Int
    ):Call<PostResponse<GetPost>>

    //내가 단 댓글
    @GET("/user/postlist/comment")
    fun loadMyReplyPost(
        @Header("authorization") accessToken: String,
        @Query("page") page: Int
    ):Call<PostResponse<GetPost>>
}
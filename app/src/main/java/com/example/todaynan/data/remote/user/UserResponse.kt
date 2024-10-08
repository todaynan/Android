package com.example.todaynan.data.remote.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse<T>(
    @SerializedName(value="isSuccess") val isSuccess: Boolean,
    @SerializedName(value="code") val code: String,
    @SerializedName(value="message") val message: String,
    @SerializedName(value="result") val result: T
) : Serializable

data class Token(
    @SerializedName(value="user_id") val userId: Int,
    @SerializedName(value="created_at") val createdAt: String,
    @SerializedName(value="accessToken") val accessToken: String,
    @SerializedName(value="refreshToken") val refreshToken: String
) : Serializable

data class Login(
    @SerializedName(value="user_id") val userId: Int,
    @SerializedName(value="accessToken") val accessToken: String,
    @SerializedName(value="refreshToken") val refreshToken: String,
    @SerializedName(value="expiration") val expiration: String
) : Serializable

data class ChangeNickNameResponse(
    @SerializedName(value = "message") val message: String
) : Serializable

data class NicknameDuplicateResponse(
    @SerializedName(value = "isSuccess")val isSuccess: Boolean,
    @SerializedName(value = "code")val code: String,
    @SerializedName(value = "message")val message: String
) : Serializable
data class ChangeLocationResponse(
    @SerializedName(value = "isSuccess")val isSuccess: Boolean,
    @SerializedName(value = "code")val code: String,
    @SerializedName(value = "message")val message: String,
    @SerializedName(value = "result")val result: String
) : Serializable
data class SignOutResponse(
    @SerializedName(value = "isSuccess")val isSuccess: Boolean,
    @SerializedName(value = "code")val code: String,
    @SerializedName(value = "message")val message: String,
    @SerializedName(value = "result")val result: String
) : Serializable
data class SearchOutsideResult(
    @SerializedName("googlePlaceResultDTOList") val googlePlaceResultDTOList: List<GooglePlaceResultDTO>,
    @SerializedName("pageToken") val pageToken: String?
) : Serializable

data class GooglePlaceResultDTO(
    @SerializedName("id") val id: String, // 고유 ID 추가
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("photoUrl") val photoUrl: String,
    @SerializedName("type") val type: String,
    @SerializedName("isLike") var isLike: Boolean,
    @SerializedName("like_id") var likeId: Int?
) : Serializable

data class Geometry(
    @SerializedName("viewport") val viewport: Viewport
) : Serializable

data class Viewport(
    @SerializedName("low") val low: LatLng,
    @SerializedName("high") val high: LatLng
) : Serializable

data class LatLng(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
) : Serializable
data class PlaceLikeResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code: String,
    @SerializedName(value = "message") val message: String,
    @SerializedName(value = "result") val result: PlaceLikeResult
) : Serializable

data class PlaceLikeResult(
    @SerializedName("like_id") val likeId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
) : Serializable

data class PlaceLikeLoadResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: PlaceLikeResult
) : Serializable
data class PlaceLikeLoadResult(
    @SerializedName("userLikeItems") val userLikeItems: List<UserLikeItem>
) : Serializable
data class UserLikeItem(
    @SerializedName("like_id") val likeId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("place_address") val placeAddress: String,
    @SerializedName("image") val image: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
) : Serializable

data class PlaceUnlikeResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code: String,
    @SerializedName(value = "message") val message: String,
    @SerializedName(value = "result") val result: String
) : Serializable


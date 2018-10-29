package itboom.com.swap.app;

import com.google.gson.JsonObject;

import io.reactivex.Completable;
import io.reactivex.Single;
import itboom.com.swap.pojo.auth.AuthResponse;
import itboom.com.swap.pojo.home.HomeResponse;
import itboom.com.swap.pojo.interests.InterestsResponse;
import itboom.com.swap.pojo.my_items.MyItemsResponse;
import itboom.com.swap.pojo.swaps.SwapsResponse;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SwapAPI {

    @Headers({"Content-Type: application/json"})
    @POST("users/login")
    Single<AuthResponse> login(@Body JsonObject jsonObject);

    @Headers({"Content-Type: application/json"})
    @POST("users/register")
    Single<AuthResponse> register(@Body JsonObject jsonObject);

    @FormUrlEncoded
    @POST("auth/forget_password")
    Single<Integer> forgetPassword(@Field("email") String email);

    @Headers({"Content-Type: application/json"})
    @GET("users/interests")
    Single<InterestsResponse> getAllInterests();

    @Headers({"Content-Type: application/json"})
    @POST("users/interest-subscribe")
    Completable submitInterests(@Body JsonObject jsonObject);

    @GET("users/home/{userId}")
    Single<HomeResponse> getHome(@Path("userId") String userId);

    @GET("users/home/{userId}")
    Single<HomeResponse> getCompletedSwaps(@Path("userId") String userId);

    @GET("users/items/get-ongoing-request/{userId}")
    Single<SwapsResponse> getOngoingSwaps(@Path("userId") String userId);

    @GET("users/home/{userId}")
    Single<HomeResponse> getRejectedSwaps(@Path("userId") String userId);

    @GET("users/my-items/{userId}")
    Single<MyItemsResponse> getMyItems(@Path("userId") String userId);
}



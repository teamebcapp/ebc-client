package teamebcapp.ebc.user;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static teamebcapp.ebc.common.global.User.access_token;

public interface UserService {
    //회원가입
    @POST("user")
    Call<User> PostUser(
            @Body User user);

    //로그인
    @GET("/login")
    Call<User> GetUser(
            @Query("userid") String UserId,
            @Query("password") String Password);

    //회원정보수정
    @POST("user")
    Call<User>PutUser(
            @Body User user,
            @Header("access_token")String access_token
    );

    //post시 로그인정보 받아오기
    @GET("user")
    Call<User> GetUserInfo(
            @Query("userid") String UserId,
            @Header("access_token")String access_token);

    @GET("/token/valid")
    Call<Map<String,Object>> GetValidToken(@Header("access_token") String access_token);

}
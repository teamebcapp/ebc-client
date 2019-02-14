package teamebcapp.ebc.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {
    //회원가입
    @POST("user")
    Call<User> PostUser(
            @Body User user);

    //로그인
    @GET("user")
    Call<User> GetUser(
            @Query("userid") String UserId,
            @Query("password") String Password);

    @GET("user")
    Call<User> GetUserInfo(
            @Query("userid") String UserId);

    //내정보조회
    @GET("user")
    Call<User> GetMyUser(
            @Query("Name") String Name,
            @Query("Company") String Company,
            @Query("Position") String Position,
            @Query("Duty") String Duty,
            @Query("Phone") String Phone,
            @Query("Email") String Email);

    //명함수정
    @POST("bc")
    Call<User> PutBC(
            @Body ModiUser modiuser);

    //명함등록
    @POST("bc")
    Call<User> PostBC(
            @Body User user);

    //사용자 명함리스트조회
    //id입력시 가지고있는 명함 조회가능
    @GET("bcs")
    Call<List<User>> GetUserBCs(
            @Query("userid") String userid);

    //명함조회
    @GET("bc")
    Call<User>ListUserBC(
            @Query("bcSeq") int bcSeq);
}
package teamebcapp.ebc.BusinessCard;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import teamebcapp.ebc.common.global.varForDeleteMyBC;
import teamebcapp.ebc.common.global.varForDeleteOtherBC;

public interface BusinessCardService {
    //내 명함 수정
    @PUT("bc")
    Call<BusinessCard> PutBC(
            @Query("BcSeq") int bcSeq,
            @Header("access_token") String access_token);

    //내 명함 등록
    @POST("bc")
    Call<BusinessCard> PostUserBC(
            @Body BusinessCard user,
            @Header("access_token") String access_token);

    //사용자가 가진 my 명함리스트 조회
    //id입력시 가지고있는 명함 조회가능
    @GET("bcs")
    Call<List<BusinessCard>> GetUserBCList(
            @Query("userId") String userId,
            @Header("access_token") String access_token);

    //내가 가진 내명함조회
    @GET("bc")
    Call<BusinessCard> GetMyBC(
            @Query("bcSeq") int bcSeq,
            @Header("access_token") String access_token);

    //BCs which a user have
    @GET("owner/bcs")
    Call<List<BusinessCard>> GetBCs(@Query("ownerUserid") String ownerUserid,
                                    @Header("access_token") String access_token);

    //other's new BC
    @POST("owner/bc")
    Call<BusinessCard> PostBC(@Body BusinessCard user,
                              @Header("access_token") String access_token);

    //내가 가진 다른사람명함 삭제
//    @DELETE("owner/bc")
//    Call<BusinessCard> DeleteOtherBC(@Body BusinessCard OwnerSeq,
//                                     @Header("access_token") String access_token);

    @HTTP(method="DELETE",path = "owner/bc",hasBody = true)
    Call<Map<String,Object>> DeleteOtherBC(@Body varForDeleteOtherBC var,
                                     @Header("access_token") String access_token);

    //내가 가진 내 명함 삭제
    //@DELETE("/bc")
    @HTTP(method="DELETE",path = "/bc",hasBody = true)
    Call<Map<String,Object>> DeleteMyBC(
            @Body varForDeleteMyBC var,
            @Header("access_token") String access_token);

    //내가 가진 명함 조회
    @GET("owner/bc")
    Call<BusinessCard> GetOtherBC(@Query("OwnerSeq") int OwnerSeq, @Header("access_token") String access_token);
}

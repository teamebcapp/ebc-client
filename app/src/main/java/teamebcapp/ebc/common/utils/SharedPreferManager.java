package teamebcapp.ebc.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferManager {
    private static final String tokenName = "tokenFile";
    private static final String tokenKey = "access_token";
    private static final String tokenCheck ="token_check";

    public static void setAccessToken(Context context,String access_token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(tokenName,MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tokenKey,access_token);
        //최종 커밋
        editor.commit();
    }
    public static String getAccessToken(Context context) {
        SharedPreferences sf = context.getSharedPreferences(tokenName,MODE_PRIVATE);
        return sf.getString(tokenKey,"");
    }

    public static Boolean getCheck(Context context){
        SharedPreferences sf= context.getSharedPreferences(tokenCheck,MODE_PRIVATE);
        return sf.getBoolean(tokenCheck,false);
    }

    public static void setTokenCheck(Context context,Boolean check){
        SharedPreferences sf=context.getSharedPreferences(tokenCheck,MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean(tokenCheck,check);
        editor.commit();
    }

    public static void removeAllPreferences(Context context){
        SharedPreferences sf = context.getSharedPreferences(tokenName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.clear();
        editor.commit();
    }

    public static void removeCheckInfo(Context context){
        setTokenCheck(context, false);
    }
}

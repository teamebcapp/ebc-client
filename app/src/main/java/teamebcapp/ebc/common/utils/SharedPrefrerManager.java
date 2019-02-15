package teamebcapp.ebc.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefrerManager {
    private static final String tokenName = "tokenFile";
    private static final String tokenKey = "access_token";

    public static void setAccessToken(Context context,String access_token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(tokenName,MODE_PRIVATE);

        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tokenKey,access_token);
        //최종 커밋
        editor.commit();
    }
    public static String getAccessToken(Context context) {
        SharedPreferences sf = context.getSharedPreferences(tokenName,MODE_PRIVATE);
        return sf.getString(tokenKey,"");
    }
}

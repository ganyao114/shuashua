package com.shuashua.buss.Utils;

import com.shuashua.buss.Model.Beans.User;

/**
 * Created by pc on 16/8/9.
 */
public class Global {
    public static User user = new User();

    public static final String BASE_URL = "http://192.168.10.132:8080";

    public final static String SKEY_LOGIN = "login";
    public final static String SKEY_UNLOGIN = "beforelogin";
}

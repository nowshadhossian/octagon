package com.kids.crm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {
    public static final String ANSWERED="ANSWERED";
    public static final String SKIPPED="SKIPPED";

    public static final long TOKEN_DURATION = 24*60*60;
    public static final String KEY_ID = "id";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_EMAIL = "email";

    public static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
}

package com.dafian.android.rssfeeder.util;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

/**
 * @author Dafian on 10/6/17
 */

public class Helper {

    @NonNull
    public static String convertDateRss(String publishDate) {

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        Date date;

        try {
            date = format.parse(publishDate);
        } catch (ParseException e) {
            Timber.e(e, "Error");
            date = new Date();
        }

        return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();
    }

    public static Date getDateFromRssItem(String publishDate) {

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        Date date;

        try {
            date = format.parse(publishDate);
        } catch (ParseException e) {
            Timber.e(e, "Error");
            date = new Date();
        }

        return date;
    }
}

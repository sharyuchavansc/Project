package com.example.foodiesta.utils;

public class Utils {

    public static String getUrl(String path) {
        return Constants.BASE_URL + path;
    }

    public static String createUrl(String route) {
        return Constants.httpUrl + "/" + route;
    }

    }


package com.nitish.qa.utility;

import com.github.javafaker.Faker;

public final class FakerUtils {

    public static String generateName(){
        return new Faker().regexify("[A-Za-z0-9 ,_-]{10}");
    }

    public static String generatePlaylistName() { return new Faker().music().genre(); }

    public static String generateDescription(){
        return new Faker().regexify("[A-Za-z0-9,./_-]{20}");
    }

}
package com.ntc.httpserver.http;

import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1",1,1);
     public final String Literal;
     public final int Major;
     public final int Minor;

     HttpVersion(String literal, int major,int minor) {
         this.Literal = literal;
         this.Major = major;
         this.Minor = minor;
     }

     private static final Pattern httpVersionRegexPatter=Pattern.compile("^HTTP/(?<major>\\d+)\\.(?<minor>\\d+)");
     public static HttpVersion getBestCompatibleVersion( String literalVersion ) throws HttpParsingException, BadHttpVersionException {
         Matcher matcher=httpVersionRegexPatter.matcher(literalVersion);
         if(!matcher.matches()){
             throw new BadHttpVersionException();
         }

         int major=Integer.parseInt(matcher.group("major"));
         int minor=Integer.parseInt(matcher.group("minor"));

         HttpVersion compatibleVersion=null;
         for(HttpVersion httpVersion:HttpVersion.values()){
             if(httpVersion.Literal.equals(literalVersion)){
                 return httpVersion;
             }
             else {
                 if(httpVersion.Major==major){
                     if(httpVersion.Minor<minor){
                         compatibleVersion=httpVersion;
                     }
                 }

             }
         }
         return compatibleVersion;
     }
}

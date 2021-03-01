package com.cotivity.ticket_reservation_authorization_server.utils;

 public class RequiredConstant {

     public static class URLConstant {
        public final static String API_VERSION = "/api/v1";
    }

     public static class FeatureAPIConstant {
         public final static String USER_API = "/user";
         public final static String TOKEN_API = "/oauth/token";
     }

     public static class MessageConstant {
         public final static String USER_CREATED = "User has been successfully added!!!";
     }

     public static class FieldValidatorConstant {
         public final static String USERNAME_REQUIRED = "Username is required";
         public final static String PASSWORD_REQUIRED = "Password is required";
     }
}

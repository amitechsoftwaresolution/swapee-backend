package io.swapee.swapeebackend.commonLibrary.conf;

/**
 * @author Minoltan Issack on 5/14/2022
 */
public class Parameter {

    /////////////////////////////////////////////////////////////////////////
    //              AUTHENTICATION SEVER CONFIG
    /////////////////////////////////////////////////////////////////////////
    public static final String JWT_TOKEN_TYPE_ACCESS = "ACCESS";
    public static final String JWT_TOKEN_TYPE_REFRESH = "REFRESH";

    //////////////////////////////////////////////////////////////////////////
    //          JWT CONFIGURATION
    //////////////////////////////////////////////////////////////////////////

    public static final String URL = "/login";
    public static final String AUTH_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer";

    //////////////////////////////////////////////////////////////////////////
    //         PLATFORM
    //////////////////////////////////////////////////////////////////////////
    public static final String PLATFORM_SCHOOL = "SCHOOL";
    public static final String PLATFORM_INSTITUTE = "INSTITUTE";

    /////////////////////////////////////////////////////////////////////////
    //          USER ROLES
    /////////////////////////////////////////////////////////////////////////
    public static final String USER_NORMAL = "USER";
    public static final String USER_DEVELOPER = "DEV";
    public static final String USER_ADMIN = "ADMIN";

    /////////////////////////////////////////////////////////////////////////
    //          HOME WORK
    /////////////////////////////////////////////////////////////////////////
    public static final String HOME_WORK_FINISHED = "Finished";
    public static final String HOME_WORK_PENDING = "Pending";

    ///////////////////////////////////////////////////////////////////////
    //             Exception Messages
    ///////////////////////////////////////////////////////////////////////
    public static final String ERROR_MESSAGE_MODIFIED_REQUEST = "Modified Request.";
    public static final String ERROR_MESSAGE_WRONG_PAGE_SIZE = "Wrong page size";
    public static final String ERROR_MESSAGE_DATABASE_ERROR = "Database server query executing error.";
    public static final String ERROR_MESSAGE_FRONT_END = "Sorry something went wrong! But we are working hard to get this fixed for you. Please try again in a few minutes.";

}



package com.ulas.constants;

public class EndPoints {

    public static final String VERSION = "/v1";
    public static final String DEV = "/dev";
    public static final String API = "/api";
    public static final String FLIGHTS= API+VERSION+DEV+"/flights";
    public static final String FINDFLIGHTS= API+VERSION+DEV+"/findflights";
    public static final String AIRPORT= API+VERSION+DEV+"/airport";
    public static final String USER= API+VERSION+DEV+"/user";

    public static final String CREATE="/create";
    public static final String UPDATE="/update";
    public static final String DELETE="/delete";
    public static final String GET_ALL="/get-all";
    public static final String DO_LOGIN="/do-login";
    public static final String VALIDATE_TOKEN="/validate-token";

}

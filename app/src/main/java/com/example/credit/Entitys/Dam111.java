package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class Dam111 {
    private String status;
    private String info;
    private String infocode;
    private String count;
    private List<GeocodesBean> geocodes;

    public static class GeocodesBean {
        private String formatted_address;
        private String province;
        private String citycode;
        private String city;
        private String adcode;
        private String location;
        private String level;
        private List<?> district;
        private List<?> township;
        private List<?> street;
        private List<?> number;


    }
}



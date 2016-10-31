package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class Dam {

    private String status;
    private String info;
    private String infocode;
    private String count;//公交换乘方案数目
    private RouteBean route;
    public static class RouteBean {//公交换乘信息列表
        private String origin;//起点坐标
        private String destination;//终点坐标
        private String distance;//起点和终点的步行 距离：米
        private String taxi_cost;//出租车费用 单位：元
        private List<TransitsBean> transits;
        public static class TransitsBean {//公交换乘方案列表
            private String cost;//此换乘方案价格 单位：元
            private String duration;//此换乘方案预期时间  单位：秒
            private String nightflag;//是否是夜班车 0：非夜班车；1：夜班车
            private String walking_distance;//此方案总步行距离 单位：米
            private String distance;//起点和终点的步行 距离：米
            private String missed;
            private List<SegmentsBean> segments;

            public static class SegmentsBean {
                private WalkingBean walking;//此路段步行导航信息
                private BusBean bus;//此路段公交导航信息
                private RailwayBean railway;
                private List<?> taxi;
                private List<?> entrance;
                private List<?> exit;
                public static class WalkingBean {
                    private String origin;//起点坐标
                    private String destination;//终点坐标
                    private String distance;//每段规划线路的步行距离 单位：米
                    private String duration;//步行时间预计 单位：秒
                    private List<StepsBean> steps;//步行路段列表
                    public static class StepsBean {
                        private String instruction;//终点坐标
                        private String road;//道路名称
                        private String distance;//起点和终点的步行距离 单位：米
                        private String polyline;//
                        private String action;//
                        private List<?> duration;//此路段预计步行时间单位：秒
                        private List<?> assistant_action;//
                    }
                }
                public static class BusBean {
                    private List<BuslinesBean> buslines;//

                    public static class BuslinesBean {
                        private DepartureStopBean departure_stop;//
                        private ArrivalStopBean arrival_stop;//
                        private String name;//
                        private String id;//
                        private String type;//
                        private String distance;//
                        private String duration;//
                        private String polyline;//
                        private String via_num;//
                        private List<?> start_time;//
                        private List<?> end_time;//
                        private List<ViaStopsBean> via_stops;//
                        public static class DepartureStopBean {
                            private String name;//
                            private String id;//
                            private String location;//
                        }

                        public static class ArrivalStopBean {
                            private String name;//
                            private String id;//
                            private String location;//
                        }
                        public static class ViaStopsBean {
                            private String name;//
                            private String id;//
                            private String location;//
                        }
                    }
                }
                public static class RailwayBean {
                    private List<?> spaces;//
                }
            }
        }
    }
}



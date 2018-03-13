package com.water.photo.common;

/**
 * @author chezhu.xin
 */
public interface Const {
    enum Flow {
        FLOW_1(1, "天线型号"),
        FLOW_2(2, "RRU型号"),
        FLOW_3(3, "小区1天线至RRU整体照"),
        FLOW_4(4, "小区2天线至RRU整体照"),
        FLOW_5(5, "小区3天线至RRU整体照"),
        FLOW_6(6, "小区1RRU至天线整体照"),
        FLOW_7(7, "小区2RRU至天线整体照"),
        FLOW_8(8, "小区3RRU至天线整体照"),
        FLOW_9(9, "小区1天线覆盖范围"),
        FLOW_10(10, "小区2天线覆盖范围"),
        FLOW_11(11, "小区3天线覆盖范围"),
        FLOW_12(12, "基站整体照"),
        FLOW_13(13, "馈线窗电源线接地"),
        FLOW_14(14, "综合柜编号"),
        FLOW_15(15, "综合柜内部整体照"),
        FLOW_16(16, "综合柜2编号"),
        FLOW_17(17, "综合柜2内部整体照"),
        FLOW_18(18, "DCDU整体照"),
        FLOW_19(19, "BBU整体照"),
        FLOW_20(20, "BBU小区端口照"),
        FLOW_21(21, "BBU标签照"),
        FLOW_22(22, "PTN本端口照"),
        FLOW_23(23, "PTN上端口照");
        private final int id;
        private final String name;

        Flow(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static Flow getFlow(int id) {
            for (Flow flow : Flow.values()) {
                if (flow.id == id) {
                    return flow;
                }
            }
            return null;
        }
    }
}

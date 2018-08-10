package com.kaishengit.erp.utils;

/**
 * @author liuyan
 * @date 2018/7/24
 */
public class Constant {
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer STATE_NORMAL = 1;
    public static final Integer STATE_FROZEN = 0;

    public static final String PERMISSION_TYPE_MENU = "菜单";
    public static final String PERMISSION_TYPE_BUTTON = "按钮";

    /**
     * 订单状态 1：新订单 2：已下发 3：维修中 4：维修完成 5：质检中 6：结算中 7：完成
     */
    public static final String ORDER_STATE_NEW = "1";
    public static final String ORDER_STATE_TRANS = "2";
    public static final String ORDER_STATE_FIXING = "3";
    public static final String ORDER_STATE_FIXED = "4";
    public static final String ORDER_STATE_CHECKING = "5";
    public static final String ORDER_STATE_SETTLEMENT = "6";
    public static final String ORDER_STATE_DONE = "7";


    // 工时费
    public static final int DEFAULT_HOUR_FEE = 50;
}

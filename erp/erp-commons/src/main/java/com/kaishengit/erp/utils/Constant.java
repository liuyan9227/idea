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

    // 订单状态 1：待维修 2：维修中 3：质检中 4：结算中 5：完成
    public static final String ORDER_STATE_NEW = "1";
    public static final String ORDER_STATE_FIXING = "2";
    public static final String ORDER_STATE_CHECKING = "3";
    public static final String ORDER_STATE_SETTLEMENT = "4";
    public static final String ORDER_STATE_DONE = "5";


}

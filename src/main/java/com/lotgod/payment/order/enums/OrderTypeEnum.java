package com.lotgod.payment.order.enums;

public enum OrderTypeEnum {

    NORMAL(0,"正常订单"),REFUND(1,"退款订单");

    private Integer code;
    private String desc;

    OrderTypeEnum(Integer code,String desc){

        this.code=code;
        this.desc = desc;
    }

    public Integer getCode(){
        return code;
    }


    public String getDesc() {
        return desc;
    }

    /**

     * 根据值获取枚举对象
     * @param code
     * @return
     */
    public static String getDesc(int code) {
        for (OrderTypeEnum item : OrderTypeEnum.values()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }
        return null;
    }
}

package indi.simuel.enums;

public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"),
    INNER_ERROR(-1001, "内部系统错误"), NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP(-1003, "shop信息为空");

    private final int state;
    private final String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 返回操作完店铺后的状态
     *
     * @param state 传入的状态参数
     * @return 操作完成后的状态
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}

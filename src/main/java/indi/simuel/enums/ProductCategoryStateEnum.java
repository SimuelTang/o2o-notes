package indi.simuel.enums;

/**
 * @Author simuel_tang
 * @Date 2021/3/6
 * @Time 9:43
 */
public enum ProductCategoryStateEnum {
    SUCCESS(1, "创建成功"), INNER_ERROE(-1001, "操作失败"), EMPTY_LIST(-1002, "空类别");

    private final int state;
    private final String stateInfo;

    ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductCategoryStateEnum stateOf(int index) {
        for (ProductCategoryStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}

package indi.simuel.enums;

import org.junit.Test;

/**
 * @Author simuel_tang
 * @Date 2021/2/22
 * @Time 13:01
 */
public class EnumTest {
    
    @Test
    public void ShopEnumTest() {
        ShopStateEnum[] values = ShopStateEnum.values();
        for (ShopStateEnum value : values) {
            System.out.println(value.getState());
            System.out.println(value.getStateInfo());
            System.out.println("---");
        }
    }
}

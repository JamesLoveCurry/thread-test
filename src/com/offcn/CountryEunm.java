package com.offcn;

/**
 * @author
 * @create 2020-02-26 20:19
 */
public enum CountryEunm {
    ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"韩国"),FOUR(4,"燕国"),FIVE(5,"赵国"),SIX(6,"魏国");

    private Integer retCode;
    private String retMessage;

    CountryEunm(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEunm forEach_CountryEunm(int index){
        CountryEunm[] values = CountryEunm.values();
        for (CountryEunm value : values) {
            if (value.retCode == index){
                return value;
            }
        }
        return null;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }
}

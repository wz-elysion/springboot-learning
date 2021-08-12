package wz_ling.learning.mybatis.typeHandler;


import lombok.Getter;

@Getter
public enum GenderEnum {

    MALE(1, "男"),
    FEMALE(2, "女"),
    UNKNOWN(-1, "未知");

    private Integer code;
    private String desc;

    GenderEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static GenderEnum getGenderEnum(Integer code) {
        if (MALE.code.equals(code)) {
            return MALE;
        } else if (FEMALE.code.equals(code)) {
            return FEMALE;
        } else {
            return UNKNOWN;
        }
    }

}

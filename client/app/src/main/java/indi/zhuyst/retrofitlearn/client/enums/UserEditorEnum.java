package indi.zhuyst.retrofitlearn.client.enums;

/**
 * Created by zhuyst on 2017/8/26.
 */

public enum  UserEditorEnum {
    username(""), password(""), remember_checked(false), autoLogin_checked(false);

    private Object defaultValue;

    UserEditorEnum(Object defaultValue){
        this.defaultValue = defaultValue;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getStringDefaultValue(){
        if(defaultValue instanceof String){
            return (String)defaultValue;
        }
        else {
            return "";
        }
    }

    public Boolean getBooleanDefaultValue(){
        if(defaultValue instanceof Boolean){
            return (Boolean)defaultValue;
        }
        else {
            return false;
        }
    }
}

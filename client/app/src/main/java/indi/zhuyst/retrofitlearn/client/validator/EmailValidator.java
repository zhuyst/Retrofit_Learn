package indi.zhuyst.retrofitlearn.client.validator;

import com.rengwuxian.materialedittext.validation.RegexpValidator;

/**
 * Created by zhuyst on 2017/8/26.
 */

public class EmailValidator extends RegexpValidator{
    private static final String REGEX = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";

    public EmailValidator(){
        super("电子邮箱格式不正确！",REGEX);
    }
}

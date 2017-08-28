package indi.zhuyst.retrofitlearn.client.validator;

import com.rengwuxian.materialedittext.validation.RegexpValidator;

/**
 * Created by zhuyst on 2017/8/26.
 */

public class EmptyValidator extends RegexpValidator{
    private static final String REGEX = "[^\\s]{1,}";

    public EmptyValidator(String errorMessage){
        super(errorMessage,REGEX);
    }
}

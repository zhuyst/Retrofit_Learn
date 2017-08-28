package indi.zhuyst.retrofitlearn.client.validator;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by zhuyst on 2017/8/26.
 */

public class LengthValidator extends METValidator{
    private Integer min;
    private Integer max;

    public LengthValidator(Integer min,Integer max){
        super("长度不正确");
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        if(min != null && text.length() < min){
            setErrorMessage("长度不能小于" + min);
            return false;
        }
        else if(max != null && text.length() > max){
            setErrorMessage("长度不能大于" + max);
            return false;
        }
        else {
            return true;
        }
    }
}

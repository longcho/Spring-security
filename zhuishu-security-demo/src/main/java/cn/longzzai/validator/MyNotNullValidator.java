package cn.longzzai.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义注解的实现
 *
 * @author longcho
 * 2017-10-16-9:10
 */
public class MyNotNullValidator implements ConstraintValidator<MyNotNull, Object> {
    @Override
    public void initialize(MyNotNull myNotNull) {
        System.out.println("init my validator");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(o);
        return false;
    }
}

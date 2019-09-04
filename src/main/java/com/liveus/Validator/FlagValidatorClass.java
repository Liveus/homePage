package com.liveus.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlagValidatorClass implements ConstraintValidator<FlagValidator,Object> {

    private String values;

    //初始化values的值
    @Override
    public void initialize(FlagValidator flagValidator){
        this.values = flagValidator.values();
    }

    //实现验证
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String[] value_array = values.split(",");
        boolean isFlag = false;
        for(int i = 0;i<value_array.length;i++){
            if(value_array[i].equals(o)){
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }
}

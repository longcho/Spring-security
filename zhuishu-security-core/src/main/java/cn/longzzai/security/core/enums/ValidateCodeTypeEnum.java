package cn.longzzai.security.core.enums;

import cn.longzzai.security.core.constant.SecurityConstant;

/**
 * @author longcho
 * 2017-11-04-22:00
 */
public enum  ValidateCodeTypeEnum {
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };
    public abstract String getParamNameOnValidate();
}

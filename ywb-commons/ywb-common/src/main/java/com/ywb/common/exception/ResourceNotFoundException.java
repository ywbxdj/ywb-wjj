package com.ywb.common.exception;

import com.ywb.common.constant.IMessage;

public class ResourceNotFoundException extends SystemException {

    private static final long serialVersionUID = 5651320805529046976L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(IMessage errorCode) {
        super(errorCode);
    }

    public ResourceNotFoundException(IMessage errorCode, Object[] msgArgs) {
        super(errorCode,msgArgs);
    }

}

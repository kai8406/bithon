package com.sbss.bithon.agent.boot.aop;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/21 9:37 下午
 */
public interface IBithonObject {

    String INJECTED_FIELD_NAME = "_$BITHON_INJECTED_OBJECT$_";

    Object getInjectedObject();

    void setInjectedObject(Object value);
}
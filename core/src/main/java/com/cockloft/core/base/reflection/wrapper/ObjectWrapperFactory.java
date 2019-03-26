package com.cockloft.core.base.reflection.wrapper;

import com.cockloft.core.base.reflection.meta.MetaObject;

/**
 * @author Clinton Begin
 */
public interface ObjectWrapperFactory {

    boolean hasWrapperFor(Object object);

    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}

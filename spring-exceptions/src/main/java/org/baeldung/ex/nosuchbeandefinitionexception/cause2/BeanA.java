package org.baeldung.ex.nosuchbeandefinitionexception.cause2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class BeanA {

    @Autowired
    private IBeanB dependency;

}
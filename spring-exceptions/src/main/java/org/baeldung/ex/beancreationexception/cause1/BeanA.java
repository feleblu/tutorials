package org.baeldung.ex.beancreationexception.cause1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class BeanA {

    @Autowired
    private BeanB dependency;

}
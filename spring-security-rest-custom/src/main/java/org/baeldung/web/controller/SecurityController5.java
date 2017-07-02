package org.baeldung.web.controller;

import org.baeldung.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class SecurityController5 {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public SecurityController5() {
        super();
    }

    // API

    @RequestMapping(value = "/username5", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple() {
        final Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

}

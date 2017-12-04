package by.it.akhmelev.project.java.controller;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

class FormValidator {

    static String getString(HttpServletRequest req, String name, String pattern) throws ParseException {
        String value = req.getParameter(name);
        if (value.matches(pattern))
            return value;
        throw new ParseException("incorrect value " + value, 0);
    }

    static boolean isPost(HttpServletRequest req) {
        return req
                .getMethod()
                .toUpperCase()
                .equals("POST");
    }


}

package cn.smbms.exception;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExpcetionResolver implements HandlerExceptionResolver {
    private static final Logger logger = Logger.getLogger(ExpcetionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        logger.error(e);
        mav.setViewName("error");
        return mav;
    }
}

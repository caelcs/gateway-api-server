package uk.co.caeldev.gateway.api.features.http;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        if(OriginsHelper.isValid(origin))
            response.addHeader("Access-Control-Allow-Origin", origin);
        return true;
    }
}

package fi.tuni.koodimankelit.antibiootit.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class ApiKeyFilter implements Filter {

    private final static String HEADER_NAME = "X-API-KEY";

    private final String apiKeySecret;

    public ApiKeyFilter(String apiKeySecret) {
        this.apiKeySecret = apiKeySecret;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        String headerValue = httpRequest.getHeader(HEADER_NAME);

        if(headerValue == null || !headerValue.equals(apiKeySecret)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        chain.doFilter(request, response);
        
    }
    
}

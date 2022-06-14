package by.mazets.travelagency.controller.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String code;

    @Override
    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        code = null;
    }

}
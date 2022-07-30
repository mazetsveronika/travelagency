package by.mazets.travelagency.controller.filter;
import jakarta.servlet.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

/**
 * Class {@code EncodingFilter}
 * Filter intercepts the request and changes its encoding
 * for correct interpretation of Cyrillic characters transmitted with the request
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class EncodingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final String CODE = "encoding";
    private String code;

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.log(Level.INFO,"Encoding filter: method - init");
        code = config.getInitParameter(CODE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String codeRequest = request.getCharacterEncoding();
        if(code != null && !code.equalsIgnoreCase(codeRequest)){
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.log(Level.INFO,"Encoding filter: method - destroy");
        code = null;
    }


}
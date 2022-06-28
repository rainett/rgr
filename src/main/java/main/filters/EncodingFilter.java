package main.filters;

import javax.servlet.*;
import java.io.IOException;



public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config) {
        System.out.println("creating encoding filter");
        encoding = config.getInitParameter("encoding");
        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running encoding filter");
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
}

package org.alexr.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterServletPrinter implements Filter {

    private static Logger log = LoggerFactory.getLogger(FilterServletPrinter.class);

    @Override
    public void init(final FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        log.info(new RequestPrinter(req).getAll());

/*
        if (response.getCharacterEncoding() == null) {
            response.setCharacterEncoding("UTF-8");
        }
*/

/*
        HttpServletResponseCopier responseCopier = new HttpServletResponseCopier((HttpServletResponse) response);
        chain.doFilter(req, responseCopier);
*/

        chain.doFilter(req, response);

        log.info("AFTER="+new ResponsePrinter(response).getAll());
/*
        responseCopier.flushBuffer();
        byte[] copy = responseCopier.getCopy();
        System.out.println(new String(copy, response.getCharacterEncoding()));
*/
    }

    @Override
    public void destroy() {

    }
}

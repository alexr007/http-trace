package org.alexr.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class FilterServletPrinter implements Filter {

    private static Logger log = LoggerFactory.getLogger(FilterServletPrinter.class);

    @Override
    public void init(final FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        log.info(new RequestPrinter(req).getAll());
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}

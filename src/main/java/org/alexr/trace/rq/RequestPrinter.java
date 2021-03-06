package org.alexr.trace.rq;

import org.alexr.colored.Ansi;
import org.alexr.colored.Attribute;
import org.alexr.colored.Colored;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;

public class RequestPrinter {
    private final HttpServletRequest req;

    public RequestPrinter(final ServletRequest req) {
        this((HttpServletRequest) req);
    }

    public RequestPrinter(final HttpServletRequest req) {
        this.req = req;
    }

    public String getRequestType() {
        StringBuilder sb = new StringBuilder("Request");
        String query = req.getQueryString() == null ? "" : req.getQueryString();
        sb.append("Request type:").append(Colored.build(req.getMethod(),Attribute.BLUE.bold())).append("\n");
        sb.append("URL:").append(Colored.build(req.getRequestURL(), Attribute.BLUE.bold())).append("\n");
        sb.append("Query:").append(Colored.build(query, Attribute.BLUE.bold())).append("\n");
        return sb.toString();
    }

    public String getAllHeaders() {
        StringBuilder sb = new StringBuilder(Colored.build("ALL HEADERS:\n", Attribute.GREEN.bold()));
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            sb
                    .append(name)
                    .append(": ")
                    .append(Colored.build(req.getHeader(name), Ansi.ColorFont.GREEN))
                    .append("\n");
        }
        return sb.toString();
    }

    public String getAllParams() {
        StringBuilder sb = new StringBuilder(Colored.build("ALL PARAMS:\n", Ansi.Style.BOLD, Ansi.ColorFont.RED));
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            sb
                    .append(name)
                    .append("=")
                    .append(Colored.build(req.getParameter(name), Ansi.ColorFont.RED))
                    .append("\n");
        }
        return sb.toString();
    }

    public String getAllBody() throws IOException {
        ServletInputStream is = req.getInputStream();
        StringBuilder sb = new StringBuilder(Colored.build("BODY:\n",  Ansi.Style.BOLD, Ansi.ColorFont.YELLOW));
        try (Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");) {
            while (s.hasNext()) {
                sb.append(s.nextLine())
                        .append("\n");
            }
        }
        return sb.toString();
    }

    public String getAll() throws IOException {
        return String.join("", getRequestType(), getAllHeaders(), getAllParams()/*, getAllBody()*/);
    }

    public void printAll() throws IOException {
        System.out.println(getAll());
    }

    public void printAll(Logger log) throws IOException {
        printAll(log, Level.INFO);
    }

    public void printAll(Logger log, Level lvl) throws IOException {
        String data = getAll();
        switch (lvl) {
            case ERROR: log.error(data);
                break;
            case WARN: log.warn(data);
                break;
            case INFO: log.info(data);
                break;
            case DEBUG: log.debug(data);
                break;
            case TRACE: log.trace(data);
                break;
        }
    }
}

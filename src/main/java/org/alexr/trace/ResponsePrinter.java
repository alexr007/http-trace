package org.alexr.trace;

import org.alexr.colored.Ansi;
import org.alexr.colored.Attribute;
import org.alexr.colored.Colored;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.function.Consumer;

public class ResponsePrinter {
    private final HttpServletResponse resp;

    public ResponsePrinter(final ServletResponse req) {
        this((HttpServletResponse) req);
    }

    public ResponsePrinter(final HttpServletResponse resp) {
        this.resp = resp;
    }

    public String getAll() throws IOException {
        return "";
    }

}

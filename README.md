# http-trace
how to use:

add to `pom.xml`

`        <dependency>
            <groupId>org.alexr</groupId>
            <artifactId>http-trace</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        `
and 
`server.addFilter(FilterServletPrinter.class, "/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));`


package com.hss.myTag;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
public class MyAutoDialect extends AbstractProcessorDialect implements IProcessorDialect {

    private static final String PREFIX = "mt";

    public MyAutoDialect() {
        super("My tag", PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new MyTagProcessor(PREFIX));
        return processors;
    }
}

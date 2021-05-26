package com.merchant.demo.log;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) @Target(ElementType.METHOD)
public @interface SrcAfterLoggable {
	public InOutDivision direction() default InOutDivision.OUTBOUND;
}

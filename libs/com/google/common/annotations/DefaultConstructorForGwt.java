package com.google.common.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.CONSTRUCTOR})
@GoogleInternal
@GwtCompatible
public @interface DefaultConstructorForGwt
{
}

/* Location:           /home/phil/workspace/labAssist/libs/GlassVoice-dex2jar.jar
 * Qualified Name:     com.google.common.annotations.DefaultConstructorForGwt
 * JD-Core Version:    0.6.2
 */
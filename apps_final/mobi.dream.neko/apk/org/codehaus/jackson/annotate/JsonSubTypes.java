package org.codehaus.jackson.annotate;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER})
@JacksonAnnotation
public @interface JsonSubTypes
{
  Type[] value();
  
  public static @interface Type
  {
    String name() default "";
    
    Class<?> value();
  }
}

/* Location:
 * Qualified Name:     org.codehaus.jackson.annotate.JsonSubTypes
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
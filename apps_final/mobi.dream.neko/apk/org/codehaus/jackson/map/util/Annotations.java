package org.codehaus.jackson.map.util;

import java.lang.annotation.Annotation;

public abstract interface Annotations
{
  public abstract <A extends Annotation> A get(Class<A> paramClass);
  
  public abstract int size();
}

/* Location:
 * Qualified Name:     org.codehaus.jackson.map.util.Annotations
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
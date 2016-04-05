package org.codehaus.jackson.map;

import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.util.Named;

public abstract class BeanPropertyDefinition
  implements Named
{
  public boolean couldDeserialize()
  {
    return getMutator() != null;
  }
  
  public boolean couldSerialize()
  {
    return getAccessor() != null;
  }
  
  public abstract AnnotatedMember getAccessor();
  
  public abstract AnnotatedParameter getConstructorParameter();
  
  public abstract AnnotatedField getField();
  
  public abstract AnnotatedMethod getGetter();
  
  public abstract String getInternalName();
  
  public abstract AnnotatedMember getMutator();
  
  public abstract String getName();
  
  public abstract AnnotatedMethod getSetter();
  
  public abstract boolean hasConstructorParameter();
  
  public abstract boolean hasField();
  
  public abstract boolean hasGetter();
  
  public abstract boolean hasSetter();
  
  public abstract boolean isExplicitlyIncluded();
}

/* Location:
 * Qualified Name:     org.codehaus.jackson.map.BeanPropertyDefinition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
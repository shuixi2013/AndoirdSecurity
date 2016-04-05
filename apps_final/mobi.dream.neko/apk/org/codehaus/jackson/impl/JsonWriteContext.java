package org.codehaus.jackson.impl;

import org.codehaus.jackson.JsonStreamContext;

public class JsonWriteContext
  extends JsonStreamContext
{
  public static final int STATUS_EXPECT_NAME = 5;
  public static final int STATUS_EXPECT_VALUE = 4;
  public static final int STATUS_OK_AFTER_COLON = 2;
  public static final int STATUS_OK_AFTER_COMMA = 1;
  public static final int STATUS_OK_AFTER_SPACE = 3;
  public static final int STATUS_OK_AS_IS = 0;
  protected JsonWriteContext _child = null;
  protected String _currentName;
  protected final JsonWriteContext _parent;
  
  protected JsonWriteContext(int paramInt, JsonWriteContext paramJsonWriteContext)
  {
    _type = paramInt;
    _parent = paramJsonWriteContext;
    _index = -1;
  }
  
  public static JsonWriteContext createRootContext()
  {
    return new JsonWriteContext(0, null);
  }
  
  private final JsonWriteContext reset(int paramInt)
  {
    _type = paramInt;
    _index = -1;
    _currentName = null;
    return this;
  }
  
  protected final void appendDesc(StringBuilder paramStringBuilder)
  {
    if (_type == 2)
    {
      paramStringBuilder.append('{');
      if (_currentName != null)
      {
        paramStringBuilder.append('"');
        paramStringBuilder.append(_currentName);
        paramStringBuilder.append('"');
      }
      for (;;)
      {
        paramStringBuilder.append('}');
        return;
        paramStringBuilder.append('?');
      }
    }
    if (_type == 1)
    {
      paramStringBuilder.append('[');
      paramStringBuilder.append(getCurrentIndex());
      paramStringBuilder.append(']');
      return;
    }
    paramStringBuilder.append("/");
  }
  
  public final JsonWriteContext createChildArrayContext()
  {
    JsonWriteContext localJsonWriteContext = _child;
    if (localJsonWriteContext == null)
    {
      localJsonWriteContext = new JsonWriteContext(1, this);
      _child = localJsonWriteContext;
      return localJsonWriteContext;
    }
    return localJsonWriteContext.reset(1);
  }
  
  public final JsonWriteContext createChildObjectContext()
  {
    JsonWriteContext localJsonWriteContext = _child;
    if (localJsonWriteContext == null)
    {
      localJsonWriteContext = new JsonWriteContext(2, this);
      _child = localJsonWriteContext;
      return localJsonWriteContext;
    }
    return localJsonWriteContext.reset(2);
  }
  
  public final String getCurrentName()
  {
    return _currentName;
  }
  
  public final JsonWriteContext getParent()
  {
    return _parent;
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendDesc(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public final int writeFieldName(String paramString)
  {
    if ((_type != 2) || (_currentName != null)) {
      return 4;
    }
    _currentName = paramString;
    if (_index < 0) {
      return 0;
    }
    return 1;
  }
  
  public final int writeValue()
  {
    int i = 0;
    if (_type == 2) {
      if (_currentName == null) {
        i = 5;
      }
    }
    do
    {
      int j;
      do
      {
        return i;
        _currentName = null;
        _index += 1;
        return 2;
        if (_type != 1) {
          break;
        }
        j = _index;
        _index += 1;
      } while (j < 0);
      return 1;
      _index += 1;
    } while (_index == 0);
    return 3;
  }
}

/* Location:
 * Qualified Name:     org.codehaus.jackson.impl.JsonWriteContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
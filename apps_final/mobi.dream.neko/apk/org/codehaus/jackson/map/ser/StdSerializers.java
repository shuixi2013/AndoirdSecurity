package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.std.CalendarSerializer;
import org.codehaus.jackson.map.ser.std.DateSerializer;
import org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;
import org.codehaus.jackson.map.ser.std.ScalarSerializerBase;
import org.codehaus.jackson.map.ser.std.SerializableSerializer;
import org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer;

public class StdSerializers
{
  @JacksonStdImpl
  public static final class BooleanSerializer
    extends NonTypedScalarSerializerBase<Boolean>
  {
    final boolean _forPrimitive;
    
    public BooleanSerializer(boolean paramBoolean)
    {
      super();
      _forPrimitive = paramBoolean;
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      if (!_forPrimitive) {}
      for (boolean bool = true;; bool = false) {
        return createSchemaNode("boolean", bool);
      }
    }
    
    public void serialize(Boolean paramBoolean, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeBoolean(paramBoolean.booleanValue());
    }
  }
  
  @Deprecated
  @JacksonStdImpl
  public static final class CalendarSerializer
    extends CalendarSerializer
  {}
  
  @JacksonStdImpl
  public static final class DoubleSerializer
    extends NonTypedScalarSerializerBase<Double>
  {
    static final DoubleSerializer instance = new DoubleSerializer();
    
    public DoubleSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Double paramDouble, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramDouble.doubleValue());
    }
  }
  
  @JacksonStdImpl
  public static final class FloatSerializer
    extends ScalarSerializerBase<Float>
  {
    static final FloatSerializer instance = new FloatSerializer();
    
    public FloatSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Float paramFloat, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramFloat.floatValue());
    }
  }
  
  @JacksonStdImpl
  public static final class IntLikeSerializer
    extends ScalarSerializerBase<Number>
  {
    static final IntLikeSerializer instance = new IntLikeSerializer();
    
    public IntLikeSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("integer", true);
    }
    
    public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramNumber.intValue());
    }
  }
  
  @JacksonStdImpl
  public static final class IntegerSerializer
    extends NonTypedScalarSerializerBase<Integer>
  {
    public IntegerSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("integer", true);
    }
    
    public void serialize(Integer paramInteger, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramInteger.intValue());
    }
  }
  
  @JacksonStdImpl
  public static final class LongSerializer
    extends ScalarSerializerBase<Long>
  {
    static final LongSerializer instance = new LongSerializer();
    
    public LongSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Long paramLong, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramLong.longValue());
    }
  }
  
  @JacksonStdImpl
  public static final class NumberSerializer
    extends ScalarSerializerBase<Number>
  {
    public static final NumberSerializer instance = new NumberSerializer();
    
    public NumberSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      if ((paramNumber instanceof BigDecimal))
      {
        paramJsonGenerator.writeNumber((BigDecimal)paramNumber);
        return;
      }
      if ((paramNumber instanceof BigInteger))
      {
        paramJsonGenerator.writeNumber((BigInteger)paramNumber);
        return;
      }
      if ((paramNumber instanceof Integer))
      {
        paramJsonGenerator.writeNumber(paramNumber.intValue());
        return;
      }
      if ((paramNumber instanceof Long))
      {
        paramJsonGenerator.writeNumber(paramNumber.longValue());
        return;
      }
      if ((paramNumber instanceof Double))
      {
        paramJsonGenerator.writeNumber(paramNumber.doubleValue());
        return;
      }
      if ((paramNumber instanceof Float))
      {
        paramJsonGenerator.writeNumber(paramNumber.floatValue());
        return;
      }
      if (((paramNumber instanceof Byte)) || ((paramNumber instanceof Short)))
      {
        paramJsonGenerator.writeNumber(paramNumber.intValue());
        return;
      }
      paramJsonGenerator.writeNumber(paramNumber.toString());
    }
  }
  
  @Deprecated
  @JacksonStdImpl
  public static final class SerializableSerializer
    extends SerializableSerializer
  {}
  
  @Deprecated
  @JacksonStdImpl
  public static final class SerializableWithTypeSerializer
    extends SerializableWithTypeSerializer
  {}
  
  @JacksonStdImpl
  public static final class SqlDateSerializer
    extends ScalarSerializerBase<Date>
  {
    public SqlDateSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("string", true);
    }
    
    public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeString(paramDate.toString());
    }
  }
  
  @JacksonStdImpl
  public static final class SqlTimeSerializer
    extends ScalarSerializerBase<Time>
  {
    public SqlTimeSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("string", true);
    }
    
    public void serialize(Time paramTime, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeString(paramTime.toString());
    }
  }
  
  @Deprecated
  @JacksonStdImpl
  public static final class StringSerializer
    extends NonTypedScalarSerializerBase<String>
  {
    public StringSerializer()
    {
      super();
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("string", true);
    }
    
    public void serialize(String paramString, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeString(paramString);
    }
  }
  
  @Deprecated
  @JacksonStdImpl
  public static final class UtilDateSerializer
    extends DateSerializer
  {}
}

/* Location:
 * Qualified Name:     org.codehaus.jackson.map.ser.StdSerializers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanPropertyDefinition;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

public class BasicBeanDescription
  extends BeanDescription
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected AnnotatedMethod _anyGetterMethod;
  protected AnnotatedMethod _anySetterMethod;
  protected TypeBindings _bindings;
  protected final AnnotatedClass _classInfo;
  protected final MapperConfig<?> _config;
  protected Set<String> _ignoredPropertyNames;
  protected Set<String> _ignoredPropertyNamesForDeser;
  protected Map<Object, AnnotatedMember> _injectables;
  protected AnnotatedMethod _jsonValueMethod;
  protected final List<BeanPropertyDefinition> _properties;
  
  @Deprecated
  public BasicBeanDescription(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass)
  {
    this(paramMapperConfig, paramJavaType, paramAnnotatedClass, Collections.emptyList());
  }
  
  protected BasicBeanDescription(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass, List<BeanPropertyDefinition> paramList)
  {
    super(paramJavaType);
    _config = paramMapperConfig;
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector())
    {
      _annotationIntrospector = paramMapperConfig;
      _classInfo = paramAnnotatedClass;
      _properties = paramList;
      return;
    }
  }
  
  public static BasicBeanDescription forDeserialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    BasicBeanDescription localBasicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector.getConfig(), paramPOJOPropertiesCollector.getType(), paramPOJOPropertiesCollector.getClassDef(), paramPOJOPropertiesCollector.getProperties());
    _anySetterMethod = paramPOJOPropertiesCollector.getAnySetterMethod();
    _ignoredPropertyNames = paramPOJOPropertiesCollector.getIgnoredPropertyNames();
    _ignoredPropertyNamesForDeser = paramPOJOPropertiesCollector.getIgnoredPropertyNamesForDeser();
    _injectables = paramPOJOPropertiesCollector.getInjectables();
    return localBasicBeanDescription;
  }
  
  public static BasicBeanDescription forOtherUse(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass)
  {
    return new BasicBeanDescription(paramMapperConfig, paramJavaType, paramAnnotatedClass, Collections.emptyList());
  }
  
  public static BasicBeanDescription forSerialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    BasicBeanDescription localBasicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector.getConfig(), paramPOJOPropertiesCollector.getType(), paramPOJOPropertiesCollector.getClassDef(), paramPOJOPropertiesCollector.getProperties());
    _jsonValueMethod = paramPOJOPropertiesCollector.getJsonValueMethod();
    _anyGetterMethod = paramPOJOPropertiesCollector.getAnyGetterMethod();
    return localBasicBeanDescription;
  }
  
  public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> paramCollection, boolean paramBoolean)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Iterator localIterator = _properties.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (BeanPropertyDefinition)localIterator.next();
      AnnotatedField localAnnotatedField = ((BeanPropertyDefinition)localObject).getField();
      if (localAnnotatedField != null)
      {
        localObject = ((BeanPropertyDefinition)localObject).getName();
        if ((paramCollection == null) || (!paramCollection.contains(localObject))) {
          localLinkedHashMap.put(localObject, localAnnotatedField);
        }
      }
    }
    return localLinkedHashMap;
  }
  
  public TypeBindings bindingsForBeanType()
  {
    if (_bindings == null) {
      _bindings = new TypeBindings(_config.getTypeFactory(), _type);
    }
    return _bindings;
  }
  
  public AnnotatedMethod findAnyGetter()
    throws IllegalArgumentException
  {
    if ((_anyGetterMethod != null) && (!Map.class.isAssignableFrom(_anyGetterMethod.getRawType()))) {
      throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + _anyGetterMethod.getName() + "(): return type is not instance of java.util.Map");
    }
    return _anyGetterMethod;
  }
  
  public AnnotatedMethod findAnySetter()
    throws IllegalArgumentException
  {
    if (_anySetterMethod != null)
    {
      Class localClass = _anySetterMethod.getParameterClass(0);
      if ((localClass != String.class) && (localClass != Object.class)) {
        throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + _anySetterMethod.getName() + "(): first argument not of type String or Object, but " + localClass.getName());
      }
    }
    return _anySetterMethod;
  }
  
  public Map<String, AnnotatedMember> findBackReferenceProperties()
  {
    Object localObject1 = null;
    Iterator localIterator = _properties.iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMember localAnnotatedMember = ((BeanPropertyDefinition)localIterator.next()).getMutator();
      if (localAnnotatedMember != null)
      {
        Object localObject3 = _annotationIntrospector.findReferenceType(localAnnotatedMember);
        if ((localObject3 != null) && (((AnnotationIntrospector.ReferenceProperty)localObject3).isBackReference()))
        {
          Object localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new HashMap();
          }
          localObject3 = ((AnnotationIntrospector.ReferenceProperty)localObject3).getName();
          localObject1 = localObject2;
          if (((HashMap)localObject2).put(localObject3, localAnnotatedMember) != null) {
            throw new IllegalArgumentException("Multiple back-reference properties with name '" + (String)localObject3 + "'");
          }
        }
      }
    }
    return (Map<String, AnnotatedMember>)localObject1;
  }
  
  public List<String> findCreatorPropertyNames()
  {
    Object localObject1 = null;
    int i = 0;
    while (i < 2)
    {
      if (i == 0) {}
      for (localObject2 = getConstructors();; localObject2 = getFactoryMethods())
      {
        Iterator localIterator = ((List)localObject2).iterator();
        AnnotatedWithParams localAnnotatedWithParams;
        int k;
        String str;
        do
        {
          do
          {
            if (!localIterator.hasNext()) {
              break;
            }
            localAnnotatedWithParams = (AnnotatedWithParams)localIterator.next();
            k = localAnnotatedWithParams.getParameterCount();
          } while (k < 1);
          str = _annotationIntrospector.findPropertyNameForParam(localAnnotatedWithParams.getParameter(0));
        } while (str == null);
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).add(str);
        int j = 1;
        for (;;)
        {
          localObject1 = localObject2;
          if (j >= k) {
            break;
          }
          ((List)localObject2).add(_annotationIntrospector.findPropertyNameForParam(localAnnotatedWithParams.getParameter(j)));
          j += 1;
        }
      }
      i += 1;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = Collections.emptyList();
    }
    return (List<String>)localObject2;
  }
  
  public AnnotatedConstructor findDefaultConstructor()
  {
    return _classInfo.getDefaultConstructor();
  }
  
  public LinkedHashMap<String, AnnotatedField> findDeserializableFields(VisibilityChecker<?> paramVisibilityChecker, Collection<String> paramCollection)
  {
    return _findPropertyFields(paramCollection, false);
  }
  
  public Method findFactoryMethod(Class<?>... paramVarArgs)
  {
    Iterator localIterator = _classInfo.getStaticMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      if (isFactoryMethod(localAnnotatedMethod))
      {
        Class localClass = localAnnotatedMethod.getParameterClass(0);
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
          if (localClass.isAssignableFrom(paramVarArgs[i])) {
            return localAnnotatedMethod.getAnnotated();
          }
          i += 1;
        }
      }
    }
    return null;
  }
  
  public LinkedHashMap<String, AnnotatedMethod> findGetters(VisibilityChecker<?> paramVisibilityChecker, Collection<String> paramCollection)
  {
    paramVisibilityChecker = new LinkedHashMap();
    Iterator localIterator = _properties.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (BeanPropertyDefinition)localIterator.next();
      AnnotatedMethod localAnnotatedMethod = ((BeanPropertyDefinition)localObject).getGetter();
      if (localAnnotatedMethod != null)
      {
        localObject = ((BeanPropertyDefinition)localObject).getName();
        if ((paramCollection == null) || (!paramCollection.contains(localObject))) {
          paramVisibilityChecker.put(localObject, localAnnotatedMethod);
        }
      }
    }
    return paramVisibilityChecker;
  }
  
  public Map<Object, AnnotatedMember> findInjectables()
  {
    return _injectables;
  }
  
  public AnnotatedMethod findJsonValueMethod()
  {
    return _jsonValueMethod;
  }
  
  public AnnotatedMethod findMethod(String paramString, Class<?>[] paramArrayOfClass)
  {
    return _classInfo.findMethod(paramString, paramArrayOfClass);
  }
  
  public List<BeanPropertyDefinition> findProperties()
  {
    return _properties;
  }
  
  public LinkedHashMap<String, AnnotatedField> findSerializableFields(VisibilityChecker<?> paramVisibilityChecker, Collection<String> paramCollection)
  {
    return _findPropertyFields(paramCollection, true);
  }
  
  public JsonSerialize.Inclusion findSerializationInclusion(JsonSerialize.Inclusion paramInclusion)
  {
    if (_annotationIntrospector == null) {
      return paramInclusion;
    }
    return _annotationIntrospector.findSerializationInclusion(_classInfo, paramInclusion);
  }
  
  public LinkedHashMap<String, AnnotatedMethod> findSetters(VisibilityChecker<?> paramVisibilityChecker)
  {
    paramVisibilityChecker = new LinkedHashMap();
    Iterator localIterator = _properties.iterator();
    while (localIterator.hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator.next();
      AnnotatedMethod localAnnotatedMethod = localBeanPropertyDefinition.getSetter();
      if (localAnnotatedMethod != null) {
        paramVisibilityChecker.put(localBeanPropertyDefinition.getName(), localAnnotatedMethod);
      }
    }
    return paramVisibilityChecker;
  }
  
  public Constructor<?> findSingleArgConstructor(Class<?>... paramVarArgs)
  {
    Iterator localIterator = _classInfo.getConstructors().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedConstructor localAnnotatedConstructor = (AnnotatedConstructor)localIterator.next();
      if (localAnnotatedConstructor.getParameterCount() == 1)
      {
        Class localClass = localAnnotatedConstructor.getParameterClass(0);
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
          if (paramVarArgs[i] == localClass) {
            return localAnnotatedConstructor.getAnnotated();
          }
          i += 1;
        }
      }
    }
    return null;
  }
  
  public Annotations getClassAnnotations()
  {
    return _classInfo.getAnnotations();
  }
  
  public AnnotatedClass getClassInfo()
  {
    return _classInfo;
  }
  
  public List<AnnotatedConstructor> getConstructors()
  {
    return _classInfo.getConstructors();
  }
  
  public List<AnnotatedMethod> getFactoryMethods()
  {
    Object localObject = _classInfo.getStaticMethods();
    if (((List)localObject).isEmpty()) {
      return (List<AnnotatedMethod>)localObject;
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)((Iterator)localObject).next();
      if (isFactoryMethod(localAnnotatedMethod)) {
        localArrayList.add(localAnnotatedMethod);
      }
    }
    return localArrayList;
  }
  
  public Set<String> getIgnoredPropertyNames()
  {
    if (_ignoredPropertyNames == null) {
      return Collections.emptySet();
    }
    return _ignoredPropertyNames;
  }
  
  public Set<String> getIgnoredPropertyNamesForDeser()
  {
    return _ignoredPropertyNamesForDeser;
  }
  
  public boolean hasKnownClassAnnotations()
  {
    return _classInfo.hasAnnotations();
  }
  
  public Object instantiateBean(boolean paramBoolean)
  {
    Object localObject = _classInfo.getDefaultConstructor();
    if (localObject == null) {
      return null;
    }
    if (paramBoolean) {
      ((AnnotatedConstructor)localObject).fixAccess();
    }
    try
    {
      localObject = ((AnnotatedConstructor)localObject).getAnnotated().newInstance(new Object[0]);
      return localObject;
    }
    catch (Exception localException)
    {
      Throwable localThrowable;
      while (localException.getCause() != null) {
        localThrowable = localException.getCause();
      }
      if ((localThrowable instanceof Error)) {
        throw ((Error)localThrowable);
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      throw new IllegalArgumentException("Failed to instantiate bean of type " + _classInfo.getAnnotated().getName() + ": (" + localThrowable.getClass().getName() + ") " + localThrowable.getMessage(), localThrowable);
    }
  }
  
  protected boolean isFactoryMethod(AnnotatedMethod paramAnnotatedMethod)
  {
    Class localClass = paramAnnotatedMethod.getRawType();
    if (!getBeanClass().isAssignableFrom(localClass)) {}
    do
    {
      return false;
      if (_annotationIntrospector.hasCreatorAnnotation(paramAnnotatedMethod)) {
        return true;
      }
    } while (!"valueOf".equals(paramAnnotatedMethod.getName()));
    return true;
  }
  
  public JavaType resolveType(Type paramType)
  {
    if (paramType == null) {
      return null;
    }
    return bindingsForBeanType().resolveType(paramType);
  }
}

/* Location:
 * Qualified Name:     org.codehaus.jackson.map.introspect.BasicBeanDescription
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
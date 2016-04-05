package com.google.zxing.common;

public final class PerspectiveTransform
{
  private final float a11;
  private final float a12;
  private final float a13;
  private final float a21;
  private final float a22;
  private final float a23;
  private final float a31;
  private final float a32;
  private final float a33;
  
  private PerspectiveTransform(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9)
  {
    a11 = paramFloat1;
    a12 = paramFloat4;
    a13 = paramFloat7;
    a21 = paramFloat2;
    a22 = paramFloat5;
    a23 = paramFloat8;
    a31 = paramFloat3;
    a32 = paramFloat6;
    a33 = paramFloat9;
  }
  
  public static PerspectiveTransform quadrilateralToQuadrilateral(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16)
  {
    PerspectiveTransform localPerspectiveTransform = quadrilateralToSquare(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8);
    return squareToQuadrilateral(paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14, paramFloat15, paramFloat16).times(localPerspectiveTransform);
  }
  
  public static PerspectiveTransform quadrilateralToSquare(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8)
  {
    return squareToQuadrilateral(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8).buildAdjoint();
  }
  
  public static PerspectiveTransform squareToQuadrilateral(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8)
  {
    float f1 = paramFloat1 - paramFloat3 + paramFloat5 - paramFloat7;
    float f2 = paramFloat2 - paramFloat4 + paramFloat6 - paramFloat8;
    if ((f1 == 0.0F) && (f2 == 0.0F)) {
      return new PerspectiveTransform(paramFloat3 - paramFloat1, paramFloat5 - paramFloat3, paramFloat1, paramFloat4 - paramFloat2, paramFloat6 - paramFloat4, paramFloat2, 0.0F, 0.0F, 1.0F);
    }
    float f3 = paramFloat3 - paramFloat5;
    float f4 = paramFloat7 - paramFloat5;
    paramFloat5 = paramFloat4 - paramFloat6;
    float f5 = paramFloat8 - paramFloat6;
    paramFloat6 = f3 * f5 - f4 * paramFloat5;
    f4 = (f1 * f5 - f4 * f2) / paramFloat6;
    paramFloat5 = (f3 * f2 - f1 * paramFloat5) / paramFloat6;
    return new PerspectiveTransform(paramFloat3 - paramFloat1 + f4 * paramFloat3, paramFloat7 - paramFloat1 + paramFloat5 * paramFloat7, paramFloat1, f4 * paramFloat4 + (paramFloat4 - paramFloat2), paramFloat5 * paramFloat8 + (paramFloat8 - paramFloat2), paramFloat2, f4, paramFloat5, 1.0F);
  }
  
  PerspectiveTransform buildAdjoint()
  {
    return new PerspectiveTransform(a22 * a33 - a23 * a32, a23 * a31 - a21 * a33, a21 * a32 - a22 * a31, a13 * a32 - a12 * a33, a11 * a33 - a13 * a31, a12 * a31 - a11 * a32, a12 * a23 - a13 * a22, a13 * a21 - a11 * a23, a11 * a22 - a12 * a21);
  }
  
  PerspectiveTransform times(PerspectiveTransform paramPerspectiveTransform)
  {
    return new PerspectiveTransform(a11 * a11 + a21 * a12 + a31 * a13, a11 * a21 + a21 * a22 + a31 * a23, a11 * a31 + a21 * a32 + a31 * a33, a12 * a11 + a22 * a12 + a32 * a13, a12 * a21 + a22 * a22 + a32 * a23, a12 * a31 + a22 * a32 + a32 * a33, a13 * a11 + a23 * a12 + a33 * a13, a13 * a21 + a23 * a22 + a33 * a23, a13 * a31 + a23 * a32 + a33 * a33);
  }
  
  public void transformPoints(float[] paramArrayOfFloat)
  {
    int j = paramArrayOfFloat.length;
    float f1 = a11;
    float f2 = a12;
    float f3 = a13;
    float f4 = a21;
    float f5 = a22;
    float f6 = a23;
    float f7 = a31;
    float f8 = a32;
    float f9 = a33;
    int i = 0;
    while (i < j)
    {
      float f10 = paramArrayOfFloat[i];
      float f11 = paramArrayOfFloat[(i + 1)];
      float f12 = f3 * f10 + f6 * f11 + f9;
      paramArrayOfFloat[i] = ((f1 * f10 + f4 * f11 + f7) / f12);
      paramArrayOfFloat[(i + 1)] = ((f2 * f10 + f5 * f11 + f8) / f12);
      i += 2;
    }
  }
  
  public void transformPoints(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    int j = paramArrayOfFloat1.length;
    int i = 0;
    while (i < j)
    {
      float f1 = paramArrayOfFloat1[i];
      float f2 = paramArrayOfFloat2[i];
      float f3 = a13 * f1 + a23 * f2 + a33;
      paramArrayOfFloat1[i] = ((a11 * f1 + a21 * f2 + a31) / f3);
      paramArrayOfFloat2[i] = ((a12 * f1 + a22 * f2 + a32) / f3);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.zxing.common.PerspectiveTransform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class m
  implements Parcelable.Creator<l>
{
  static void a(l paraml, Parcel paramParcel, int paramInt)
  {
    paramInt = b.C(paramParcel);
    b.c(paramParcel, 1, paraml.getVersionCode());
    b.a(paramParcel, 2, akX);
    b.a(paramParcel, 3, akY);
    b.G(paramParcel, paramInt);
  }
  
  public l cp(Parcel paramParcel)
  {
    long l1 = 0L;
    int j = a.B(paramParcel);
    int i = 0;
    long l2 = 0L;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.A(paramParcel);
      switch (a.ar(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        l2 = a.i(paramParcel, k);
        break;
      case 3: 
        l1 = a.i(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new l(i, l2, l1);
  }
  
  public l[] dX(int paramInt)
  {
    return new l[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.wallet.wobs.m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;

class e$a$a
  implements e
{
  private IBinder ko;
  
  e$a$a(IBinder paramIBinder)
  {
    ko = paramIBinder;
  }
  
  /* Error */
  public boolean a(e parame)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   6: astore_2
    //   7: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   10: astore_3
    //   11: aload_2
    //   12: ldc 26
    //   14: invokevirtual 30	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   17: aload_1
    //   18: ifnull +58 -> 76
    //   21: aload_1
    //   22: invokeinterface 34 1 0
    //   27: astore_1
    //   28: aload_2
    //   29: aload_1
    //   30: invokevirtual 37	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
    //   33: aload_0
    //   34: getfield 15	com/google/android/gms/maps/model/internal/e$a$a:ko	Landroid/os/IBinder;
    //   37: iconst_4
    //   38: aload_2
    //   39: aload_3
    //   40: iconst_0
    //   41: invokeinterface 43 5 0
    //   46: pop
    //   47: aload_3
    //   48: invokevirtual 46	android/os/Parcel:readException	()V
    //   51: aload_3
    //   52: invokevirtual 50	android/os/Parcel:readInt	()I
    //   55: istore 4
    //   57: iload 4
    //   59: ifeq +6 -> 65
    //   62: iconst_1
    //   63: istore 5
    //   65: aload_3
    //   66: invokevirtual 53	android/os/Parcel:recycle	()V
    //   69: aload_2
    //   70: invokevirtual 53	android/os/Parcel:recycle	()V
    //   73: iload 5
    //   75: ireturn
    //   76: aconst_null
    //   77: astore_1
    //   78: goto -50 -> 28
    //   81: astore_1
    //   82: aload_3
    //   83: invokevirtual 53	android/os/Parcel:recycle	()V
    //   86: aload_2
    //   87: invokevirtual 53	android/os/Parcel:recycle	()V
    //   90: aload_1
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	a
    //   0	92	1	parame	e
    //   6	81	2	localParcel1	Parcel
    //   10	73	3	localParcel2	Parcel
    //   55	3	4	i	int
    //   1	73	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   11	17	81	finally
    //   21	28	81	finally
    //   28	57	81	finally
  }
  
  public void activate()
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
      ko.transact(3, localParcel1, localParcel2, 0);
      localParcel2.readException();
      return;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
  
  public IBinder asBinder()
  {
    return ko;
  }
  
  public String getName()
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
      ko.transact(1, localParcel1, localParcel2, 0);
      localParcel2.readException();
      String str = localParcel2.readString();
      return str;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
  
  public String getShortName()
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
      ko.transact(2, localParcel1, localParcel2, 0);
      localParcel2.readException();
      String str = localParcel2.readString();
      return str;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
  
  public int hashCodeRemote()
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
      ko.transact(5, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      return i;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.model.internal.e.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
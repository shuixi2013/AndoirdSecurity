package com.google.android.gms.plus.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.d;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hc.b;

final class e$h
  extends hc<d>.b<a.d<Status>>
{
  private final Status yw;
  
  public e$h(a.d<Status> paramd, Status paramStatus)
  {
    super(paramd, paramStatus);
    Status localStatus;
    yw = localStatus;
  }
  
  protected final void fp() {}
  
  protected final void n(a.d<Status> paramd)
  {
    abJ.disconnect();
    if (paramd != null) {
      paramd.a(yw);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.plus.internal.e.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
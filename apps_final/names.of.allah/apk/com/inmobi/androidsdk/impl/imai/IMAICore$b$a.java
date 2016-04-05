package com.inmobi.androidsdk.impl.imai;

import com.inmobi.androidsdk.impl.net.HttpRequestCallback;
import com.inmobi.commons.internal.Log;

class IMAICore$b$a
  implements HttpRequestCallback
{
  IMAICore$b$a(IMAICore.b paramb) {}
  
  public void notifyResult(int paramInt, Object paramObject)
  {
    try
    {
      Log.internal("[InMobi]-[Network]-4.4.1", "Got PING IN WEBVIEW callback. Status: " + paramInt);
      if (paramInt == 0)
      {
        IMAICore.firePingInWebViewSuccessful(a.c, a.a);
        return;
      }
      IMAICore.fireErrorEvent(a.c, "IMAI Ping in webview failed", "pingInWebview", a.a);
      return;
    }
    catch (Exception paramObject)
    {
      Log.internal("[InMobi]-[Network]-4.4.1", "Exception", (Throwable)paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.inmobi.androidsdk.impl.imai.IMAICore.b.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.appyet.b.a;

import android.sax.EndTextElementListener;
import com.appyet.data.FeedItem;

final class o
  implements EndTextElementListener
{
  o(a parama, FeedItem paramFeedItem) {}
  
  public final void end(String paramString)
  {
    try
    {
      a.setTitle(paramString);
      return;
    }
    catch (Exception paramString) {}
  }
}

/* Location:
 * Qualified Name:     com.appyet.b.a.o
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
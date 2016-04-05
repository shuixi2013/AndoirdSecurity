package com.google.android.gms.tagmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class aw
  implements bl
{
  private HttpURLConnection afM;
  
  private InputStream a(HttpURLConnection paramHttpURLConnection)
  {
    int i = paramHttpURLConnection.getResponseCode();
    if (i == 200) {
      return paramHttpURLConnection.getInputStream();
    }
    paramHttpURLConnection = "Bad response: " + i;
    if (i == 404) {
      throw new FileNotFoundException(paramHttpURLConnection);
    }
    throw new IOException(paramHttpURLConnection);
  }
  
  private void b(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection != null) {
      paramHttpURLConnection.disconnect();
    }
  }
  
  public InputStream bV(String paramString)
  {
    afM = bW(paramString);
    return a(afM);
  }
  
  HttpURLConnection bW(String paramString)
  {
    paramString = (HttpURLConnection)new URL(paramString).openConnection();
    paramString.setReadTimeout(20000);
    paramString.setConnectTimeout(20000);
    return paramString;
  }
  
  public void close()
  {
    b(afM);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.aw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
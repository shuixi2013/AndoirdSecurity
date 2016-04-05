package com.google.zxing.client.android.camera.open;

import com.google.zxing.client.android.common.PlatformSupportManager;

public final class OpenCameraManager
  extends PlatformSupportManager<OpenCameraInterface>
{
  public OpenCameraManager()
  {
    super(OpenCameraInterface.class, new DefaultOpenCameraInterface());
    addImplementationClass(9, "com.google.zxing.client.android.camera.open.GingerbreadOpenCameraInterface");
  }
}

/* Location:
 * Qualified Name:     com.google.zxing.client.android.camera.open.OpenCameraManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
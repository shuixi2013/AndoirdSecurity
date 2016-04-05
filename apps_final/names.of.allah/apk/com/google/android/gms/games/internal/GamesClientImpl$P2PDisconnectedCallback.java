package com.google.android.gms.games.internal;

import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hc.b;

final class GamesClientImpl$P2PDisconnectedCallback
  extends hc<IGamesService>.b<RoomStatusUpdateListener>
{
  private final String On;
  
  GamesClientImpl$P2PDisconnectedCallback(GamesClientImpl paramGamesClientImpl, RoomStatusUpdateListener paramRoomStatusUpdateListener, String paramString)
  {
    super(paramGamesClientImpl, paramRoomStatusUpdateListener);
    On = paramString;
  }
  
  public final void a(RoomStatusUpdateListener paramRoomStatusUpdateListener)
  {
    if (paramRoomStatusUpdateListener != null) {
      paramRoomStatusUpdateListener.onP2PDisconnected(On);
    }
  }
  
  protected final void fp() {}
}

/* Location:
 * Qualified Name:     com.google.android.gms.games.internal.GamesClientImpl.P2PDisconnectedCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
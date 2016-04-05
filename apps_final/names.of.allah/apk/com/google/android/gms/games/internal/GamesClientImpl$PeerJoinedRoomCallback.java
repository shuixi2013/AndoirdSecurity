package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import java.util.ArrayList;

final class GamesClientImpl$PeerJoinedRoomCallback
  extends GamesClientImpl.AbstractPeerStatusCallback
{
  GamesClientImpl$PeerJoinedRoomCallback(GamesClientImpl paramGamesClientImpl, RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
  {
    super(paramGamesClientImpl, paramRoomStatusUpdateListener, paramDataHolder, paramArrayOfString);
  }
  
  protected final void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
  {
    paramRoomStatusUpdateListener.onPeerJoined(paramRoom, paramArrayList);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.games.internal.GamesClientImpl.PeerJoinedRoomCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
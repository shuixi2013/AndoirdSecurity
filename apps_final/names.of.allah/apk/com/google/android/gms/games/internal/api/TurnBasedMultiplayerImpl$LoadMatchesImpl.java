package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;

abstract class TurnBasedMultiplayerImpl$LoadMatchesImpl
  extends Games.BaseGamesApiMethodImpl<TurnBasedMultiplayer.LoadMatchesResult>
{
  public TurnBasedMultiplayer.LoadMatchesResult ah(Status paramStatus)
  {
    return new TurnBasedMultiplayerImpl.LoadMatchesImpl.1(this, paramStatus);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.games.internal.api.TurnBasedMultiplayerImpl.LoadMatchesImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
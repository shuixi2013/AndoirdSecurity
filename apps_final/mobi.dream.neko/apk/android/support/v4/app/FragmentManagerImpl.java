package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class FragmentManagerImpl
  extends FragmentManager
{
  static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
  static final Interpolator ACCELERATE_QUINT;
  static final int ANIM_DUR = 220;
  public static final int ANIM_STYLE_CLOSE_ENTER = 3;
  public static final int ANIM_STYLE_CLOSE_EXIT = 4;
  public static final int ANIM_STYLE_FADE_ENTER = 5;
  public static final int ANIM_STYLE_FADE_EXIT = 6;
  public static final int ANIM_STYLE_OPEN_ENTER = 1;
  public static final int ANIM_STYLE_OPEN_EXIT = 2;
  static boolean DEBUG = false;
  static final Interpolator DECELERATE_CUBIC;
  static final Interpolator DECELERATE_QUINT;
  static final boolean HONEYCOMB;
  static final String TAG = "FragmentManager";
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  static final String TARGET_STATE_TAG = "android:target_state";
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  static final String VIEW_STATE_TAG = "android:view_state";
  ArrayList<Fragment> mActive;
  FragmentActivity mActivity;
  ArrayList<Fragment> mAdded;
  ArrayList<Integer> mAvailBackStackIndices;
  ArrayList<Integer> mAvailIndices;
  ArrayList<BackStackRecord> mBackStack;
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  ArrayList<BackStackRecord> mBackStackIndices;
  FragmentContainer mContainer;
  ArrayList<Fragment> mCreatedMenus;
  int mCurState = 0;
  boolean mDestroyed;
  Runnable mExecCommit = new Runnable()
  {
    public void run()
    {
      execPendingActions();
    }
  };
  boolean mExecutingActions;
  boolean mHavePendingDeferredStart;
  boolean mNeedMenuInvalidate;
  String mNoTransactionsBecause;
  Fragment mParent;
  ArrayList<Runnable> mPendingActions;
  SparseArray<Parcelable> mStateArray = null;
  Bundle mStateBundle = null;
  boolean mStateSaved;
  Runnable[] mTmpActions;
  
  static
  {
    boolean bool = false;
    DEBUG = false;
    if (Build.VERSION.SDK_INT >= 11) {
      bool = true;
    }
    HONEYCOMB = bool;
    DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
  }
  
  private void checkStateLoss()
  {
    if (mStateSaved) {
      throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }
    if (mNoTransactionsBecause != null) {
      throw new IllegalStateException("Can not perform this action inside of " + mNoTransactionsBecause);
    }
  }
  
  static Animation makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2)
  {
    paramContext = new AlphaAnimation(paramFloat1, paramFloat2);
    paramContext.setInterpolator(DECELERATE_CUBIC);
    paramContext.setDuration(220L);
    return paramContext;
  }
  
  static Animation makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramContext = new AnimationSet(false);
    Object localObject = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    ((ScaleAnimation)localObject).setInterpolator(DECELERATE_QUINT);
    ((ScaleAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    localObject = new AlphaAnimation(paramFloat3, paramFloat4);
    ((AlphaAnimation)localObject).setInterpolator(DECELERATE_CUBIC);
    ((AlphaAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    return paramContext;
  }
  
  public static int reverseTransit(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 4097: 
      return 8194;
    case 8194: 
      return 4097;
    }
    return 4099;
  }
  
  private void throwException(RuntimeException paramRuntimeException)
  {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter localPrintWriter = new PrintWriter(new LogWriter("FragmentManager"));
    if (mActivity != null) {}
    for (;;)
    {
      try
      {
        mActivity.dump("  ", null, localPrintWriter, new String[0]);
        throw paramRuntimeException;
      }
      catch (Exception localException1)
      {
        Log.e("FragmentManager", "Failed dumping state", localException1);
        continue;
      }
      try
      {
        dump("  ", null, localException1, new String[0]);
      }
      catch (Exception localException2)
      {
        Log.e("FragmentManager", "Failed dumping state", localException2);
      }
    }
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean)
  {
    switch (paramInt)
    {
    default: 
      return -1;
    case 4097: 
      if (paramBoolean) {
        return 1;
      }
      return 2;
    case 8194: 
      if (paramBoolean) {
        return 3;
      }
      return 4;
    }
    if (paramBoolean) {
      return 5;
    }
    return 6;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord)
  {
    if (mBackStack == null) {
      mBackStack = new ArrayList();
    }
    mBackStack.add(paramBackStackRecord);
    reportBackStackChanged();
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean)
  {
    if (mAdded == null) {
      mAdded = new ArrayList();
    }
    if (DEBUG) {
      Log.v("FragmentManager", "add: " + paramFragment);
    }
    makeActive(paramFragment);
    if (!mDetached)
    {
      if (mAdded.contains(paramFragment)) {
        throw new IllegalStateException("Fragment already added: " + paramFragment);
      }
      mAdded.add(paramFragment);
      mAdded = true;
      mRemoving = false;
      if ((mHasMenu) && (mMenuVisible)) {
        mNeedMenuInvalidate = true;
      }
      if (paramBoolean) {
        moveToState(paramFragment);
      }
    }
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (mBackStackChangeListeners == null) {
      mBackStackChangeListeners = new ArrayList();
    }
    mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord)
  {
    try
    {
      if ((mAvailBackStackIndices == null) || (mAvailBackStackIndices.size() <= 0))
      {
        if (mBackStackIndices == null) {
          mBackStackIndices = new ArrayList();
        }
        i = mBackStackIndices.size();
        if (DEBUG) {
          Log.v("FragmentManager", "Setting back stack index " + i + " to " + paramBackStackRecord);
        }
        mBackStackIndices.add(paramBackStackRecord);
        return i;
      }
      int i = ((Integer)mAvailBackStackIndices.remove(mAvailBackStackIndices.size() - 1)).intValue();
      if (DEBUG) {
        Log.v("FragmentManager", "Adding back stack index " + i + " with " + paramBackStackRecord);
      }
      mBackStackIndices.set(i, paramBackStackRecord);
      return i;
    }
    finally {}
  }
  
  public void attachActivity(FragmentActivity paramFragmentActivity, FragmentContainer paramFragmentContainer, Fragment paramFragment)
  {
    if (mActivity != null) {
      throw new IllegalStateException("Already attached");
    }
    mActivity = paramFragmentActivity;
    mContainer = paramFragmentContainer;
    mParent = paramFragment;
  }
  
  public void attachFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "attach: " + paramFragment);
    }
    if (mDetached)
    {
      mDetached = false;
      if (!mAdded)
      {
        if (mAdded == null) {
          mAdded = new ArrayList();
        }
        if (mAdded.contains(paramFragment)) {
          throw new IllegalStateException("Fragment already added: " + paramFragment);
        }
        if (DEBUG) {
          Log.v("FragmentManager", "add from attach: " + paramFragment);
        }
        mAdded.add(paramFragment);
        mAdded = true;
        if ((mHasMenu) && (mMenuVisible)) {
          mNeedMenuInvalidate = true;
        }
        moveToState(paramFragment, mCurState, paramInt1, paramInt2, false);
      }
    }
  }
  
  public FragmentTransaction beginTransaction()
  {
    return new BackStackRecord(this);
  }
  
  public void detachFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "detach: " + paramFragment);
    }
    if (!mDetached)
    {
      mDetached = true;
      if (mAdded)
      {
        if (mAdded != null)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "remove from detach: " + paramFragment);
          }
          mAdded.remove(paramFragment);
        }
        if ((mHasMenu) && (mMenuVisible)) {
          mNeedMenuInvalidate = true;
        }
        mAdded = false;
        moveToState(paramFragment, 1, paramInt1, paramInt2, false);
      }
    }
  }
  
  public void dispatchActivityCreated()
  {
    mStateSaved = false;
    moveToState(2, false);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    if (mAdded != null)
    {
      int i = 0;
      while (i < mAdded.size())
      {
        Fragment localFragment = (Fragment)mAdded.get(i);
        if (localFragment != null) {
          localFragment.performConfigurationChanged(paramConfiguration);
        }
        i += 1;
      }
    }
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem)
  {
    if (mAdded != null)
    {
      int i = 0;
      while (i < mAdded.size())
      {
        Fragment localFragment = (Fragment)mAdded.get(i);
        if ((localFragment != null) && (localFragment.performContextItemSelected(paramMenuItem))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public void dispatchCreate()
  {
    mStateSaved = false;
    moveToState(1, false);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    Object localObject2 = null;
    Object localObject1 = null;
    int i;
    if (mAdded != null)
    {
      i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        bool2 = bool1;
        if (i >= mAdded.size()) {
          break;
        }
        Fragment localFragment = (Fragment)mAdded.get(i);
        localObject2 = localObject1;
        bool2 = bool1;
        if (localFragment != null)
        {
          localObject2 = localObject1;
          bool2 = bool1;
          if (localFragment.performCreateOptionsMenu(paramMenu, paramMenuInflater))
          {
            bool2 = true;
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localFragment);
          }
        }
        i += 1;
        localObject1 = localObject2;
        bool1 = bool2;
      }
    }
    if (mCreatedMenus != null)
    {
      i = 0;
      while (i < mCreatedMenus.size())
      {
        paramMenu = (Fragment)mCreatedMenus.get(i);
        if ((localObject2 == null) || (!((ArrayList)localObject2).contains(paramMenu))) {
          paramMenu.onDestroyOptionsMenu();
        }
        i += 1;
      }
    }
    mCreatedMenus = ((ArrayList)localObject2);
    return bool2;
  }
  
  public void dispatchDestroy()
  {
    mDestroyed = true;
    execPendingActions();
    moveToState(0, false);
    mActivity = null;
    mContainer = null;
    mParent = null;
  }
  
  public void dispatchDestroyView()
  {
    moveToState(1, false);
  }
  
  public void dispatchLowMemory()
  {
    if (mAdded != null)
    {
      int i = 0;
      while (i < mAdded.size())
      {
        Fragment localFragment = (Fragment)mAdded.get(i);
        if (localFragment != null) {
          localFragment.performLowMemory();
        }
        i += 1;
      }
    }
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (mAdded != null)
    {
      int i = 0;
      while (i < mAdded.size())
      {
        Fragment localFragment = (Fragment)mAdded.get(i);
        if ((localFragment != null) && (localFragment.performOptionsItemSelected(paramMenuItem))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu)
  {
    if (mAdded != null)
    {
      int i = 0;
      while (i < mAdded.size())
      {
        Fragment localFragment = (Fragment)mAdded.get(i);
        if (localFragment != null) {
          localFragment.performOptionsMenuClosed(paramMenu);
        }
        i += 1;
      }
    }
  }
  
  public void dispatchPause()
  {
    moveToState(4, false);
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    if (mAdded != null)
    {
      int i = 0;
      for (;;)
      {
        bool2 = bool1;
        if (i >= mAdded.size()) {
          break;
        }
        Fragment localFragment = (Fragment)mAdded.get(i);
        bool2 = bool1;
        if (localFragment != null)
        {
          bool2 = bool1;
          if (localFragment.performPrepareOptionsMenu(paramMenu)) {
            bool2 = true;
          }
        }
        i += 1;
        bool1 = bool2;
      }
    }
    return bool2;
  }
  
  public void dispatchReallyStop()
  {
    moveToState(2, false);
  }
  
  public void dispatchResume()
  {
    mStateSaved = false;
    moveToState(5, false);
  }
  
  public void dispatchStart()
  {
    mStateSaved = false;
    moveToState(4, false);
  }
  
  public void dispatchStop()
  {
    mStateSaved = true;
    moveToState(3, false);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    String str = paramString + "    ";
    int j;
    int i;
    Object localObject;
    if (mActive != null)
    {
      j = mActive.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("Active Fragments in ");
        paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
        paramPrintWriter.println(":");
        i = 0;
        while (i < j)
        {
          localObject = (Fragment)mActive.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(localObject);
          if (localObject != null) {
            ((Fragment)localObject).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          }
          i += 1;
        }
      }
    }
    if (mAdded != null)
    {
      j = mAdded.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Added Fragments:");
        i = 0;
        while (i < j)
        {
          localObject = (Fragment)mAdded.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject).toString());
          i += 1;
        }
      }
    }
    if (mCreatedMenus != null)
    {
      j = mCreatedMenus.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Fragments Created Menus:");
        i = 0;
        while (i < j)
        {
          localObject = (Fragment)mCreatedMenus.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject).toString());
          i += 1;
        }
      }
    }
    if (mBackStack != null)
    {
      j = mBackStack.size();
      if (j > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Back Stack:");
        i = 0;
        while (i < j)
        {
          localObject = (BackStackRecord)mBackStack.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((BackStackRecord)localObject).toString());
          ((BackStackRecord)localObject).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          i += 1;
        }
      }
    }
    try
    {
      if (mBackStackIndices != null)
      {
        j = mBackStackIndices.size();
        if (j > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Back Stack Indices:");
          i = 0;
          while (i < j)
          {
            paramFileDescriptor = (BackStackRecord)mBackStackIndices.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      if ((mAvailBackStackIndices != null) && (mAvailBackStackIndices.size() > 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mAvailBackStackIndices: ");
        paramPrintWriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
      }
      if (mPendingActions != null)
      {
        j = mPendingActions.size();
        if (j > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Pending Actions:");
          i = 0;
          while (i < j)
          {
            paramFileDescriptor = (Runnable)mPendingActions.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      paramPrintWriter.print(paramString);
    }
    finally {}
    paramPrintWriter.println("FragmentManager misc state:");
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mActivity=");
    paramPrintWriter.println(mActivity);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mContainer=");
    paramPrintWriter.println(mContainer);
    if (mParent != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mParent=");
      paramPrintWriter.println(mParent);
    }
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mCurState=");
    paramPrintWriter.print(mCurState);
    paramPrintWriter.print(" mStateSaved=");
    paramPrintWriter.print(mStateSaved);
    paramPrintWriter.print(" mDestroyed=");
    paramPrintWriter.println(mDestroyed);
    if (mNeedMenuInvalidate)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mNeedMenuInvalidate=");
      paramPrintWriter.println(mNeedMenuInvalidate);
    }
    if (mNoTransactionsBecause != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mNoTransactionsBecause=");
      paramPrintWriter.println(mNoTransactionsBecause);
    }
    if ((mAvailIndices != null) && (mAvailIndices.size() > 0))
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mAvailIndices: ");
      paramPrintWriter.println(Arrays.toString(mAvailIndices.toArray()));
    }
  }
  
  public void enqueueAction(Runnable paramRunnable, boolean paramBoolean)
  {
    if (!paramBoolean) {
      checkStateLoss();
    }
    try
    {
      if ((mDestroyed) || (mActivity == null)) {
        throw new IllegalStateException("Activity has been destroyed");
      }
    }
    finally
    {
      throw paramRunnable;
      if (mPendingActions == null) {
        mPendingActions = new ArrayList();
      }
      mPendingActions.add(paramRunnable);
      if (mPendingActions.size() == 1) {
        mActivity.mHandler.removeCallbacks(mExecCommit);
      }
    }
  }
  
  public boolean execPendingActions()
  {
    if (mExecutingActions) {
      throw new IllegalStateException("Recursive entry to executePendingTransactions");
    }
    if (Looper.myLooper() != mActivity.mHandler.getLooper()) {
      throw new IllegalStateException("Must be called from main thread of process");
    }
    int j;
    for (boolean bool = false;; bool = true) {
      try
      {
        if ((mPendingActions == null) || (mPendingActions.size() == 0))
        {
          if (!mHavePendingDeferredStart) {
            break label274;
          }
          j = 0;
          i = 0;
          while (i < mActive.size())
          {
            Fragment localFragment = (Fragment)mActive.get(i);
            int k = j;
            int m;
            if (localFragment != null)
            {
              k = j;
              if (mLoaderManager != null) {
                m = j | mLoaderManager.hasRunningLoaders();
              }
            }
            i += 1;
            j = m;
          }
        }
        j = mPendingActions.size();
        if ((mTmpActions == null) || (mTmpActions.length < j)) {
          mTmpActions = new Runnable[j];
        }
        mPendingActions.toArray(mTmpActions);
        mPendingActions.clear();
        mActivity.mHandler.removeCallbacks(mExecCommit);
        mExecutingActions = true;
        int i = 0;
        while (i < j)
        {
          mTmpActions[i].run();
          mTmpActions[i] = null;
          i += 1;
        }
        mExecutingActions = false;
      }
      finally {}
    }
    if (j == 0)
    {
      mHavePendingDeferredStart = false;
      startPendingDeferredFragments();
    }
    label274:
    return bool;
  }
  
  public boolean executePendingTransactions()
  {
    return execPendingActions();
  }
  
  public Fragment findFragmentById(int paramInt)
  {
    int i;
    Object localObject;
    if (mAdded != null)
    {
      i = mAdded.size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)mAdded.get(i);
        if ((localObject != null) && (mFragmentId == paramInt)) {
          return (Fragment)localObject;
        }
        i -= 1;
      }
    }
    if (mActive != null)
    {
      i = mActive.size() - 1;
      for (;;)
      {
        if (i < 0) {
          break label118;
        }
        Fragment localFragment = (Fragment)mActive.get(i);
        if (localFragment != null)
        {
          localObject = localFragment;
          if (mFragmentId == paramInt) {
            break;
          }
        }
        i -= 1;
      }
    }
    label118:
    return null;
  }
  
  public Fragment findFragmentByTag(String paramString)
  {
    int i;
    Object localObject;
    if ((mAdded != null) && (paramString != null))
    {
      i = mAdded.size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)mAdded.get(i);
        if ((localObject != null) && (paramString.equals(mTag))) {
          return (Fragment)localObject;
        }
        i -= 1;
      }
    }
    if ((mActive != null) && (paramString != null))
    {
      i = mActive.size() - 1;
      for (;;)
      {
        if (i < 0) {
          break label132;
        }
        Fragment localFragment = (Fragment)mActive.get(i);
        if (localFragment != null)
        {
          localObject = localFragment;
          if (paramString.equals(mTag)) {
            break;
          }
        }
        i -= 1;
      }
    }
    label132:
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString)
  {
    if ((mActive != null) && (paramString != null))
    {
      int i = mActive.size() - 1;
      while (i >= 0)
      {
        Fragment localFragment = (Fragment)mActive.get(i);
        if (localFragment != null)
        {
          localFragment = localFragment.findFragmentByWho(paramString);
          if (localFragment != null) {
            return localFragment;
          }
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public void freeBackStackIndex(int paramInt)
  {
    try
    {
      mBackStackIndices.set(paramInt, null);
      if (mAvailBackStackIndices == null) {
        mAvailBackStackIndices = new ArrayList();
      }
      if (DEBUG) {
        Log.v("FragmentManager", "Freeing back stack index " + paramInt);
      }
      mAvailBackStackIndices.add(Integer.valueOf(paramInt));
      return;
    }
    finally {}
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt)
  {
    return (FragmentManager.BackStackEntry)mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount()
  {
    if (mBackStack != null) {
      return mBackStack.size();
    }
    return 0;
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString)
  {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1) {
      paramBundle = null;
    }
    Fragment localFragment;
    do
    {
      return paramBundle;
      if (i >= mActive.size()) {
        throwException(new IllegalStateException("Fragement no longer exists for key " + paramString + ": index " + i));
      }
      localFragment = (Fragment)mActive.get(i);
      paramBundle = localFragment;
    } while (localFragment != null);
    throwException(new IllegalStateException("Fragement no longer exists for key " + paramString + ": index " + i));
    return localFragment;
  }
  
  public List<Fragment> getFragments()
  {
    return mActive;
  }
  
  public void hideFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "hide: " + paramFragment);
    }
    if (!mHidden)
    {
      mHidden = true;
      if (mView != null)
      {
        Animation localAnimation = loadAnimation(paramFragment, paramInt1, false, paramInt2);
        if (localAnimation != null) {
          mView.startAnimation(localAnimation);
        }
        mView.setVisibility(8);
      }
      if ((mAdded) && (mHasMenu) && (mMenuVisible)) {
        mNeedMenuInvalidate = true;
      }
      paramFragment.onHiddenChanged(true);
    }
  }
  
  Animation loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    Animation localAnimation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, mNextAnim);
    if (localAnimation != null) {
      return localAnimation;
    }
    if (mNextAnim != 0)
    {
      paramFragment = AnimationUtils.loadAnimation(mActivity, mNextAnim);
      if (paramFragment != null) {
        return paramFragment;
      }
    }
    if (paramInt1 == 0) {
      return null;
    }
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0) {
      return null;
    }
    switch (paramInt1)
    {
    default: 
      paramInt1 = paramInt2;
      if (paramInt2 == 0)
      {
        paramInt1 = paramInt2;
        if (mActivity.getWindow() != null) {
          paramInt1 = mActivity.getWindow().getAttributes().windowAnimations;
        }
      }
      if (paramInt1 == 0) {
        return null;
      }
      break;
    case 1: 
      return makeOpenCloseAnimation(mActivity, 1.125F, 1.0F, 0.0F, 1.0F);
    case 2: 
      return makeOpenCloseAnimation(mActivity, 1.0F, 0.975F, 1.0F, 0.0F);
    case 3: 
      return makeOpenCloseAnimation(mActivity, 0.975F, 1.0F, 0.0F, 1.0F);
    case 4: 
      return makeOpenCloseAnimation(mActivity, 1.0F, 1.075F, 1.0F, 0.0F);
    case 5: 
      return makeFadeAnimation(mActivity, 0.0F, 1.0F);
    case 6: 
      return makeFadeAnimation(mActivity, 1.0F, 0.0F);
    }
    return null;
  }
  
  void makeActive(Fragment paramFragment)
  {
    if (mIndex >= 0) {}
    for (;;)
    {
      return;
      if ((mAvailIndices == null) || (mAvailIndices.size() <= 0))
      {
        if (mActive == null) {
          mActive = new ArrayList();
        }
        paramFragment.setIndex(mActive.size(), mParent);
        mActive.add(paramFragment);
      }
      while (DEBUG)
      {
        Log.v("FragmentManager", "Allocated fragment index " + paramFragment);
        return;
        paramFragment.setIndex(((Integer)mAvailIndices.remove(mAvailIndices.size() - 1)).intValue(), mParent);
        mActive.set(mIndex, paramFragment);
      }
    }
  }
  
  void makeInactive(Fragment paramFragment)
  {
    if (mIndex < 0) {
      return;
    }
    if (DEBUG) {
      Log.v("FragmentManager", "Freeing fragment index " + paramFragment);
    }
    mActive.set(mIndex, null);
    if (mAvailIndices == null) {
      mAvailIndices = new ArrayList();
    }
    mAvailIndices.add(Integer.valueOf(mIndex));
    mActivity.invalidateSupportFragment(mWho);
    paramFragment.initState();
  }
  
  void moveToState(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if ((mActivity == null) && (paramInt1 != 0)) {
      throw new IllegalStateException("No activity");
    }
    if ((!paramBoolean) && (mCurState == paramInt1)) {}
    do
    {
      do
      {
        return;
        mCurState = paramInt1;
      } while (mActive == null);
      boolean bool1 = false;
      int i = 0;
      while (i < mActive.size())
      {
        Fragment localFragment = (Fragment)mActive.get(i);
        boolean bool2 = bool1;
        if (localFragment != null)
        {
          moveToState(localFragment, paramInt1, paramInt2, paramInt3, false);
          bool2 = bool1;
          if (mLoaderManager != null) {
            bool2 = bool1 | mLoaderManager.hasRunningLoaders();
          }
        }
        i += 1;
        bool1 = bool2;
      }
      if (!bool1) {
        startPendingDeferredFragments();
      }
    } while ((!mNeedMenuInvalidate) || (mActivity == null) || (mCurState != 5));
    mActivity.supportInvalidateOptionsMenu();
    mNeedMenuInvalidate = false;
  }
  
  void moveToState(int paramInt, boolean paramBoolean)
  {
    moveToState(paramInt, 0, 0, paramBoolean);
  }
  
  void moveToState(Fragment paramFragment)
  {
    moveToState(paramFragment, mCurState, 0, 0, false);
  }
  
  void moveToState(final Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i;
    if (mAdded)
    {
      i = paramInt1;
      if (!mDetached) {}
    }
    else
    {
      i = paramInt1;
      if (paramInt1 > 1) {
        i = 1;
      }
    }
    int j = i;
    if (mRemoving)
    {
      j = i;
      if (i > mState) {
        j = mState;
      }
    }
    paramInt1 = j;
    if (mDeferStart)
    {
      paramInt1 = j;
      if (mState < 4)
      {
        paramInt1 = j;
        if (j > 3) {
          paramInt1 = 3;
        }
      }
    }
    int k;
    if (mState < paramInt1)
    {
      if ((mFromLayout) && (!mInLayout)) {
        return;
      }
      if (mAnimatingAway != null)
      {
        mAnimatingAway = null;
        moveToState(paramFragment, mStateAfterAnimating, 0, 0, true);
      }
      i = paramInt1;
      k = paramInt1;
      j = paramInt1;
      switch (mState)
      {
      default: 
        i = paramInt1;
      }
    }
    for (;;)
    {
      mState = i;
      return;
      if (DEBUG) {
        Log.v("FragmentManager", "moveto CREATED: " + paramFragment);
      }
      j = paramInt1;
      if (mSavedFragmentState != null)
      {
        mSavedViewState = mSavedFragmentState.getSparseParcelableArray("android:view_state");
        mTarget = getFragment(mSavedFragmentState, "android:target_state");
        if (mTarget != null) {
          mTargetRequestCode = mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        mUserVisibleHint = mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        j = paramInt1;
        if (!mUserVisibleHint)
        {
          mDeferStart = true;
          j = paramInt1;
          if (paramInt1 > 3) {
            j = 3;
          }
        }
      }
      mActivity = mActivity;
      mParentFragment = mParent;
      if (mParent != null) {}
      for (Object localObject1 = mParent.mChildFragmentManager;; localObject1 = mActivity.mFragments)
      {
        mFragmentManager = ((FragmentManagerImpl)localObject1);
        mCalled = false;
        paramFragment.onAttach(mActivity);
        if (mCalled) {
          break;
        }
        throw new SuperNotCalledException("Fragment " + paramFragment + " did not call through to super.onAttach()");
      }
      if (mParentFragment == null) {
        mActivity.onAttachFragment(paramFragment);
      }
      if (!mRetaining) {
        paramFragment.performCreate(mSavedFragmentState);
      }
      mRetaining = false;
      i = j;
      label553:
      Object localObject2;
      if (mFromLayout)
      {
        mView = paramFragment.performCreateView(paramFragment.getLayoutInflater(mSavedFragmentState), null, mSavedFragmentState);
        if (mView != null)
        {
          mInnerView = mView;
          mView = NoSaveStateFrameLayout.wrap(mView);
          if (mHidden) {
            mView.setVisibility(8);
          }
          paramFragment.onViewCreated(mView, mSavedFragmentState);
          i = j;
        }
      }
      else
      {
        k = i;
        if (i > 1)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + paramFragment);
          }
          if (!mFromLayout)
          {
            localObject1 = null;
            if (mContainerId != 0)
            {
              localObject2 = (ViewGroup)mContainer.findViewById(mContainerId);
              localObject1 = localObject2;
              if (localObject2 == null)
              {
                localObject1 = localObject2;
                if (!mRestored)
                {
                  throwException(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(mContainerId) + " (" + paramFragment.getResources().getResourceName(mContainerId) + ") for fragment " + paramFragment));
                  localObject1 = localObject2;
                }
              }
            }
            mContainer = ((ViewGroup)localObject1);
            mView = paramFragment.performCreateView(paramFragment.getLayoutInflater(mSavedFragmentState), (ViewGroup)localObject1, mSavedFragmentState);
            if (mView == null) {
              break label1004;
            }
            mInnerView = mView;
            mView = NoSaveStateFrameLayout.wrap(mView);
            if (localObject1 != null)
            {
              localObject2 = loadAnimation(paramFragment, paramInt2, true, paramInt3);
              if (localObject2 != null) {
                mView.startAnimation((Animation)localObject2);
              }
              ((ViewGroup)localObject1).addView(mView);
            }
            if (mHidden) {
              mView.setVisibility(8);
            }
            paramFragment.onViewCreated(mView, mSavedFragmentState);
          }
        }
      }
      for (;;)
      {
        paramFragment.performActivityCreated(mSavedFragmentState);
        if (mView != null) {
          paramFragment.restoreViewState(mSavedFragmentState);
        }
        mSavedFragmentState = null;
        k = i;
        j = k;
        if (k > 3)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "moveto STARTED: " + paramFragment);
          }
          paramFragment.performStart();
          j = k;
        }
        i = j;
        if (j <= 4) {
          break;
        }
        if (DEBUG) {
          Log.v("FragmentManager", "moveto RESUMED: " + paramFragment);
        }
        mResumed = true;
        paramFragment.performResume();
        mSavedFragmentState = null;
        mSavedViewState = null;
        i = j;
        break;
        mInnerView = null;
        i = j;
        break label553;
        label1004:
        mInnerView = null;
      }
      i = paramInt1;
      if (mState > paramInt1) {
        switch (mState)
        {
        default: 
          i = paramInt1;
          break;
        case 1: 
        case 5: 
        case 4: 
        case 3: 
        case 2: 
          for (;;)
          {
            i = paramInt1;
            if (paramInt1 >= 1) {
              break;
            }
            if ((mDestroyed) && (mAnimatingAway != null))
            {
              localObject1 = mAnimatingAway;
              mAnimatingAway = null;
              ((View)localObject1).clearAnimation();
            }
            if (mAnimatingAway == null) {
              break label1440;
            }
            mStateAfterAnimating = paramInt1;
            i = 1;
            break;
            if (paramInt1 < 5)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom RESUMED: " + paramFragment);
              }
              paramFragment.performPause();
              mResumed = false;
            }
            if (paramInt1 < 4)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom STARTED: " + paramFragment);
              }
              paramFragment.performStop();
            }
            if (paramInt1 < 3)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom STOPPED: " + paramFragment);
              }
              paramFragment.performReallyStop();
            }
            if (paramInt1 < 2)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + paramFragment);
              }
              if ((mView != null) && (!mActivity.isFinishing()) && (mSavedViewState == null)) {
                saveFragmentViewState(paramFragment);
              }
              paramFragment.performDestroyView();
              if ((mView != null) && (mContainer != null))
              {
                localObject2 = null;
                localObject1 = localObject2;
                if (mCurState > 0)
                {
                  localObject1 = localObject2;
                  if (!mDestroyed) {
                    localObject1 = loadAnimation(paramFragment, paramInt2, false, paramInt3);
                  }
                }
                if (localObject1 != null)
                {
                  mAnimatingAway = mView;
                  mStateAfterAnimating = paramInt1;
                  ((Animation)localObject1).setAnimationListener(new Animation.AnimationListener()
                  {
                    public void onAnimationEnd(Animation paramAnonymousAnimation)
                    {
                      if (paramFragmentmAnimatingAway != null)
                      {
                        paramFragmentmAnimatingAway = null;
                        moveToState(paramFragment, paramFragmentmStateAfterAnimating, 0, 0, false);
                      }
                    }
                    
                    public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
                    
                    public void onAnimationStart(Animation paramAnonymousAnimation) {}
                  });
                  mView.startAnimation((Animation)localObject1);
                }
                mContainer.removeView(mView);
              }
              mContainer = null;
              mView = null;
              mInnerView = null;
            }
          }
          label1440:
          if (DEBUG) {
            Log.v("FragmentManager", "movefrom CREATED: " + paramFragment);
          }
          if (!mRetaining) {
            paramFragment.performDestroy();
          }
          mCalled = false;
          paramFragment.onDetach();
          if (!mCalled) {
            throw new SuperNotCalledException("Fragment " + paramFragment + " did not call through to super.onDetach()");
          }
          i = paramInt1;
          if (!paramBoolean) {
            if (!mRetaining)
            {
              makeInactive(paramFragment);
              i = paramInt1;
            }
            else
            {
              mActivity = null;
              mFragmentManager = null;
              i = paramInt1;
            }
          }
          break;
        }
      }
    }
  }
  
  public void noteStateNotSaved()
  {
    mStateSaved = false;
  }
  
  public void performPendingDeferredStart(Fragment paramFragment)
  {
    if (mDeferStart)
    {
      if (mExecutingActions) {
        mHavePendingDeferredStart = true;
      }
    }
    else {
      return;
    }
    mDeferStart = false;
    moveToState(paramFragment, mCurState, 0, 0, false);
  }
  
  public void popBackStack()
  {
    enqueueAction(new Runnable()
    {
      public void run()
      {
        popBackStackState(mActivity.mHandler, null, -1, 0);
      }
    }, false);
  }
  
  public void popBackStack(final int paramInt1, final int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Bad id: " + paramInt1);
    }
    enqueueAction(new Runnable()
    {
      public void run()
      {
        popBackStackState(mActivity.mHandler, null, paramInt1, paramInt2);
      }
    }, false);
  }
  
  public void popBackStack(final String paramString, final int paramInt)
  {
    enqueueAction(new Runnable()
    {
      public void run()
      {
        popBackStackState(mActivity.mHandler, paramString, -1, paramInt);
      }
    }, false);
  }
  
  public boolean popBackStackImmediate()
  {
    checkStateLoss();
    executePendingTransactions();
    return popBackStackState(mActivity.mHandler, null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2)
  {
    checkStateLoss();
    executePendingTransactions();
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Bad id: " + paramInt1);
    }
    return popBackStackState(mActivity.mHandler, null, paramInt1, paramInt2);
  }
  
  public boolean popBackStackImmediate(String paramString, int paramInt)
  {
    checkStateLoss();
    executePendingTransactions();
    return popBackStackState(mActivity.mHandler, paramString, -1, paramInt);
  }
  
  boolean popBackStackState(Handler paramHandler, String paramString, int paramInt1, int paramInt2)
  {
    if (mBackStack == null) {
      break label114;
    }
    label7:
    do
    {
      return false;
      if ((paramString != null) || (paramInt1 >= 0) || ((paramInt2 & 0x1) != 0)) {
        break;
      }
      paramInt1 = mBackStack.size() - 1;
    } while (paramInt1 < 0);
    ((BackStackRecord)mBackStack.remove(paramInt1)).popFromBackStack(true);
    reportBackStackChanged();
    for (;;)
    {
      return true;
      int i = -1;
      if ((paramString != null) || (paramInt1 >= 0))
      {
        int j = mBackStack.size() - 1;
        for (;;)
        {
          if (j >= 0)
          {
            paramHandler = (BackStackRecord)mBackStack.get(j);
            if ((paramString == null) || (!paramString.equals(paramHandler.getName()))) {}
          }
          else
          {
            label114:
            if (j < 0) {
              break label7;
            }
            i = j;
            if ((paramInt2 & 0x1) == 0) {
              break label223;
            }
            paramInt2 = j - 1;
            for (;;)
            {
              i = paramInt2;
              if (paramInt2 < 0) {
                break;
              }
              paramHandler = (BackStackRecord)mBackStack.get(paramInt2);
              if ((paramString == null) || (!paramString.equals(paramHandler.getName())))
              {
                i = paramInt2;
                if (paramInt1 < 0) {
                  break;
                }
                i = paramInt2;
                if (paramInt1 != mIndex) {
                  break;
                }
              }
              paramInt2 -= 1;
            }
          }
          if ((paramInt1 >= 0) && (paramInt1 == mIndex)) {
            break;
          }
          j -= 1;
        }
      }
      label223:
      if (i == mBackStack.size() - 1) {
        break label7;
      }
      paramHandler = new ArrayList();
      paramInt1 = mBackStack.size() - 1;
      while (paramInt1 > i)
      {
        paramHandler.add(mBackStack.remove(paramInt1));
        paramInt1 -= 1;
      }
      paramInt2 = paramHandler.size() - 1;
      paramInt1 = 0;
      if (paramInt1 <= paramInt2)
      {
        if (DEBUG) {
          Log.v("FragmentManager", "Popping back stack state: " + paramHandler.get(paramInt1));
        }
        paramString = (BackStackRecord)paramHandler.get(paramInt1);
        if (paramInt1 == paramInt2) {}
        for (boolean bool = true;; bool = false)
        {
          paramString.popFromBackStack(bool);
          paramInt1 += 1;
          break;
        }
      }
      reportBackStackChanged();
    }
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    if (mIndex < 0) {
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager"));
    }
    paramBundle.putInt(paramString, mIndex);
  }
  
  public void removeFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "remove: " + paramFragment + " nesting=" + mBackStackNesting);
    }
    if (!paramFragment.isInBackStack())
    {
      i = 1;
      if ((!mDetached) || (i != 0))
      {
        if (mAdded != null) {
          mAdded.remove(paramFragment);
        }
        if ((mHasMenu) && (mMenuVisible)) {
          mNeedMenuInvalidate = true;
        }
        mAdded = false;
        mRemoving = true;
        if (i == 0) {
          break label137;
        }
      }
    }
    label137:
    for (int i = 0;; i = 1)
    {
      moveToState(paramFragment, i, paramInt1, paramInt2, false);
      return;
      i = 0;
      break;
    }
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (mBackStackChangeListeners != null) {
      mBackStackChangeListeners.remove(paramOnBackStackChangedListener);
    }
  }
  
  void reportBackStackChanged()
  {
    if (mBackStackChangeListeners != null)
    {
      int i = 0;
      while (i < mBackStackChangeListeners.size())
      {
        ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();
        i += 1;
      }
    }
  }
  
  void restoreAllState(Parcelable paramParcelable, ArrayList<Fragment> paramArrayList)
  {
    if (paramParcelable == null) {}
    for (;;)
    {
      return;
      paramParcelable = (FragmentManagerState)paramParcelable;
      if (mActive != null)
      {
        Object localObject1;
        Object localObject2;
        if (paramArrayList != null)
        {
          i = 0;
          while (i < paramArrayList.size())
          {
            localObject1 = (Fragment)paramArrayList.get(i);
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: re-attaching retained " + localObject1);
            }
            localObject2 = mActive[mIndex];
            mInstance = ((Fragment)localObject1);
            mSavedViewState = null;
            mBackStackNesting = 0;
            mInLayout = false;
            mAdded = false;
            mTarget = null;
            if (mSavedFragmentState != null)
            {
              mSavedFragmentState.setClassLoader(mActivity.getClassLoader());
              mSavedViewState = mSavedFragmentState.getSparseParcelableArray("android:view_state");
            }
            i += 1;
          }
        }
        mActive = new ArrayList(mActive.length);
        if (mAvailIndices != null) {
          mAvailIndices.clear();
        }
        int i = 0;
        if (i < mActive.length)
        {
          localObject1 = mActive[i];
          if (localObject1 != null)
          {
            localObject2 = ((FragmentState)localObject1).instantiate(mActivity, mParent);
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: active #" + i + ": " + localObject2);
            }
            mActive.add(localObject2);
            mInstance = null;
          }
          for (;;)
          {
            i += 1;
            break;
            mActive.add(null);
            if (mAvailIndices == null) {
              mAvailIndices = new ArrayList();
            }
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: avail #" + i);
            }
            mAvailIndices.add(Integer.valueOf(i));
          }
        }
        if (paramArrayList != null)
        {
          i = 0;
          if (i < paramArrayList.size())
          {
            localObject1 = (Fragment)paramArrayList.get(i);
            if (mTargetIndex >= 0) {
              if (mTargetIndex >= mActive.size()) {
                break label450;
              }
            }
            for (mTarget = ((Fragment)mActive.get(mTargetIndex));; mTarget = null)
            {
              i += 1;
              break;
              label450:
              Log.w("FragmentManager", "Re-attaching retained fragment " + localObject1 + " target no longer exists: " + mTargetIndex);
            }
          }
        }
        if (mAdded != null)
        {
          mAdded = new ArrayList(mAdded.length);
          i = 0;
          while (i < mAdded.length)
          {
            paramArrayList = (Fragment)mActive.get(mAdded[i]);
            if (paramArrayList == null) {
              throwException(new IllegalStateException("No instantiated fragment for index #" + mAdded[i]));
            }
            mAdded = true;
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + paramArrayList);
            }
            if (mAdded.contains(paramArrayList)) {
              throw new IllegalStateException("Already added!");
            }
            mAdded.add(paramArrayList);
            i += 1;
          }
        }
        mAdded = null;
        if (mBackStack == null) {
          break;
        }
        mBackStack = new ArrayList(mBackStack.length);
        i = 0;
        while (i < mBackStack.length)
        {
          paramArrayList = mBackStack[i].instantiate(this);
          if (DEBUG)
          {
            Log.v("FragmentManager", "restoreAllState: back stack #" + i + " (index " + mIndex + "): " + paramArrayList);
            paramArrayList.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
          }
          mBackStack.add(paramArrayList);
          if (mIndex >= 0) {
            setBackStackIndex(mIndex, paramArrayList);
          }
          i += 1;
        }
      }
    }
    mBackStack = null;
  }
  
  ArrayList<Fragment> retainNonConfig()
  {
    Object localObject2 = null;
    Object localObject1 = null;
    if (mActive != null)
    {
      int i = 0;
      localObject2 = localObject1;
      if (i < mActive.size())
      {
        Fragment localFragment = (Fragment)mActive.get(i);
        Object localObject3 = localObject1;
        if (localFragment != null)
        {
          localObject3 = localObject1;
          if (mRetainInstance)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localFragment);
            mRetaining = true;
            if (mTarget == null) {
              break label159;
            }
          }
        }
        label159:
        for (int j = mTarget.mIndex;; j = -1)
        {
          mTargetIndex = j;
          localObject3 = localObject2;
          if (DEBUG)
          {
            Log.v("FragmentManager", "retainNonConfig: keeping retained " + localFragment);
            localObject3 = localObject2;
          }
          i += 1;
          localObject1 = localObject3;
          break;
        }
      }
    }
    return (ArrayList<Fragment>)localObject2;
  }
  
  Parcelable saveAllState()
  {
    execPendingActions();
    if (HONEYCOMB) {
      mStateSaved = true;
    }
    if ((mActive == null) || (mActive.size() <= 0)) {}
    FragmentState[] arrayOfFragmentState;
    int j;
    int i;
    label348:
    do
    {
      return null;
      int m = mActive.size();
      arrayOfFragmentState = new FragmentState[m];
      j = 0;
      i = 0;
      if (i < m)
      {
        localObject1 = (Fragment)mActive.get(i);
        int k;
        if (localObject1 != null)
        {
          if (mIndex < 0) {
            throwException(new IllegalStateException("Failure saving state: active " + localObject1 + " has cleared index: " + mIndex));
          }
          k = 1;
          localObject2 = new FragmentState((Fragment)localObject1);
          arrayOfFragmentState[i] = localObject2;
          if ((mState <= 0) || (mSavedFragmentState != null)) {
            break label348;
          }
          mSavedFragmentState = saveFragmentBasicState((Fragment)localObject1);
          if (mTarget != null)
          {
            if (mTarget.mIndex < 0) {
              throwException(new IllegalStateException("Failure saving state: " + localObject1 + " has target not in fragment manager: " + mTarget));
            }
            if (mSavedFragmentState == null) {
              mSavedFragmentState = new Bundle();
            }
            putFragment(mSavedFragmentState, "android:target_state", mTarget);
            if (mTargetRequestCode != 0) {
              mSavedFragmentState.putInt("android:target_req_state", mTargetRequestCode);
            }
          }
        }
        for (;;)
        {
          j = k;
          if (DEBUG)
          {
            Log.v("FragmentManager", "Saved state of " + localObject1 + ": " + mSavedFragmentState);
            j = k;
          }
          i += 1;
          break;
          mSavedFragmentState = mSavedFragmentState;
        }
      }
      if (j != 0) {
        break;
      }
    } while (!DEBUG);
    Log.v("FragmentManager", "saveAllState: no fragments!");
    return null;
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject1 = localObject2;
    if (mAdded != null)
    {
      j = mAdded.size();
      localObject1 = localObject2;
      if (j > 0)
      {
        localObject2 = new int[j];
        i = 0;
        for (;;)
        {
          localObject1 = localObject2;
          if (i >= j) {
            break;
          }
          localObject2[i] = mAdded.get(i)).mIndex;
          if (localObject2[i] < 0) {
            throwException(new IllegalStateException("Failure saving state: active " + mAdded.get(i) + " has cleared index: " + localObject2[i]));
          }
          if (DEBUG) {
            Log.v("FragmentManager", "saveAllState: adding fragment #" + i + ": " + mAdded.get(i));
          }
          i += 1;
        }
      }
    }
    localObject2 = localObject3;
    if (mBackStack != null)
    {
      j = mBackStack.size();
      localObject2 = localObject3;
      if (j > 0)
      {
        localObject3 = new BackStackState[j];
        i = 0;
        for (;;)
        {
          localObject2 = localObject3;
          if (i >= j) {
            break;
          }
          localObject3[i] = new BackStackState(this, (BackStackRecord)mBackStack.get(i));
          if (DEBUG) {
            Log.v("FragmentManager", "saveAllState: adding back stack #" + i + ": " + mBackStack.get(i));
          }
          i += 1;
        }
      }
    }
    localObject3 = new FragmentManagerState();
    mActive = arrayOfFragmentState;
    mAdded = ((int[])localObject1);
    mBackStack = ((BackStackState[])localObject2);
    return (Parcelable)localObject3;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment)
  {
    Object localObject2 = null;
    if (mStateBundle == null) {
      mStateBundle = new Bundle();
    }
    paramFragment.performSaveInstanceState(mStateBundle);
    if (!mStateBundle.isEmpty())
    {
      localObject2 = mStateBundle;
      mStateBundle = null;
    }
    if (mView != null) {
      saveFragmentViewState(paramFragment);
    }
    Object localObject1 = localObject2;
    if (mSavedViewState != null)
    {
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Bundle();
      }
      ((Bundle)localObject1).putSparseParcelableArray("android:view_state", mSavedViewState);
    }
    localObject2 = localObject1;
    if (!mUserVisibleHint)
    {
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new Bundle();
      }
      ((Bundle)localObject2).putBoolean("android:user_visible_hint", mUserVisibleHint);
    }
    return (Bundle)localObject2;
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment)
  {
    Object localObject2 = null;
    if (mIndex < 0) {
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager"));
    }
    Object localObject1 = localObject2;
    if (mState > 0)
    {
      paramFragment = saveFragmentBasicState(paramFragment);
      localObject1 = localObject2;
      if (paramFragment != null) {
        localObject1 = new Fragment.SavedState(paramFragment);
      }
    }
    return (Fragment.SavedState)localObject1;
  }
  
  void saveFragmentViewState(Fragment paramFragment)
  {
    if (mInnerView == null) {
      return;
    }
    if (mStateArray == null) {
      mStateArray = new SparseArray();
    }
    for (;;)
    {
      mInnerView.saveHierarchyState(mStateArray);
      if (mStateArray.size() <= 0) {
        break;
      }
      mSavedViewState = mStateArray;
      mStateArray = null;
      return;
      mStateArray.clear();
    }
  }
  
  /* Error */
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 313	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   6: ifnonnull +14 -> 20
    //   9: aload_0
    //   10: new 249	java/util/ArrayList
    //   13: dup
    //   14: invokespecial 250	java/util/ArrayList:<init>	()V
    //   17: putfield 313	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   20: aload_0
    //   21: getfield 313	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   24: invokevirtual 311	java/util/ArrayList:size	()I
    //   27: istore 4
    //   29: iload 4
    //   31: istore_3
    //   32: iload_1
    //   33: iload 4
    //   35: if_icmpge +58 -> 93
    //   38: getstatic 95	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   41: ifeq +39 -> 80
    //   44: ldc 42
    //   46: new 149	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   53: ldc_w 315
    //   56: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: iload_1
    //   60: invokevirtual 318	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   63: ldc_w 320
    //   66: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_2
    //   70: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: invokestatic 269	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   79: pop
    //   80: aload_0
    //   81: getfield 313	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   84: iload_1
    //   85: aload_2
    //   86: invokevirtual 337	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
    //   89: pop
    //   90: aload_0
    //   91: monitorexit
    //   92: return
    //   93: iload_3
    //   94: iload_1
    //   95: if_icmpge +81 -> 176
    //   98: aload_0
    //   99: getfield 313	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   102: aconst_null
    //   103: invokevirtual 254	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   106: pop
    //   107: aload_0
    //   108: getfield 307	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   111: ifnonnull +14 -> 125
    //   114: aload_0
    //   115: new 249	java/util/ArrayList
    //   118: dup
    //   119: invokespecial 250	java/util/ArrayList:<init>	()V
    //   122: putfield 307	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   125: getstatic 95	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   128: ifeq +29 -> 157
    //   131: ldc 42
    //   133: new 149	java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   140: ldc_w 1130
    //   143: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: iload_3
    //   147: invokevirtual 318	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   150: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 269	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: aload_0
    //   158: getfield 307	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   161: iload_3
    //   162: invokestatic 598	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   165: invokevirtual 254	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   168: pop
    //   169: iload_3
    //   170: iconst_1
    //   171: iadd
    //   172: istore_3
    //   173: goto -80 -> 93
    //   176: getstatic 95	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   179: ifeq +39 -> 218
    //   182: ldc 42
    //   184: new 149	java/lang/StringBuilder
    //   187: dup
    //   188: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   191: ldc_w 331
    //   194: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: iload_1
    //   198: invokevirtual 318	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   201: ldc_w 333
    //   204: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_2
    //   208: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   211: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 269	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   217: pop
    //   218: aload_0
    //   219: getfield 313	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   222: aload_2
    //   223: invokevirtual 254	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   226: pop
    //   227: goto -137 -> 90
    //   230: astore_2
    //   231: aload_0
    //   232: monitorexit
    //   233: aload_2
    //   234: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	235	0	this	FragmentManagerImpl
    //   0	235	1	paramInt	int
    //   0	235	2	paramBackStackRecord	BackStackRecord
    //   31	142	3	i	int
    //   27	9	4	j	int
    // Exception table:
    //   from	to	target	type
    //   2	20	230	finally
    //   20	29	230	finally
    //   38	80	230	finally
    //   80	90	230	finally
    //   90	92	230	finally
    //   98	125	230	finally
    //   125	157	230	finally
    //   157	169	230	finally
    //   176	218	230	finally
    //   218	227	230	finally
    //   231	233	230	finally
  }
  
  public void showFragment(Fragment paramFragment, int paramInt1, int paramInt2)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "show: " + paramFragment);
    }
    if (mHidden)
    {
      mHidden = false;
      if (mView != null)
      {
        Animation localAnimation = loadAnimation(paramFragment, paramInt1, true, paramInt2);
        if (localAnimation != null) {
          mView.startAnimation(localAnimation);
        }
        mView.setVisibility(0);
      }
      if ((mAdded) && (mHasMenu) && (mMenuVisible)) {
        mNeedMenuInvalidate = true;
      }
      paramFragment.onHiddenChanged(false);
    }
  }
  
  void startPendingDeferredFragments()
  {
    if (mActive == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < mActive.size())
      {
        Fragment localFragment = (Fragment)mActive.get(i);
        if (localFragment != null) {
          performPendingDeferredStart(localFragment);
        }
        i += 1;
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("FragmentManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    if (mParent != null) {
      DebugUtils.buildShortClassTag(mParent, localStringBuilder);
    }
    for (;;)
    {
      localStringBuilder.append("}}");
      return localStringBuilder.toString();
      DebugUtils.buildShortClassTag(mActivity, localStringBuilder);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentManagerImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.inmobi.re.configs;

import android.content.Context;
import com.inmobi.commons.InMobi;
import com.inmobi.commons.cache.CacheController;
import com.inmobi.commons.cache.CacheController.Validator;
import com.inmobi.commons.cache.ProductConfig;
import com.inmobi.commons.internal.InternalSDKUtil;
import com.inmobi.commons.internal.Log;
import com.inmobi.commons.uid.UIDUtil;
import java.util.HashMap;
import java.util.Map;

public class Initializer
{
  public static final String PRODUCT_MRAID = "mraid";
  public static final String PRODUCT_RENDERING = "rendering";
  private static Context a = null;
  private static Map<String, String> b = new HashMap();
  private static ConfigParams c = new ConfigParams();
  private static CacheController.Validator d = new a();
  
  private static void a(Context paramContext)
  {
    if ((paramContext != null) && (a == null)) {
      if (a == null) {}
    }
    while ((a != null) || (paramContext != null))
    {
      return;
      a = paramContext;
      c(paramContext);
      try
      {
        a(CacheController.getConfig("rendering", paramContext, b, d).getData());
        return;
      }
      catch (Exception paramContext)
      {
        return;
      }
    }
    throw new NullPointerException();
  }
  
  static boolean a(Map<String, Object> paramMap)
  {
    c(a);
    try
    {
      paramMap = InternalSDKUtil.populateToNewMap((Map)paramMap.get("AND"), (Map)paramMap.get("common"), true);
      ConfigParams localConfigParams = new ConfigParams();
      localConfigParams.setFromMap(paramMap);
      c = localConfigParams;
      return true;
    }
    catch (Exception paramMap)
    {
      Log.debug("[InMobi]-[RE]-4.4.1", "Config couldn't be parsed", paramMap);
    }
    return false;
  }
  
  private static void b(Context paramContext)
  {
    a(paramContext);
    try
    {
      CacheController.getConfig("rendering", paramContext, b, d);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  private static void c(Context paramContext)
  {
    b = UIDUtil.getMap(a, c.getUID());
  }
  
  public static ConfigParams getConfigParams()
  {
    if ((InternalSDKUtil.getContext() != null) && (InMobi.getAppId() != null)) {
      b(InternalSDKUtil.getContext());
    }
    return c;
  }
  
  public static String getMRAIDString()
  {
    try
    {
      String str = CacheController.getConfig("mraid", a, b, new Initializer.a()).getRawData();
      if ((str != null) && (str != "")) {
        return str;
      }
    }
    catch (Exception localException) {}
    return "var imIsObjValid=function(a){return\"undefined\"!=typeof a&&null!=a?!0:!1},imIsObjInvalid=function(a){return\"undefined\"==typeof a||null==a?!0:!1},EventListeners=function(a){this.event=a;this.count=0;var b={};this.add=function(a){var f=String(a);b[f]||(b[f]=a,this.count++)};this.remove=function(a){a=String(a);return b[a]?(b[a]=null,delete b[a],this.count--,!0):!1};this.removeAll=function(){for(var a in b)this.remove(b[a])};this.broadcast=function(a){for(var f in b)b[f].apply({},a)};this.toString=function(){var c=[a,\":\"],f;for(f in b)c.push(\"|\",f,\"|\");return c.join(\"\")}},InmobiObj=function(){this.listeners=[];this.addEventListener=function(a,b){try{if(imIsObjValid(b)&&imIsObjValid(a)){var c=this.listeners;c[a]||(c[a]=new EventListeners);c[a].add(b);\"micIntensityChange\"==a&&window.mraidview.startListeningMicIntensity()}}catch(f){this.log(f)}};this.removeEventListener=function(a,b){if(imIsObjValid(a)){var c=this.listeners;imIsObjValid(c[a])&&(imIsObjValid(b)?c[a].remove(b):c[a].removeAll());\"micIntensityChange\"==a&&0==c[a].count&&window.mraidview.stopListeningMicIntensity()}};this.broadcastEvent=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)b[c]=arguments[c];c=b.shift();try{this.listeners[c]&&this.listeners[c].broadcast(b)}catch(f){}}};this.sendSaveContentResult=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)if(2==c){var f=JSON.parse(arguments[c]);b[c]=f}else b[c]=arguments[c];window.mraid.broadcastEvent(b[0],b[1],b[2])}}},__im__iosNativeCall={nativeCallInFlight:!1,nativeCallQueue:[],executeNativeCall:function(a){this.nativeCallInFlight?this.nativeCallQueue.push(a):(this.nativeCallInFlight=!0,window.location=a)},nativeCallComplete:function(a){0==this.nativeCallQueue.length?this.nativeCallInFlight=!1:(a=this.nativeCallQueue.shift(),window.location=a)}},IOSNativeCall=function(){this.urlScheme=\"\";this.executeNativeCall=function(a){for(var b=this.urlScheme+\"://\"+a,c,f=!0,g=1;g<arguments.length;g+=2)c=arguments[g+1],null!=c&&(f?(b+=\"?\",f=!1):b+=\"&\",b+=arguments[g]+\"=\"+escape(c));__im__iosNativeCall.executeNativeCall(b);return\"OK\"};this.nativeCallComplete=function(a){__im__iosNativeCall.nativeCallComplete(a);return\"OK\"}};(function(){var a=window.mraidview={},b={allowOrientationChange:!0,forceOrientation:\"none\"},c=[],f=!1;a.fireGalleryImageSelectedEvent=function(a,e,b){var c=new Image;c.src=\"data:image/jpeg;base64,\"+a;c.width=e;c.height=b;window.mraid.broadcastEvent(\"galleryImageSelected\",c);return\"OK\"};a.fireCameraPictureCatpturedEvent=function(a,e,b){var c=new Image;c.src=\"data:image/jpeg;base64,\"+a;c.width=e;c.height=b;window.mraid.broadcastEvent(\"cameraPictureCaptured\",c);return\"OK\"};a.firePostStatusEvent=function(a){window.mraid.broadcastEvent(\"postStatus\",a);return\"OK\"};a.fireMediaTrackingEvent=function(a,e){var b={};b.name=a;var c=\"inmobi_media_\"+a;\"undefined\"!=typeof e&&(null!=e&&\"\"!=e)&&(c=c+\"_\"+e);window.mraid.broadcastEvent(c,b);return\"OK\"};a.fireMediaErrorEvent=function(a,e){var b={name:\"error\"};b.code=e;var c=\"inmobi_media_\"+b.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(c=c+\"_\"+a);window.mraid.broadcastEvent(c,b);return\"OK\"};a.fireMediaTimeUpdateEvent=function(a,e,b){var c={name:\"timeupdate\",target:{}};c.target.currentTime=e;c.target.duration=b;e=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(e=e+\"_\"+a);window.mraid.broadcastEvent(e,c);return\"OK\"};a.fireMediaCloseEvent=function(a,e,b){var c={name:\"close\"};c.viaUserInteraction=e;c.target={};c.target.currentTime=b;e=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(e=e+\"_\"+a);window.mraid.broadcastEvent(e,c);return\"OK\"};a.fireMediaVolumeChangeEvent=function(a,e,b){var c={name:\"volumechange\",target:{}};c.target.volume=e;c.target.muted=b;e=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(e=e+\"_\"+a);window.mraid.broadcastEvent(e,c);return\"OK\"};a.showAlert=function(a){utilityController.showAlert(a)};a.zeroPad=function(a){var e=\"\";10>a&&(e+=\"0\");return e+a};a.useCustomClose=function(d){try{displayController.useCustomClose(d)}catch(e){a.showAlert(\"use CustomClose: \"+e)}};a.close=function(){try{displayController.close()}catch(d){a.showAlert(\"close: \"+d)}};a.stackCommands=function(a,e){f?c.push(a):(eval(a),e&&(f=!0))};a.executeStack=function(){for(f=!1;0<c.length;){var a=c.shift();eval(a)}};a.emptyStack=function(){for(;0<c.length;)c.shift()};a.expand=function(d){try{displayController.expand(d)}catch(e){a.showAlert(\"executeNativeExpand: \"+e+\", URL = \"+d)}};a.setExpandProperties=function(d){try{d?this.props=d:d=null,displayController.setExpandProperties(a.stringify(d))}catch(e){a.showAlert(\"executeNativesetExpandProperties: \"+e+\", props = \"+d)}};a.getExpandProperties=function(){try{return eval(\"(\"+displayController.getExpandProperties()+\")\")}catch(d){a.showAlert(\"getExpandProperties: \"+d)}};a.setOrientationProperties=function(d){try{d?b=d:d=null,displayController.setOrientationProperties(a.stringify(d))}catch(e){a.showAlert(\"executeNativesetOrientationProperties: \"+e+\", props = \"+d)}};a.getOrientationProperties=function(){return b};a.resizeProps=null;a.setResizeProperties=function(d){var e,b;try{e=parseInt(d.width);b=parseInt(d.height);if(isNaN(e)||isNaN(b)||1>e||1>b)throw\"Invalid\";d.width=e;d.height=b;a.resizeProps=d;displayController.setResizeProperties(a.stringify(d))}catch(c){window.mraid.broadcastEvent(\"error\",\"Invalid properties.\",\"setResizeProperties\")}};a.getResizeProperties=function(){try{return eval(\"(\"+displayController.getResizeProperties()+\")\")}catch(d){a.showAlert(\"getResizeProperties: \"+d)}};a.acceptAction=function(d){try{displayController.acceptAction(a.stringify(d))}catch(e){a.showAlert(\"acceptAction: \"+e+\", params = \"+d)}};a.rejectAction=function(d){try{displayController.rejectAction(a.stringify(d))}catch(e){a.showAlert(\"rejectAction: \"+e+\", params = \"+d)}};a.open=function(d){\"undefined\"==typeof d&&(d=null);try{displayController.open(d)}catch(e){a.showAlert(\"open: \"+e)}};a.openExternal=function(d){try{utilityController.openExternal(d)}catch(e){a.showAlert(\"openExternal: \"+e)}};a.getScreenSize=function(){try{return eval(\"(\"+utilityController.getScreenSize()+\")\")}catch(d){a.showAlert(\"getScreenSize: \"+d)}};a.getMaxSize=function(){try{return eval(\"(\"+utilityController.getMaxSize()+\")\")}catch(d){a.showAlert(\"getMaxSize: \"+d)}};a.getCurrentPosition=function(){try{return eval(\"(\"+utilityController.getCurrentPosition()+\")\")}catch(d){a.showAlert(\"getCurrentPosition: \"+d)}};a.getDefaultPosition=function(){try{return eval(\"(\"+utilityController.getDefaultPosition()+\")\")}catch(d){a.showAlert(\"getDefaultPosition: \"+d)}};a.getState=function(){try{return String(displayController.getState())}catch(d){a.showAlert(\"getState: \"+d)}};a.getOrientation=function(){try{return String(displayController.getOrientation())}catch(d){a.showAlert(\"getOrientation: \"+d)}};a.isViewable=function(){try{return displayController.isViewable()}catch(d){a.showAlert(\"isViewable: \"+d)}};a.log=function(d){try{utilityController.log(d)}catch(e){a.showAlert(\"log: \"+e)}};a.getPlatform=function(){return\"android\"};a.getPlacementType=function(){return displayController.getPlacementType()};a.asyncPing=function(d){try{utilityController.asyncPing(d)}catch(e){a.showAlert(\"asyncPing: \"+e)}};a.close=function(){try{displayController.close()}catch(d){a.showAlert(\"close: \"+d)}};a.makeCall=function(d){try{d.startsWith(\"tel:\")?utilityController.openExternal(d):utilityController.openExternal(\"tel:\"+d)}catch(e){a.showAlert(\"makeCall: \"+e)}};\"function\"!=typeof String.prototype.startsWith&&(String.prototype.startsWith=function(a){return 0==this.indexOf(a)});a.sendMail=function(d,e,b){try{utilityController.sendMail(d,e,b)}catch(c){a.showAlert(\"sendMail: \"+c)}};a.sendSMS=function(d,e){try{utilityController.sendSMS(d,e)}catch(b){a.showAlert(\"sendSMS: \"+b)}};a.pauseAudio=function(d){try{var e=getPID(d);utilityController.pauseAudio(e)}catch(b){a.showAlert(\"pauseAudio: \"+b)}};a.muteAudio=function(d){try{var e=getPID(d);utilityController.muteAudio(e)}catch(b){a.showAlert(\"muteAudio: \"+b)}};a.unMuteAudio=function(d){try{var e=getPID(d);utilityController.unMuteAudio(e)}catch(b){a.showAlert(\"unMuteAudio: \"+b)}};a.isAudioMuted=function(d){try{var e=getPID(d);return utilityController.isAudioMuted(e)}catch(b){a.showAlert(\"isAudioMuted: \"+b)}};a.setAudioVolume=function(d,e){try{var b=getPID(d);e=parseInt(e);utilityController.setAudioVolume(b,e)}catch(c){a.showAlert(\"setAudioVolume: \"+c)}};a.getAudioVolume=function(d){try{var e=getPID(d);return utilityController.getAudioVolume(e)}catch(b){a.showAlert(\"getAudioVolume: \"+b)}};a.seekAudio=function(d,e){try{var b=getPID(d);e=parseInt(e);utilityController.seekAudio(b,e)}catch(c){a.showAlert(\"seekAudio: \"+c)}};a.playAudio=function(d,e){var b=!0,c=!1,f=\"normal\",g=\"normal\",k=!0,h=\"\",q=getPID(e);null!=d&&(h=d);null!=e&&(\"undefined\"!=typeof e.autoplay&&!1===e.autoplay&&(b=!1),\"undefined\"!=typeof e.loop&&!0===e.loop&&(c=!0),\"undefined\"!=typeof e.startStyle&&null!=e.startStyle&&(f=e.startStyle),\"undefined\"!=typeof e.stopStyle&&null!=e.stopStyle&&(g=e.stopStyle),\"fullscreen\"==f&&(k=!0));try{utilityController.playAudio(h,b,k,c,f,g,q)}catch(r){a.showAlert(\"playAudio: \"+r)}};a.pauseVideo=function(d){try{var e=getPID(d);utilityController.pauseVideo(e)}catch(b){a.showAlert(\"pauseVideo: \"+b)}};a.closeVideo=function(d){try{var e=getPID(d);utilityController.closeVideo(e)}catch(b){a.showAlert(\"closeVideo: \"+b)}};a.hideVideo=function(d){try{var b=getPID(d);utilityController.hideVideo(b)}catch(c){a.showAlert(\"hideVideo: \"+c)}};a.showVideo=function(d){try{var b=getPID(d);utilityController.showVideo(b)}catch(c){a.showAlert(\"showVideo: \"+c)}};a.muteVideo=function(d){try{var b=getPID(d);utilityController.muteVideo(b)}catch(c){a.showAlert(\"muteVideo: \"+c)}};a.unMuteVideo=function(d){try{var b=getPID(d);utilityController.unMuteVideo(b)}catch(c){a.showAlert(\"unMuteVideo: \"+c)}};a.seekVideo=function(d,b){try{var c=getPID(d);b=parseInt(b);utilityController.seekVideo(c,b)}catch(f){a.showAlert(\"seekVideo: \"+f)}};a.isVideoMuted=function(d){try{var b=getPID(d);return utilityController.isVideoMuted(b)}catch(c){a.showAlert(\"isVideoMuted: \"+c)}};a.setVideoVolume=function(d,b){try{var c=getPID(d);b=parseInt(b);utilityController.setVideoVolume(c,b)}catch(f){a.showAlert(\"setVideoVolume: \"+f)}};a.getVideoVolume=function(d){try{var b=getPID(d);return utilityController.getVideoVolume(b)}catch(c){a.showAlert(\"getVideoVolume: \"+c)}};a.playVideo=function(d,b){var c=!1,f=!0,g=!0,k=!1,h=-99999,s=-99999,q=-99999,r=-99999,t=\"normal\",u=\"exit\",v=\"\",w=getPID(b);null!=d&&(v=d);null!=b&&(\"undefined\"!=typeof b.audio&&\"muted\"==b.audio&&(c=!0),\"undefined\"!=typeof b.autoplay&&!1===b.autoplay&&(f=!1),\"undefined\"!=typeof b.controls&&!1===b.controls&&(g=!1),\"undefined\"!=typeof b.loop&&!0===b.loop&&(k=!0),\"undefined\"!=typeof b.inline&&null!=b.inline&&(h=b.inline.left,s=b.inline.top),\"undefined\"!=typeof b.width&&null!=b.width&&(q=b.width),\"undefined\"!=typeof b.height&&null!=b.height&&(r=b.height),\"undefined\"!=typeof b.startStyle&&null!=b.startStyle&&(t=b.startStyle),\"undefined\"!=typeof b.stopStyle&&null!=b.stopStyle&&(u=b.stopStyle),\"fullscreen\"==t&&(g=!0),h=parseInt(h).toString(),s=parseInt(s).toString(),q=parseInt(q).toString(),r=parseInt(r).toString());try{utilityController.playVideo(v,c,f,g,k,h,s,q,r,t,u,w)}catch(x){a.showAlert(\"playVideo: \"+x)}};a.updateToPassbook=function(b){window.mraid.broadcastEvent(\"error\",\"Method not supported\",\"updateToPassbook\");a.log(\"Method not supported\")};a.stringify=function(b){if(\"undefined\"===typeof JSON){var c=\"\",f;if(\"undefined\"==typeof b.length)return a.stringifyArg(b);for(f=0;f<b.length;f++)0<f&&(c+=\",\"),c+=a.stringifyArg(b[f]);return c+\"]\"}return JSON.stringify(b)};a.stringifyArg=function(b){var c,f,g;f=typeof b;c=\"\";if(\"number\"===f||\"boolean\"===f)c+=args;else if(b instanceof Array)c=c+\"[\"+b+\"]\";else if(b instanceof Object){f=!0;c+=\"{\";for(g in b)null!==b[g]&&(f||(c+=\",\"),c=c+'\"'+g+'\":',f=typeof b[g],c=\"number\"===f||\"boolean\"===f?c+b[g]:\"function\"===typeof b[g]?c+'\"\"':b[g]instanceof Object?c+this.stringify(args[i][g]):c+'\"'+b[g]+'\"',f=!1);c+=\"}\"}else b=b.replace(/\\/g,\"\\\\\"),b=b.replace(/\"/g,'\\\"'),c=c+'\"'+b+'\"';a.showAlert(\"json:\"+c);return c};getPID=function(a){var b=\"\";null!=a&&(\"undefined\"!=typeof a.id&&null!=a.id)&&(b=a.id);return b};var g,k=!0;a.getWindowOrientation=function(){var a=window.orientation;0>a&&(a+=360);return a};var h=function(){if(k||a.getWindowOrientation()!==g)k=!1,g=a.getWindowOrientation(),displayController.onOrientationChange(),mraid.broadcastEvent(\"orientationChange\",g)};a.registerOrientationListener=function(){g=a.getWindowOrientation();window.addEventListener(\"resize\",h,!1);window.addEventListener(\"orientationchange\",h,!1)};a.unRegisterOrientationListener=function(){window.removeEventListener(\"resize\",h,!1);window.removeEventListener(\"orientationchange\",h,!1)};a.startListeningMicIntensity=function(){utilityController.registerMicListener()};a.stopListeningMicIntensity=function(){utilityController.unRegisterMicListener()};getSdkVersionInt=function(){for(var b=a.getSdkVersion().split(\".\"),c=b.length,f=\"\",g=0;g<c;g++)f+=b[g];return parseInt(f)};a.resize=function(){if(null==a.resizeProps)window.mraid.broadcastEvent(\"error\",\"Valid resize dimensions must be provided before calling resize\",\"resize\");else try{displayController.resize()}catch(b){a.showAlert(\"resize called in bridge\")}};a.vibrate=function(b){null==b||420>getSdkVersionInt()?utilityController.vibrate():\"number\"==typeof b?0==b?utilityController.vibrate(\"[]\",-1):utilityController.vibrate(\"[0,\"+b+\"]\",-1):\"[object Array]\"===Object.prototype.toString.call(b)?0==b.length?utilityController.vibrate(\"[]\",-1):utilityController.vibrate(\"[0,\"+String(b)+\"]\",-1):a.showAlert(\"Invalid params in vibrate\")};a.takeCameraPicture=function(){utilityController.takeCameraPicture()};a.getGalleryImage=function(){return utilityController.getGalleryImage()};a.getSdkVersion=function(){return window._im_imaiBridge.getSdkVersion()};a.createCalendarEvent=function(b){var c={};\"object\"!=typeof b&&window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects parameter\",\"createCalendarEvent\");if(\"string\"!=typeof b.start||\"string\"!=typeof b.end)window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects string parameters for start and end dates\",\"createCalendarEvent\");else{\"string\"!=typeof b.id&&(b.id=\"\");\"string\"!=typeof b.location&&(b.location=\"\");\"string\"!=typeof b.description&&(b.description=\"\");\"string\"!=typeof b.summary&&(b.summary=\"\");\"string\"==typeof b.status&&(\"pending\"==b.status||\"tentative\"==b.status||\"confirmed\"==b.status||\"cancelled\"==b.status)||(b.status=\"\");\"string\"==typeof b.transparency&&(\"opaque\"==b.transparency||\"transparent\"==b.transparency)||(b.transparency=\"\");if(null==b.recurrence||\"\"==b.recurrence)c={};else{\"string\"==typeof b.summary&&(c.frequency=b.recurrence.frequency);null!=b.recurrence.interval&&(c.interval=b.recurrence.interval);\"string\"==typeof b.summary&&(c.expires=b.recurrence.expires);if(null!=b.recurrence.daysInWeek){var f=formatDaysInWeek(b.recurrence.daysInWeek);null!=f?c.daysInWeek=f:a.showAlert(\"daysInWeek invalid format \")}c.daysInMonth=b.recurrence.daysInMonth;c.daysInYear=b.recurrence.daysInYear;c.weeksInMonth=b.recurrence.weeksInMonth;c.monthsInYear=b.recurrence.monthsInYear}\"string\"!=typeof b.reminder&&(b.reminder=\"\");try{utilityController.createCalendarEvent(b.id,b.start,b.end,b.location,b.description,b.summary,b.status,b.transparency,JSON.stringify(c),b.reminder)}catch(g){utilityController.createCalendarEvent(b.start,b.end,b.location,b.description)}}};formatDaysInWeek=function(b){try{if(0!=b.length){for(var a=0;a<b.length;a++)switch(b[a]){case 0:b[a]=\"SU\";break;case 1:b[a]=\"MO\";break;case 2:b[a]=\"TU\";break;case 3:b[a]=\"WE\";break;case 4:b[a]=\"TH\";break;case 5:b[a]=\"FR\";break;case 6:b[a]=\"SA\";break;default:return null}return b}}catch(c){}return null};a.supports=function(b){console.log(\"bridge: supports\");if(\"string\"!=typeof b)window.mraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=utilityController.supportsFeature(b)};a.postToSocial=function(b,a,c,f){b=parseInt(b);isNaN(b)&&window.mraid.broadcastEvent(\"error\",\"socialType must be an integer\",\"postToSocial\");\"string\"!=typeof a&&(a=\"\");\"string\"!=typeof c&&(c=\"\");\"string\"!=typeof f&&(f=\"\");utilityController.postToSocial(b,a,c,f)};a.storePicture=function(b){console.log(\"bridge: storePicture\");if(\"string\"!=typeof b)window.mraid.broadcastEvent(\"error\",\"storePicture method expects url as string parameter\",\"storePicture\");else{if(a.supports(\"storePicture\"))return!window.confirm(\"Do you want to download the file?\")?(window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled by user.\",\"storePicture\"),!1):utilityController.storePicture(b);window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled because it is unsupported in this device/app.\",\"storePicture\")}};a.getMicIntensity=function(){return utilityController.getMicIntensity()};a.incentCompleted=function(b){b instanceof Object?utilityController.incentCompleted(JSON.stringify(b)):utilityController.incentCompleted(\"\")};window.mraidview.registerOrientationListener()})();(function(){var a=window.mraid=new InmobiObj,b=window.mraidview;a.getSdkVersion=b.getSdkVersion;a.useCustomClose=b.useCustomClose;a.close=b.close;a.getExpandProperties=b.getExpandProperties;a.setExpandProperties=b.setExpandProperties;a.getResizeProperties=b.getResizeProperties;a.setResizeProperties=b.setResizeProperties;a.getOrientationProperties=b.getOrientationProperties;a.setOrientationProperties=b.setOrientationProperties;a.expand=b.expand;a.getMaxSize=b.getMaxSize;a.getState=b.getState;a.getOrientation=b.getOrientation;a.isViewable=b.isViewable;a.createCalendarEvent=b.createCalendarEvent;a.open=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"open\"):b.open(c)};a.resize=b.resize;a.log=function(c){\"undefined\"==typeof c?a.broadcastEvent(\"error\",\"message is required.\",\"log\"):\"string\"==typeof c?b.log(c):b.log(JSON.stringify(c))};a.getVersion=function(){return\"2.0\"};a.getInMobiAIVersion=function(){return\"2.0\"};a.getPlacementType=b.getPlacementType;a.asyncPing=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"asyncPing\"):b.asyncPing(c)};a.makeCall=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must provide a number to call.\",\"makeCall\"):b.makeCall(c)};a.sendMail=function(c,f,g){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a recipient.\",\"sendMail\"):b.sendMail(c,f,g)};a.sendSMS=function(c,f){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a recipient.\",\"sendSMS\"):b.sendSMS(c,f)};a.playAudio=function(a,f){\"object\"!=typeof f?\"string\"==typeof a?b.playAudio(a,null):\"object\"==typeof a?b.playAudio(null,a):b.playAudio(null,null):b.playAudio(a,f)};a.playVideo=function(a){null==a?window.mraid.broadcastEvent(\"error\",\"Parameters incorrect\",\"playVideo\"):b.playVideo(a)};a.playVideo=function(a,f){\"object\"!=typeof f?\"string\"==typeof a?b.playVideo(a,null):\"object\"==typeof a?b.playVideo(null,a):b.playVideo(null,null):b.playVideo(a,f)};a.getGalleryImage=b.getGalleryImage;a.pauseAudio=b.pauseAudio;a.muteAudio=b.muteAudio;a.unMuteAudio=b.unMuteAudio;a.isAudioMuted=b.isAudioMuted;a.setAudioVolume=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var f=c.volume;isNaN(f)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setAudioVolume\"):(0>f?f=0:100<f&&(f=100),b.setAudioVolume(c,f))}};a.getAudioVolume=b.getAudioVolume;a.pauseVideo=b.pauseVideo;a.closeVideo=b.closeVideo;a.hideVideo=b.hideVideo;a.showVideo=b.showVideo;a.muteVideo=b.muteVideo;a.unMuteVideo=b.unMuteVideo;a.isVideoMuted=b.isVideoMuted;a.setVideoVolume=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var f=c.volume;isNaN(f)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setVideoVolume\"):(0>f?f=0:100<f&&(f=100),b.setVideoVolume(c,f))}};a.getVideoVolume=b.getVideoVolume;a.seekAudio=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"seekAudio\");else{var f=c.time;imIsObjInvalid(f)?a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekAudio\"):0!=f?a.broadcastEvent(\"error\",\"Cannot seek audio other than 0\",\"seekAudio\"):b.seekAudio(c,f)}};a.seekVideo=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\");else{var f=c.time;imIsObjInvalid(f)?a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\"):0!=f?a.broadcastEvent(\"error\",\"Cannot seek video other than 0\",\"seekVideo\"):b.seekVideo(c,f)}};a.openExternal=b.openExternal;a.getScreenSize=b.getScreenSize;a.getCurrentPosition=b.getCurrentPosition;a.getDefaultPosition=b.getDefaultPosition;a.updateToPassbook=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"updateToPassbook\"):b.updateToPassbook(c)};a.acceptAction=b.acceptAction;a.rejectAction=b.rejectAction;a.storePicture=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"storePicture\"):b.storePicture(c)};a.vibrate=b.vibrate;a.takeCameraPicture=b.takeCameraPicture;a.getMicIntensity=function(){return imIsObjInvalid(a.listeners.micIntensityChange)?-1:b.getMicIntensity()};a.postToSocial=b.postToSocial;a.supports=function(a){var f=!1;\"inlineVideo\"==a?(f=b.supports(a),!1==f&&(f=b.supports(\"html5video\"))):f=b.supports(a);return f};a.getPlatform=b.getPlatform;a.incentCompleted=b.incentCompleted;a.loadSKStore=b.loadSKStore;a.showSKStore=b.showSKStore})();(function(){var a=window.imraidview={};a.saveContent=function(b,a,f){var g=getSdkVersionInt();window.mraid.addEventListener(\"saveContent_\"+b,f);440>g?window.mraid.sendSaveContentResult(\"saveContent_\"+b,\"fail\",JSON.stringify({url:a,reason:5})):utilityController.saveContent(b,a)};a.cancelSaveContent=function(a){utilityController.cancelSaveContent(a)}})();(function(){var a=window.imraid=new InmobiObj,b=window.imraidview;a.saveContent=function(a,f,g){var k=arguments.length;3>k?\"function\"===typeof arguments[k-1]&&(k=arguments[k-1],window.mraid.addEventListener(\"saveContent\",k),window.mraid.sendSaveContentResult(\"saveContent\",\"fail\",JSON.stringify({reason:1}))):b.saveContent(a,f,g)};a.cancelSaveContent=function(a){b.cancelSaveContent(a)}})();(function(){var a=window._im_imaiBridge={ios:{}};a.broadcastEvent=function(){for(var a=Array(arguments.length),c=0;c<arguments.length;c++)a[c]=arguments[c];c=a.shift();try{window.mraid.broadcastEvent(c,a)}catch(f){}};a.getPlatform=function(){return\"android\"};a.getPlatformVersion=function(){return imaiController.getPlatformVersion()};a.log=function(a){imaiController.log(a)};a.openEmbedded=function(a){imaiController.openEmbedded(a)};a.openExternal=function(a){imaiController.openExternal(a)};a.ping=function(a,c){imaiController.ping(a,c)};a.pingInWebView=function(a,c){imaiController.pingInWebView(a,c)};a.getSdkVersion=function(){try{var a=imaiController.getSdkVersion();if(\"string\"==typeof a&&null!=a)return a}catch(c){return\"3.7.0\"}};a.onUserInteraction=function(a){if(\"object\"!=typeof a||null==a)utilityController.onUserInteraction(\"{}\");else try{utilityController.onUserInteraction(JSON.stringify(a))}catch(c){utilityController.onUserInteraction(\"{}\")}};a.ios.openItunesProductView=function(a){this.broadcastEvent(\"error\",\"functionality not supported\",\"openItunesProductView\")};a.ios.updateToPassbook=function(a){this.broadcastEvent(\"error\",\"functionality not supported\",\"updateToPassbook\")}})();(function(){var a=window._im_imaiBridge;window._im_imai=new InmobiObj;window._im_imai.ios=new InmobiObj;var b=window._im_imai;b.matchString=function(a,b){if(\"string\"!=typeof a||null==a||null==b)return-1;var g=-1;try{g=a.indexOf(b)}catch(k){}return g};b.isHttpUrl=function(a){return\"string\"!=typeof a||null==a?!1:0==b.matchString(a,\"http://\")?!0:0==b.matchString(a,\"https://\")?!0:!1};b.appendTapParams=function(a,f,g){if(imIsObjInvalid(f)||imIsObjInvalid(g))return a;b.isHttpUrl(a)&&(a=-1==b.matchString(a,\"?\")?a+(\"?u-tap-o=\"+f+\",\"+g):a+(\"&u-tap-o=\"+f+\",\"+g));return a};b.performAdClick=function(a,f){f=f||event;if(imIsObjInvalid(a))b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\");else{var g=a.clickConfig,k=a.landingConfig;if(imIsObjInvalid(g)&&imIsObjInvalid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,d=null,e=null,m=null,n=null,l=null,p=null;if(imIsObjValid(f))try{m=f.changedTouches[0].pageX,n=f.changedTouches[0].pageY}catch(y){n=m=0}imIsObjInvalid(k)?(l=g.url,p=g.urlType):imIsObjInvalid(g)?(l=k.url,p=k.urlType):(l=k.url,p=k.urlType,h=g.url,d=g.pingWV,e=g.fr);g=b.getPlatform();try{if(\"boolean\"!=typeof e&&\"number\"!=typeof e||null==e)e=!0;if(0>e||1<e)e=!0;if(\"boolean\"!=typeof d&&\"number\"!=typeof d||null==d)d=!0;if(0>d||1<d)d=!0;if(\"number\"!=typeof p||null==p)p=0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==d?b.pingInWebView(h):b.ping(h,e):b.log(\"clickurl provided is null.\");if(imIsObjValid(l))switch(imIsObjInvalid(h)&&(l=b.appendTapParams(l,m,n)),p){case 1:b.openEmbedded(l);break;case 2:\"ios\"==g?b.ios.openItunesProductView(l):this.broadcastEvent(\"error\",\"Cannot process openItunesProductView for os\"+g);break;default:b.openExternal(l)}else b.log(\"Landing url provided is null.\")}catch(s){}}}};b.performActionClick=function(a,f){f=f||event;if(imIsObjInvalid(a))b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\");else{var g=a.clickConfig,k=a.landingConfig;if(imIsObjInvalid(g)&&imIsObjInvalid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,d=null,e=null,m=null,n=null;if(imIsObjValid(f))try{m=f.changedTouches[0].pageX,n=f.changedTouches[0].pageY}catch(l){n=m=0}imIsObjValid(g)&&(h=g.url,d=g.pingWV,e=g.fr);try{if(\"boolean\"!=typeof e&&\"number\"!=typeof e||null==e)e=!0;if(0>e||1<e)e=!0;if(\"boolean\"!=typeof d&&\"number\"!=typeof d||null==d)d=!0;if(0>d||1<d)d=!0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==d?b.pingInWebView(h):b.ping(h,e):b.log(\"clickurl provided is null.\");b.onUserInteraction(k)}catch(p){}}}};b.getVersion=function(){return\"1.0\"};b.getPlatform=a.getPlatform;b.getPlatformVersion=a.getPlatformVersion;b.log=a.log;b.openEmbedded=a.openEmbedded;b.openExternal=a.openExternal;b.ping=a.ping;b.pingInWebView=a.pingInWebView;b.onUserInteraction=function(b){try{if(imIsObjValid(b)&&\"object\"==typeof b)a.onUserInteraction(b);else a.onUserInteraction({})}catch(f){}};b.getSdkVersion=a.getSdkVersion;b.ios.openItunesProductView=a.ios.openItunesProductView})();";
  }
}

/* Location:
 * Qualified Name:     com.inmobi.re.configs.Initializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
.class public Lcom/taobao/wireless/security/adapter/JNICLibrary;
.super Ljava/lang/Object;


# static fields
.field private static a:Lcom/taobao/wireless/security/adapter/JNICLibrary;


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/taobao/wireless/security/adapter/JNICLibrary;
    .locals 1

    sget-object v0, Lcom/taobao/wireless/security/adapter/JNICLibrary;->a:Lcom/taobao/wireless/security/adapter/JNICLibrary;

    if-nez v0, :cond_0

    new-instance v0, Lcom/taobao/wireless/security/adapter/JNICLibrary;

    invoke-direct {v0}, Lcom/taobao/wireless/security/adapter/JNICLibrary;-><init>()V

    sput-object v0, Lcom/taobao/wireless/security/adapter/JNICLibrary;->a:Lcom/taobao/wireless/security/adapter/JNICLibrary;

    :cond_0
    sget-object v0, Lcom/taobao/wireless/security/adapter/JNICLibrary;->a:Lcom/taobao/wireless/security/adapter/JNICLibrary;

    return-object v0
.end method


# virtual methods
.method public native addErrorRecord(CCCII)Z
.end method

.method public native analyzeOpenIdNative(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[BLjava/lang/String;)[B
.end method

.method public native atlasEncrypt([B[BLjava/lang/String;)[B
.end method

.method public native checkEnvAndFilesNative([Ljava/lang/String;ILandroid/content/Context;)I
.end method

.method public native doCommandNative(I[Ljava/lang/Object;)Ljava/lang/Object;
.end method

.method public native encryptSecretData(I[B[B)[B
.end method

.method public native getAppKeyByIndex(ILjava/lang/String;)Ljava/lang/String;
.end method

.method public native getDexHashNative(Ljava/lang/String;Ljava/lang/String;I)[B
.end method

.method public native getDynamicKeyNative(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
.end method

.method public native getDynamicValueNative(Ljava/lang/String;[Ljava/lang/String;I)[B
.end method

.method public native getEncryptedDevAndEnvInfoNative(ILjava/lang/String;)[B
.end method

.method public native getExtraData(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
.end method

.method public native getKeyType(Ljava/lang/String;Ljava/lang/String;)I
.end method

.method public native getOrgValueNative(Ljava/lang/String;[Ljava/lang/String;I)[B
.end method

.method public native getSecurityBodyData(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
.end method

.method public native getSeed([B)[B
.end method

.method public native getTraceData()Ljava/lang/String;
.end method

.method public native indieKitRequestNative([Ljava/lang/String;ILjava/lang/String;I)Ljava/lang/String;
.end method

.method public native initNoCaptcha(Ljava/lang/String;Ljava/lang/String;III)V
.end method

.method public native initSecurityBody(Ljava/lang/String;Ljava/lang/String;)Z
.end method

.method public native initialize(Landroid/content/Context;Z)I
.end method

.method public native isPackageValidNative(Ljava/lang/String;)Z
.end method

.method public native isRootNative()Z
.end method

.method public native isSimulator()Z
.end method

.method public native noCaptchaVerification(Ljava/lang/String;)V
.end method

.method public native onControlClick(Ljava/lang/String;Ljava/lang/String;)V
.end method

.method public native onFocusChange(Ljava/lang/String;Ljava/lang/String;Z)V
.end method

.method public native onKeyDown(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
.end method

.method public native onNewPage(Ljava/lang/String;)V
.end method

.method public native onSensorChanged(Ljava/lang/String;FFF)V
.end method

.method public native onTouchScreen(Ljava/lang/String;Ljava/lang/String;FF)V
.end method

.method public native putNoCaptchaTraceRecord(IFF)Z
.end method

.method public native putUserActionRecord(Ljava/lang/String;Ljava/lang/String;FF)Z
.end method

.method public native putUserTrackRecord(Ljava/lang/String;)Z
.end method

.method public native saveKeyEncrypt([B[B)[B
.end method

.method public native seedDecrypt(I[B[B)[B
.end method

.method public native seedEncrypt(I[B[B)[B
.end method

.method public native setSecurityBodyServer(I)V
.end method

.method public native signRequestNative([Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;)Ljava/lang/String;
.end method

.method public native staticDecrypt(I[B[BLjava/lang/String;)[B
.end method

.method public native staticEncrypt(I[B[BLjava/lang/String;)[B
.end method

.method public native testCpuArchCounterNative()I
.end method

.method public native testDataCollectionAdapterNative()Z
.end method

.method public native testDataReportAdapterNative()Z
.end method

.method public native testDynamicUpdateNative()V
.end method

.method public native updateNickNative()V
.end method
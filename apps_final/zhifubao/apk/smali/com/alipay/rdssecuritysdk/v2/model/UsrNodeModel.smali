.class public Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;
.super Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;


# static fields
.field public static final mapKeys:[Ljava/lang/String;


# instance fields
.field private a:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private b:Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    const/16 v0, 0x8

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string/jumbo v2, "appver"

    aput-object v2, v0, v1

    const/4 v1, 0x1

    const-string/jumbo v2, "ua"

    aput-object v2, v0, v1

    const/4 v1, 0x2

    const-string/jumbo v2, "user"

    aput-object v2, v0, v1

    const/4 v1, 0x3

    const-string/jumbo v2, "appname"

    aput-object v2, v0, v1

    const/4 v1, 0x4

    const-string/jumbo v2, "sdkver"

    aput-object v2, v0, v1

    const/4 v1, 0x5

    const-string/jumbo v2, "sdkname"

    aput-object v2, v0, v1

    const/4 v1, 0x6

    const-string/jumbo v2, "pubkey"

    aput-object v2, v0, v1

    const/4 v1, 0x7

    const-string/jumbo v2, "appkey"

    aput-object v2, v0, v1

    sput-object v0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->mapKeys:[Ljava/lang/String;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;-><init>()V

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    invoke-direct {p0}, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;-><init>()V

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "appver"

    invoke-interface {v0, v1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "user"

    invoke-interface {v0, v1, p3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "appname"

    invoke-interface {v0, v1, p4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "sdkver"

    const-string/jumbo v2, "2.0.20150501"

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "sdkname"

    const-string/jumbo v2, "APRdsSdk"

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {}, Lcom/alipay/security/mobile/module/deviceinfo/AppInfo;->getInstance()Lcom/alipay/security/mobile/module/deviceinfo/AppInfo;

    move-result-object v0

    invoke-virtual {v0, p1, p6}, Lcom/alipay/security/mobile/module/deviceinfo/AppInfo;->getPublicKey(Landroid/content/Context;Ljava/lang/String;)[B

    move-result-object v0

    iget-object v1, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v2, "pubkey"

    invoke-static {v0}, Lcom/alipay/security/mobile/module/crypto/DigestUtil;->digestWithSha1([B)Ljava/lang/String;

    move-result-object v0

    invoke-interface {v1, v2, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "appkey"

    invoke-static {p5}, Lcom/alipay/security/mobile/module/crypto/util/CommonUtils;->isBlank(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_0

    const-string/jumbo p5, ""

    :cond_0
    invoke-interface {v0, v1, p5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public bridge synthetic buildJsonNode()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->buildJsonNode()Lorg/json/JSONObject;

    move-result-object v0

    return-object v0
.end method

.method public buildJsonNode()Lorg/json/JSONObject;
    .locals 7

    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    sget-object v3, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->mapKeys:[Ljava/lang/String;

    array-length v4, v3

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v4, :cond_2

    aget-object v5, v3, v1

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    invoke-interface {v0, v5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_1

    instance-of v6, v0, Ljava/lang/String;

    if-eqz v6, :cond_1

    :try_start_0
    invoke-virtual {v2, v5, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_1

    :cond_0
    :goto_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    :cond_1
    if-eqz v0, :cond_0

    instance-of v6, v0, Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;

    if-eqz v6, :cond_0

    :try_start_1
    check-cast v0, Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;

    invoke-virtual {v0}, Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;->buildJsonNode()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v2, v5, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    :catch_0
    move-exception v0

    goto :goto_1

    :catch_1
    move-exception v0

    goto :goto_1

    :cond_2
    return-object v2
.end method

.method public injectNodes(Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;)V
    .locals 3

    iput-object p1, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->b:Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "ua"

    iget-object v2, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->b:Lcom/alipay/rdssecuritysdk/v2/model/BaseNodeModel;

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public updateUser(Ljava/lang/String;)V
    .locals 2

    iget-object v0, p0, Lcom/alipay/rdssecuritysdk/v2/model/UsrNodeModel;->a:Ljava/util/Map;

    const-string/jumbo v1, "user"

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method
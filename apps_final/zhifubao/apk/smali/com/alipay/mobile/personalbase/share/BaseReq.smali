.class public abstract Lcom/alipay/mobile/personalbase/share/BaseReq;
.super Ljava/lang/Object;
.source "BaseReq.java"


# instance fields
.field public transaction:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public fromBundle(Landroid/os/Bundle;)V
    .locals 1

    .prologue
    .line 19
    sget-object v0, Lcom/alipay/mobile/personalbase/share/ShareConstants;->EXTRA_BASEREQ_TRANSACTION:Ljava/lang/String;

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/alipay/mobile/personalbase/share/BaseReq;->transaction:Ljava/lang/String;

    .line 20
    return-void
.end method

.method public abstract getType()I
.end method

.method public toBundle(Landroid/os/Bundle;)V
    .locals 2

    .prologue
    .line 14
    sget-object v0, Lcom/alipay/mobile/personalbase/share/ShareConstants;->EXTRA_COMMAND_TYPE:Ljava/lang/String;

    invoke-virtual {p0}, Lcom/alipay/mobile/personalbase/share/BaseReq;->getType()I

    move-result v1

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 15
    sget-object v0, Lcom/alipay/mobile/personalbase/share/ShareConstants;->EXTRA_BASEREQ_TRANSACTION:Ljava/lang/String;

    iget-object v1, p0, Lcom/alipay/mobile/personalbase/share/BaseReq;->transaction:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 16
    return-void
.end method
.class Lae/gov/mol/fragment/eTasheel/eServices/Fragment_eServiceDetail$setValue;
.super Ljava/lang/Object;
.source "Fragment_eServiceDetail.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lae/gov/mol/fragment/eTasheel/eServices/Fragment_eServiceDetail;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "setValue"
.end annotation


# instance fields
.field mstrJson:Ljava/lang/String;

.field final synthetic this$0:Lae/gov/mol/fragment/eTasheel/eServices/Fragment_eServiceDetail;


# direct methods
.method constructor <init>(Lae/gov/mol/fragment/eTasheel/eServices/Fragment_eServiceDetail;)V
    .locals 1

    .prologue
    .line 851
    iput-object p1, p0, Lae/gov/mol/fragment/eTasheel/eServices/Fragment_eServiceDetail$setValue;->this$0:Lae/gov/mol/fragment/eTasheel/eServices/Fragment_eServiceDetail;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 852
    const-string v0, ""

    iput-object v0, p0, Lae/gov/mol/fragment/eTasheel/eServices/Fragment_eServiceDetail$setValue;->mstrJson:Ljava/lang/String;

    return-void
.end method
.class Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig$1;
.super Ljava/lang/ThreadLocal;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/ThreadLocal",
        "<",
        "Ljava/text/DateFormat;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$1:Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig;


# direct methods
.method constructor <init>(Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig;)V
    .locals 0

    iput-object p1, p0, Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig$1;->this$1:Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig;

    invoke-direct {p0}, Ljava/lang/ThreadLocal;-><init>()V

    return-void
.end method


# virtual methods
.method protected bridge synthetic initialValue()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig$1;->initialValue()Ljava/text/DateFormat;

    move-result-object v0

    return-object v0
.end method

.method protected initialValue()Ljava/text/DateFormat;
    .locals 2

    new-instance v0, Ljava/text/SimpleDateFormat;

    iget-object v1, p0, Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig$1;->this$1:Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig;

    iget-object v1, v1, Lcom/j256/ormlite/field/types/BaseDateType$DateStringFormatConfig;->dateFormatStr:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    return-object v0
.end method
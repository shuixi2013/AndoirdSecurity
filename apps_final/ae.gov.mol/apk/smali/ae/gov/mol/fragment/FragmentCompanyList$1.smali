.class Lae/gov/mol/fragment/FragmentCompanyList$1;
.super Ljava/lang/Object;
.source "FragmentCompanyList.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lae/gov/mol/fragment/FragmentCompanyList;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lae/gov/mol/fragment/FragmentCompanyList;


# direct methods
.method constructor <init>(Lae/gov/mol/fragment/FragmentCompanyList;)V
    .locals 0

    .prologue
    .line 1
    iput-object p1, p0, Lae/gov/mol/fragment/FragmentCompanyList$1;->this$0:Lae/gov/mol/fragment/FragmentCompanyList;

    .line 156
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1
    .param p1, "v"    # Landroid/view/View;

    .prologue
    .line 161
    iget-object v0, p0, Lae/gov/mol/fragment/FragmentCompanyList$1;->this$0:Lae/gov/mol/fragment/FragmentCompanyList;

    iget-object v0, v0, Lae/gov/mol/fragment/FragmentCompanyList;->molFragmentActivity:Lae/gov/mol/MolFragmentActivity;

    invoke-virtual {v0}, Lae/gov/mol/MolFragmentActivity;->removeBack()V

    .line 162
    return-void
.end method
.class final Lcom/google/glass/util/ParcelableImageSpannable$1;
.super Ljava/lang/Object;
.source "ParcelableImageSpannable.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/glass/util/ParcelableImageSpannable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator",
        "<",
        "Lcom/google/glass/util/ParcelableImageSpannable;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 82
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public createFromParcel(Landroid/os/Parcel;)Lcom/google/glass/util/ParcelableImageSpannable;
    .locals 2
    .parameter "in"

    .prologue
    .line 85
    new-instance v0, Lcom/google/glass/util/ParcelableImageSpannable;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lcom/google/glass/util/ParcelableImageSpannable;-><init>(Landroid/os/Parcel;Lcom/google/glass/util/ParcelableImageSpannable$1;)V

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1
    .parameter "x0"

    .prologue
    .line 82
    invoke-virtual {p0, p1}, Lcom/google/glass/util/ParcelableImageSpannable$1;->createFromParcel(Landroid/os/Parcel;)Lcom/google/glass/util/ParcelableImageSpannable;

    move-result-object v0

    return-object v0
.end method

.method public newArray(I)[Lcom/google/glass/util/ParcelableImageSpannable;
    .locals 1
    .parameter "size"

    .prologue
    .line 90
    new-array v0, p1, [Lcom/google/glass/util/ParcelableImageSpannable;

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1
    .parameter "x0"

    .prologue
    .line 82
    invoke-virtual {p0, p1}, Lcom/google/glass/util/ParcelableImageSpannable$1;->newArray(I)[Lcom/google/glass/util/ParcelableImageSpannable;

    move-result-object v0

    return-object v0
.end method

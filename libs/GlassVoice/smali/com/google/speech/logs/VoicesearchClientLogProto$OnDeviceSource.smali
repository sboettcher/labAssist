.class public final Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;
.super Lcom/google/protobuf/micro/MessageMicro;
.source "VoicesearchClientLogProto.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/speech/logs/VoicesearchClientLogProto;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "OnDeviceSource"
.end annotation


# static fields
.field public static final PACKAGE_NAME_FIELD_NUMBER:I = 0x1


# instance fields
.field private cachedSize:I

.field private hasPackageName:Z

.field private packageName_:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 2560
    invoke-direct {p0}, Lcom/google/protobuf/micro/MessageMicro;-><init>()V

    .line 2565
    const-string v0, ""

    iput-object v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->packageName_:Ljava/lang/String;

    .line 2597
    const/4 v0, -0x1

    iput v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->cachedSize:I

    .line 2560
    return-void
.end method

.method public static parseFrom(Lcom/google/protobuf/micro/CodedInputStreamMicro;)Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;
    .locals 1
    .parameter "input"
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 2649
    new-instance v0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    invoke-direct {v0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;-><init>()V

    invoke-virtual {v0, p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->mergeFrom(Lcom/google/protobuf/micro/CodedInputStreamMicro;)Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    move-result-object v0

    return-object v0
.end method

.method public static parseFrom([B)Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;
    .locals 1
    .parameter "data"
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/google/protobuf/micro/InvalidProtocolBufferMicroException;
        }
    .end annotation

    .prologue
    .line 2643
    new-instance v0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    invoke-direct {v0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;-><init>()V

    invoke-virtual {v0, p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->mergeFrom([B)Lcom/google/protobuf/micro/MessageMicro;

    move-result-object v0

    check-cast v0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    check-cast v0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    return-object v0
.end method


# virtual methods
.method public final clear()Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;
    .locals 1

    .prologue
    .line 2580
    invoke-virtual {p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->clearPackageName()Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    .line 2581
    const/4 v0, -0x1

    iput v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->cachedSize:I

    .line 2582
    return-object p0
.end method

.method public clearPackageName()Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;
    .locals 1

    .prologue
    .line 2574
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->hasPackageName:Z

    .line 2575
    const-string v0, ""

    iput-object v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->packageName_:Ljava/lang/String;

    .line 2576
    return-object p0
.end method

.method public getCachedSize()I
    .locals 1

    .prologue
    .line 2600
    iget v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->cachedSize:I

    if-gez v0, :cond_0

    .line 2602
    invoke-virtual {p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->getSerializedSize()I

    .line 2604
    :cond_0
    iget v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->cachedSize:I

    return v0
.end method

.method public getPackageName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 2566
    iget-object v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->packageName_:Ljava/lang/String;

    return-object v0
.end method

.method public getSerializedSize()I
    .locals 3

    .prologue
    .line 2609
    const/4 v0, 0x0

    .line 2610
    .local v0, size:I
    invoke-virtual {p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->hasPackageName()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 2611
    const/4 v1, 0x1

    invoke-virtual {p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/google/protobuf/micro/CodedOutputStreamMicro;->computeStringSize(ILjava/lang/String;)I

    move-result v1

    add-int/2addr v0, v1

    .line 2614
    :cond_0
    iput v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->cachedSize:I

    .line 2615
    return v0
.end method

.method public hasPackageName()Z
    .locals 1

    .prologue
    .line 2567
    iget-boolean v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->hasPackageName:Z

    return v0
.end method

.method public final isInitialized()Z
    .locals 1

    .prologue
    .line 2586
    const/4 v0, 0x1

    return v0
.end method

.method public bridge synthetic mergeFrom(Lcom/google/protobuf/micro/CodedInputStreamMicro;)Lcom/google/protobuf/micro/MessageMicro;
    .locals 1
    .parameter "x0"
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 2557
    invoke-virtual {p0, p1}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->mergeFrom(Lcom/google/protobuf/micro/CodedInputStreamMicro;)Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    move-result-object v0

    return-object v0
.end method

.method public mergeFrom(Lcom/google/protobuf/micro/CodedInputStreamMicro;)Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;
    .locals 2
    .parameter "input"
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 2623
    :cond_0
    :goto_0
    invoke-virtual {p1}, Lcom/google/protobuf/micro/CodedInputStreamMicro;->readTag()I

    move-result v0

    .line 2624
    .local v0, tag:I
    sparse-switch v0, :sswitch_data_0

    .line 2628
    invoke-virtual {p0, p1, v0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->parseUnknownField(Lcom/google/protobuf/micro/CodedInputStreamMicro;I)Z

    move-result v1

    if-nez v1, :cond_0

    .line 2629
    :sswitch_0
    return-object p0

    .line 2634
    :sswitch_1
    invoke-virtual {p1}, Lcom/google/protobuf/micro/CodedInputStreamMicro;->readString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v1}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->setPackageName(Ljava/lang/String;)Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;

    goto :goto_0

    .line 2624
    :sswitch_data_0
    .sparse-switch
        0x0 -> :sswitch_0
        0xa -> :sswitch_1
    .end sparse-switch
.end method

.method public setPackageName(Ljava/lang/String;)Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;
    .locals 1
    .parameter "value"

    .prologue
    .line 2569
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->hasPackageName:Z

    .line 2570
    iput-object p1, p0, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->packageName_:Ljava/lang/String;

    .line 2571
    return-object p0
.end method

.method public writeTo(Lcom/google/protobuf/micro/CodedOutputStreamMicro;)V
    .locals 2
    .parameter "output"
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 2592
    invoke-virtual {p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->hasPackageName()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2593
    const/4 v0, 0x1

    invoke-virtual {p0}, Lcom/google/speech/logs/VoicesearchClientLogProto$OnDeviceSource;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v0, v1}, Lcom/google/protobuf/micro/CodedOutputStreamMicro;->writeString(ILjava/lang/String;)V

    .line 2595
    :cond_0
    return-void
.end method

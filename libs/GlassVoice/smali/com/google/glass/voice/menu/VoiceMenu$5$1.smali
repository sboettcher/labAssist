.class Lcom/google/glass/voice/menu/VoiceMenu$5$1;
.super Landroid/animation/AnimatorListenerAdapter;
.source "VoiceMenu.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/glass/voice/menu/VoiceMenu$5;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/google/glass/voice/menu/VoiceMenu$5;


# direct methods
.method constructor <init>(Lcom/google/glass/voice/menu/VoiceMenu$5;)V
    .locals 0
    .parameter

    .prologue
    .line 374
    iput-object p1, p0, Lcom/google/glass/voice/menu/VoiceMenu$5$1;->this$1:Lcom/google/glass/voice/menu/VoiceMenu$5;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2
    .parameter "animation"

    .prologue
    .line 377
    iget-object v0, p0, Lcom/google/glass/voice/menu/VoiceMenu$5$1;->this$1:Lcom/google/glass/voice/menu/VoiceMenu$5;

    iget-object v0, v0, Lcom/google/glass/voice/menu/VoiceMenu$5;->val$postAnimationRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 378
    iget-object v0, p0, Lcom/google/glass/voice/menu/VoiceMenu$5$1;->this$1:Lcom/google/glass/voice/menu/VoiceMenu$5;

    iget-object v0, v0, Lcom/google/glass/voice/menu/VoiceMenu$5;->this$0:Lcom/google/glass/voice/menu/VoiceMenu;

    iget-object v1, p0, Lcom/google/glass/voice/menu/VoiceMenu$5$1;->this$1:Lcom/google/glass/voice/menu/VoiceMenu$5;

    iget-object v1, v1, Lcom/google/glass/voice/menu/VoiceMenu$5;->val$postAnimationRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lcom/google/glass/voice/menu/VoiceMenu;->cancelIfDismissed(Ljava/lang/Runnable;)Ljava/lang/Runnable;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    .line 380
    :cond_0
    iget-object v0, p0, Lcom/google/glass/voice/menu/VoiceMenu$5$1;->this$1:Lcom/google/glass/voice/menu/VoiceMenu$5;

    iget-object v0, v0, Lcom/google/glass/voice/menu/VoiceMenu$5;->this$0:Lcom/google/glass/voice/menu/VoiceMenu;

    #getter for: Lcom/google/glass/voice/menu/VoiceMenu;->selectedPrimaryMenuItem:Lcom/google/glass/widget/TypophileTextView;
    invoke-static {v0}, Lcom/google/glass/voice/menu/VoiceMenu;->access$700(Lcom/google/glass/voice/menu/VoiceMenu;)Lcom/google/glass/widget/TypophileTextView;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/glass/widget/TypophileTextView;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setListener(Landroid/animation/Animator$AnimatorListener;)Landroid/view/ViewPropertyAnimator;

    .line 381
    return-void
.end method

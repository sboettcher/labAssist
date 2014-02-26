package de.tud.labAssist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterViewFlipper;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.glass.media.Sounds;

import de.tud.ess.BearingLocalizer;
import de.tud.ess.BearingLocalizer.BearingLocalizerListener;
import de.tud.ess.HeadImageView;
import de.tud.ess.LogCatWriter;
import de.tud.ess.OnSwipeTouchListener;
import de.tud.ess.VerticalBars;
import de.tud.ess.VoiceMenu;
import de.tud.ess.VoiceMenu.VoiceMenuListener;
import de.tud.labAssist.LabMarkdown.ProtocolStep;

public class LabAssist extends FragmentActivity implements VoiceMenuListener {
  public static final String TAG = "labAssist";
  private AudioManager mAudio;
  protected AdapterViewFlipper mCardScrollView;
  protected VoiceMenu mVoiceMenu;
  protected boolean mAttentionChallenge = false;
  protected TextView mBarText;
  protected BearingLocalizer    mBearinglocalizer;
  protected LogCatWriter mLogCatWriter;
  protected FeedbackController mFeedback;
  protected View mMainView;
  protected OnSwipeTouchListener mSwipeTouchListener;
  
  protected static final String NEXT = "next slide";
  protected static final String PREVIOUS = "previous";
  //protected static final String GOFORWARD = "go forward";
  //protected static final String GOBACK = "go back";
  protected static final String DONE = "mark as done";
  protected static final String CHECK = "check this step";
  //protected static final String MARK = "mark ";
  protected static final String ZOOM_IN = "zoom image";
  protected static final String ZOOM_OUT = "scale down";
  protected static final String LENGTH = "bar changed";
  protected static final String COLOR = "highlight color";
  protected static final String[] STATIC_VOICECOMMANDS = new String[]
      { NEXT, PREVIOUS  };
  protected static final String OKGLASS = "ok glass";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    /* start a logcat instance that logs the current run on the sdcard */
    String now  = new SimpleDateFormat("yyyy-MMM-dd-HH-mm-ss").format(new Date());
    String path = new File(getExternalFilesDir(null), "logcat_" + now + ".log").toString();
    mLogCatWriter = new LogCatWriter(path, TAG);

    Log.e(TAG, path);
    
    if (!getIntent().hasExtra(Launcher.FILENAME))
    {
      Log.e(TAG, String.format("missing argument for '%s'", Launcher.FILENAME));
      Toast.makeText(this, "no document supplied", Toast.LENGTH_LONG).show();
      return;
    }
    
    String file = getIntent().getExtras().getString(Launcher.FILENAME);
    
    if (file==null)
    {
      Toast.makeText(this, "file not found", Toast.LENGTH_LONG).show();
      return;      
    }
    
    String mdown = null; 
    
    try {
      mdown = toString(getAssets().open(file));
    } catch (IOException e) {
      File ext = getExternalFilesDir(null);
      if (ext == null) {
        Log.e(TAG, "external storage not mounted");
        return;
      }
      try {
        mdown = toString(new FileInputStream(new File(ext,file)));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
    
    if (file.contains("Lego")) {
      setContentView(R.layout.barlayout);
      mMainView = findViewById(R.id.mainView);
      mAttentionChallenge = true;
      mBarText = (TextView) findViewById(R.id.bartext);
      mBarText.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
    } else {
      setContentView(R.layout.main);
      mMainView = findViewById(R.id.mainView);
    }

    mVoiceMenu = new VoiceMenu(this, OKGLASS);
    
    LabMarkdown lm = new LabMarkdown(this, mdown);
    mCardScrollView = (AdapterViewFlipper) findViewById(R.id.cardscroll);
    mCardScrollView.setAdapter(lm);
    mCardScrollView.setOnItemClickListener(new LabOnClickListener());
    mCardScrollView.setOnItemSelectedListener(new VoiceConfigChanger());
    
    mSwipeTouchListener = new OnSwipeTouchListener(this) {
      @Override
      public void onSwipeLeft() {
        mCardScrollView.showPrevious();
      }
      @Override
      public void onSwipeRight() {
        mCardScrollView.showNext();
      }
    };

    mAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    
    mFeedback = new FeedbackController();
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return mSwipeTouchListener.onTouch(mCardScrollView, event);
  }
  
  @Override
  public boolean onGenericMotionEvent(MotionEvent event) {
    return mSwipeTouchListener.onTouch(mCardScrollView, event);
  }
  
  @Override
  protected void onDestroy() {
    super.onDestroy();
    
    if (mLogCatWriter != null) {
      mLogCatWriter.stop();
      mLogCatWriter = null;
    }
  }
  
  private String toString(InputStream is) {
    Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
    String str = scanner.hasNext() ? scanner.next() : "";
    scanner.close();
    return str;
  }

  @Override
  protected void onResume() {
    super.onResume();
    mCardScrollView.setOnItemSelectedListener(new OnItemSelectedListener(   ) {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, String.format("switch to item %d", position));
        mFeedback.onItemSelected(parent, view, position, id);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
      
    });
    
    //mBearinglocalizer = new BearingLocalizer(this, mFeedback);

    mVoiceMenu.setListener(this);
    getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
    
    Log.e(TAG, "onResume");
  }

  @Override
  protected void onPause() {
    mVoiceMenu.setListener(null);
    mCardScrollView.setOnItemClickListener(null);
    if (mBearinglocalizer != null)
      mBearinglocalizer.deactivate();
    mBearinglocalizer = null;
    super.onPause();
    
    Log.e(TAG, "onPause");
  }

  protected Method mAnimateFunc;
  protected static final int ANIMATE_GOTO = 2;
  protected static final float SCALE_STEP = 1.F;
  
  public class LabOnClickListener implements OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int cur, long id) {
      ProtocolStep step = (ProtocolStep) mCardScrollView.getItemAtPosition(cur);
      
      mAudio.playSoundEffect(Sounds.TAP);
      if (step.hasZoomAbleImage()) {
        HeadImageView v = (HeadImageView) view.findViewById(R.id.imview);
        v.setScaleFactor(v.getScaleFactor() + SCALE_STEP);
        Log.e(TAG, "zoomed in (via tap)");
      }
      else if (step.hasCheckableItems()) 
        markAsDone(step, cur);
    }

  }

  protected void markAsDone(ProtocolStep step, int pos) {
    boolean done = step.markAsDone();
    mCardScrollView.getAdapter().getView(pos, mCardScrollView.getSelectedView(), null);
    if (done) {
      mFeedback.switchedByMarkingTo(pos + 1);
      mCardScrollView.showNext();
    }
    
    Log.e(TAG, String.format("marked item %d as done (%b)", pos, done));
  }
  
  public class VoiceConfigChanger implements OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
        long id) {
      ProtocolStep s = (ProtocolStep) parent.getItemAtPosition(position);
      recreateVoiceMenu(s);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
  }

  
  @Override
  public void onItemSelected(String literal) {
    try {
      int cur = mCardScrollView.getSelectedItemPosition();
      Log.e("cv", String.format("cure position: %d", cur));
      ProtocolStep step = (ProtocolStep) mCardScrollView
          .getItemAtPosition(cur);
      HeadImageView im = (HeadImageView) mCardScrollView
          .getSelectedView().findViewById(R.id.imview);
      
      if (PREVIOUS.equals(literal))
        mCardScrollView.showNext();
      else if (NEXT.equals(literal))
        mCardScrollView.showPrevious();
      else if (CHECK.equals(literal) || DONE.equals(literal) ) //|| MARK.equals(literal))
        markAsDone(step, cur);
      else if (ZOOM_IN.equals(literal))
        im.setScaleFactor( im.getScaleFactor() + SCALE_STEP );
      else if (ZOOM_OUT.equals(literal))
        im.setScaleFactor( im.getScaleFactor() - SCALE_STEP );
      else if (COLOR.equals(literal))
        toggleBarText(true,false);
      else if (LENGTH.equals(literal))
        toggleBarText(false,true);
      else if (OKGLASS.equals(literal))
        ; //toggleBarText(true, true);
      else
        mAudio.playSoundEffect(Sounds.ERROR);
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
  }

  protected void toggleBarText(boolean color, boolean bar) {
    VerticalBars v = (VerticalBars) findViewById(R.id.bars);
    updateBarText(color ? v.getCurrentColor() : null,
                  bar ? v.getCurrentBar() : null);
  }

  protected String mBarColor = "";
  protected String mBarPosition = "";
  protected void updateBarText(String color, String position) {    
    if (mBarText==null) {
      Log.e(TAG, "setting barText without a view");
      return;
    }
    
    String[] cur = mBarText.getText().toString().split("-");
    String mBarColor = "", mBarPosition = "";
    
    if (cur.length==1)
      mBarColor = cur[0];
    else if (cur.length==2) {
      mBarColor = cur[0];
      mBarPosition = cur[1];
    }
    
    if (color != null) {
      mBarColor = color;
      Log.e(TAG, String.format("user gave new color: %s", color));
    }
    
    if (position != null) {
      mBarPosition = position;
      Log.e(TAG, String.format("user gave new position: %s", position));
    }
    
    mBarText.setText(String.format("%s - %s",mBarColor.trim(), mBarPosition.trim()));
  }

  protected void recreateVoiceMenu(ProtocolStep s) {
    List<String> c = new LinkedList<String>(Arrays.asList(STATIC_VOICECOMMANDS));
    
    if (s.hasZoomAbleImage()) {
      c.add(ZOOM_IN);
      c.add(ZOOM_OUT);
    }
    if (s.hasCheckableItems()) {
      //c.add(MARK);
      c.add(CHECK);
      c.add(DONE);
    }
    if (mAttentionChallenge ) {
      c.add(COLOR);
      c.add(LENGTH);
    }
    
    mVoiceMenu.setCommands((String[]) c.toArray(new String[c.size()]));
  }
  
  /** positive feedback when the current step needs feedback and bearing
   * has been entered. negative feedback when step needed feedback but has 
   * never gotten any.  */
  public class FeedbackController implements BearingLocalizerListener {
   
    protected boolean mWantFeedback = false;
    protected boolean mGotFeedback  = false;

    @Override
    public void enteredBearing() {
      Log.e(TAG, "entered bearing");
      
      if (!mWantFeedback || mGotFeedback)
        return;
      
      mGotFeedback = true;
      giveFeedback(true);
    }

    public void switchedByMarkingTo(int i) {
      if (mWantFeedback && !mGotFeedback)
        giveFeedback(false);
      
      mGotFeedback = false;
    }

    @Override
    public void leftBearing() {
      Log.e(TAG, "left bearing");
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
        long id) {
      ProtocolStep s = (ProtocolStep) parent.getItemAtPosition(position);
      mWantFeedback  = (s!=null && s.hasFeedback());
      mGotFeedback   = false;
    }
  }
  
  protected void giveFeedback(boolean b) {
    Log.e(TAG, String.format("giving feedback %b",b));
    Intent i = new Intent(this, PersuasiveFeedback.class);
    i.putExtra(PersuasiveFeedback.STATE_ARGUMENT, b);
    startActivity(i);
  }
  
}
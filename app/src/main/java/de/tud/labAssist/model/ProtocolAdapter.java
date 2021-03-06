package de.tud.labAssist.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.glass.widget.CardScrollAdapter;

import java.util.HashMap;
import java.util.List;

import de.tud.ess.AdapterGridLayout;
import de.tud.ess.HeadScrollView;
import de.tud.labAssist.R;
import de.tud.labAssist.model.steps.MajorStep;
import de.tud.labAssist.model.time.TimerManager;

/**
 * Created by Ramon on 02.05.2014.
 */
public class ProtocolAdapter extends CardScrollAdapter {
	private static HashMap<String, Drawable> pictograms;
	private final LayoutInflater inflater;
	private final Context context;
	private List<MajorStep> steps;
	private TimerManager timerManager;

	private static class ViewMem {
		public HeadScrollView scrollView;
		public AdapterGridLayout footer;
		public TextView header;
	}

	public static void setPictograms(HashMap<String, Drawable> pictograms) {
		ProtocolAdapter.pictograms = pictograms;
	}


	public ProtocolAdapter(Context context, Protocol protocol, TimerManager timerManager) {
		this.steps = protocol.getSteps();//maybe make list immutable
		this.context = context;
		this.timerManager = timerManager;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getCount() {
		return steps.size();
	}

	@Override
	public MajorStep getItem(int i) {
		return steps.get(i);
	}

	@Override
	public View getView(int i, View convertView, ViewGroup viewGroup) {
		View v = convertView;
		MajorStep step = steps.get(i);

		ViewMem mem;

		if (v == null) {
			v = inflater.inflate(R.layout.major_step, viewGroup, false);

			mem = new ViewMem();

			mem.header = (TextView) v.findViewById(R.id.header);
			mem.scrollView = (HeadScrollView) v.findViewById(R.id.scrollView);
			mem.footer = (AdapterGridLayout) v.findViewById(R.id.footer);

			v.setTag(mem);

		} else {
			mem = (ViewMem) v.getTag();
		}

		mem.header.setText(step.getHeader());

		AnnotationAdapter aa = new AnnotationAdapter(context, step.getAnnotations(), pictograms);
//		mem.footer.setAdapter(aa); //TODO: works, but stutters, probably need to cache pictograms
		mem.footer.removeAllViews();
		for (int j=0; j<aa.getCount(); ++j) {
			mem.footer.addView(aa.getView(j, null, mem.footer));
		}
//		aa.notifyDataSetChanged();
		mem.scrollView.setAdapter(new MinorStepAdapter(context, step.getMinorSteps(), timerManager));
		return v;
	}

	@Override
	public int getPosition(Object o) {
		return steps.indexOf(o);
	}

}

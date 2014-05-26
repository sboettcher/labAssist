package de.tud.labAssist.model.io;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.tud.labAssist.model.steps.MajorStep;
import in.uncod.android.bypass.Bypass;
import in.uncod.android.bypass.Document;

/**
 * Created by Ramon on 22.04.2014.
 */
public class MarkdownManager {

	public static String[] getMarkdownDocuments(Context context) {
		List<String> paths = new LinkedList<>();

		try {
			for (String path : context.getAssets().list(""))
				if (path.trim().endsWith(".md"))
					paths.add(path.trim().substring(0, path.trim().length() - 3));

			File dir = context.getExternalFilesDir(null);
			if (dir == null)
				return paths.toArray(new String[paths.size()]);

			Log.e("labLauncher", String.format("External directory is %s", dir.toString()));

			for (String path : dir.list())
				if (path.trim().endsWith(".md"))
					paths.add(path.trim().substring(0, path.trim().length() - 3));

			return paths.toArray(new String[paths.size()]);
		} catch (IOException e) {
			Log.e("LabLauncher", e.toString());
			return new String[0];
		}
	}

	public static List<MajorStep> getProtocol(String md) {

		Bypass bp = new Bypass();
		Document doc = bp.processMarkdown(md);
		doc.toString();

		List<MajorStep> steps = new ReadInVisitor().readIn(doc);

		return steps;
	}
}

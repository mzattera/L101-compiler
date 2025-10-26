/**
 * 
 */
package org.mzattera.dsl.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Various file utilities.
 * 
 * @author Massimiliano "Maxi" Zattera
 *
 */
public class FileUtil {

	private FileUtil() {
	}

	/**
	 * Write a string into an ASCII file.
	 */
	public static void write(String txt, String fileName) throws IOException {
		write(txt, fileName, "ASCII");
	}

	/**
	 * Write a string into a file with given encoding.
	 */
	public static void write(String txt, String fileName, String encoding) throws IOException {

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), encoding));
			out.write(txt);
			out.flush();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception e) {
				}
		}
	}

	/**
	 * Write a list of strings into a file. Uses UTF-8 encoding.
	 */
	public static void write(List<String> txt, String fileName) throws IOException {
		write(txt, fileName, "UTF-8");
	}

	/**
	 * Write a list of strings into a file with given encoding.
	 */
	public static void write(List<String> txt, String fileName, String encoding) throws IOException {

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), encoding));
			for (String s : txt) {
				out.write(s);
				out.newLine();
			}
			out.flush();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception e) {
				}
		}
	}

	/**
	 * Reads given text file and returns its contents as a list of non-empty lines.
	 * It assumes file is in UTF-8 encoding.
	 * 
	 * @throws IOException
	 */
	public static List<String> read(File file) throws IOException {
		return read(file, "UTF-8");
	}

	/**
	 * Reads given text file and returns its contents as a list of non-empty lines.
	 * 
	 * @throws IOException
	 */
	public static List<String> read(File file, String encoding) throws IOException {
		return read(file.getCanonicalPath(), encoding);
	}

	/**
	 * Reads given text file and returns its contents as a list of non-empty lines.
	 * It assumes file is in UTF-8 encoding.
	 * 
	 * @throws IOException
	 */
	public static List<String> read(String fileName) throws IOException {
		return read(fileName, "UTF-8");
	}

	/**
	 * Reads given text file and returns its contents as a list of non-empty lines.
	 * 
	 * @throws IOException
	 */
	public static List<String> read(String fileName, String encoding) throws IOException {
		 // TODO 1 - use standard Java functions instead.

		List<String> rows = new ArrayList<String>();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding));
			String row;
			while ((row = in.readLine()) != null) {
				rows.add(row);
			}

			return rows;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (Exception e) {
				}
		}
	}

	/**
	 * Replaces the extension for a file.
	 * 
	 * @param fileName Original file name.
	 * @param ext      New extension.
	 * 
	 * @return Original file name, but with its extension changed (
	 */
	public static String replaceExtension(String fileName, String ext) {
		int p = fileName.lastIndexOf('.');
		String result;

		if (p == -1) {
			result = fileName;
		} else {
			result = fileName.substring(0, p);
		}

		if (ext.startsWith(".") && (ext.length() > 1))
			ext = ext.substring(1);

		return result + '.' + ext;
	}

	/**
	 * Returns file name for output (compiled) file. The output file name will
	 * retain any relative folder path that "source" had relative to "src" folder.
	 * 
	 * @param source The URI for the input (source) file. It is expected that this
	 *               contains a fragment "src" corresponding to the default input
	 *               folder.
	 * @return Output file name, including any relative path under "src" folder.
	 */
	public static String getCompiledFileName(URI source) {
		int i = 0;
		for (; i < source.segmentCount(); ++i) {
			if (source.segment(i).equalsIgnoreCase("src"))
				break;
		}

		StringBuffer result = new StringBuffer();
		for (++i; i < source.segmentCount(); ++i) {
			if (result.length() > 0)
				result.append('/');
			try {
				result.append(URLDecoder.decode(source.segment(i), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				result.append(source.segment(i));
			}
		}

		return result.toString();
	}

	/**
	 * Returns file name for output (compiled) file. The output file name will
	 * retain any relative folder path that "source" had relative to "src" folder.
	 * 
	 * @param source The URI for the input (source) file. It is expected that this
	 *               contains a fragment "src" corresponding to the default input
	 *               folder.
	 * @param ext    New file extension.
	 * @return Output file name, including any relative path under "src" folder.
	 */
	public static String getCompiledFileName(URI source, String ext) {
		return replaceExtension(getCompiledFileName(source), ext);
	}

	/**
	 * Returns file name for output (compiled) file. The output file name will
	 * retain any relative folder path that "resource" had relative to "src" folder.
	 * 
	 * @param resource The resource for the input (source) file. It is expected that
	 *                 this is located under "src" folder in a project.
	 * @param ext      New file extension.
	 * @return Output file name, including any relative path under "src" folder.
	 */
	public static String getCompiledFileName(Resource resource, String ext) {
		return replaceExtension(getCompiledFileName(resource.getURI()), ext);
	}

	/**
	 * Returns resource URI for output (compiled) file. The output file will be
	 * created under "src-gen" folder and will retain any relative folder path that
	 * "source" had relative to "src" folder.
	 * 
	 * @param source The URI for the input (source) file. It is expected that this
	 *               contains a fragment "src" corresponding to the default input
	 *               folder.
	 * @return Output file URI, including any relative path under "src" folder.
	 */
	public static URI getCompiledURI(URI source) {
		return getCompiledURI(source, null);
	}

	/**
	 * Returns resource URI for output (compiled) file. The output file will be
	 * created under "src-gen" folder and will retain any relative folder path that
	 * "source" had relative to "src" folder.
	 * 
	 * @param source The URI for the input (source) file. It is expected that this
	 *               contains a fragment "src" corresponding to the default input
	 *               folder.
	 * @param ext    New file extension.
	 * @return Output file URI, including any relative path under "src" folder.
	 */
	public static URI getCompiledURI(URI source, String ext) {
		String result = source.toPlatformString(true);
		result = result.replaceAll("/src/", "/src-gen/");
		if (ext != null)
			result = replaceExtension(result, ext);
		return URI.createPlatformResourceURI(result, true);
	}

	/**
	 * Returns a stream to write output (compiled) file. The output file will be
	 * created under "src-gen" folder and will retain any relative folder path that
	 * "source" had relative to "src" folder.
	 * 
	 * @param resource The resource for the input (source) file. It is expected that
	 *                 this is located under "src" folder in a project.
	 * @param ext      New file extension.
	 * @return Output file URI, including any relative path under "src" folder.
	 * @throws IOException
	 */
	public static OutputStream getCompiledOutputStream(Resource resource, String ext) throws IOException {
		return resource.getResourceSet().getURIConverter()
				.createOutputStream(FileUtil.getCompiledURI(resource.getURI(), ext));
	}
}

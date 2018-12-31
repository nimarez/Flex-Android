package org.avinalabs.flex;

import android.content.Context;
import android.util.Log;

/**
 * Created by Shomil Jain on 11/23/17.
 * <p>
 * © The Filos Project, Inc.
 */

public class Out {

    private static final String TAG = "F_LOG";

    private static String getSource() {
        StackTraceElement[] list = Thread.currentThread().getStackTrace();
        StackTraceElement i = list[4];
        String[] l = i.getMethodName().split("/");
        return "(" + i.getFileName().replace(".java", "") + "/" + l[l.length - 1] + ")";
    }

    // This does not go into the
    public static void print(Object o) {
        String message = "(PRINT)\t" + getSource() + " " + o.toString();
        writeToFile(message);
        Log.v(TAG, message);
    }

    public static void m() {
        String message = "(METHOD)\t" + getSource();
        writeToFile(message);
        Log.i(TAG, message);
    }

    public static void v(Object o) {
        String message = "(VERBOSE)" + getSource() + " " + o.toString();
        writeToFile(message);
        Log.v(TAG, message);
    }

    // Used for debugging purposes.
    // Shouldn't be used.
    public static void d(Object o) {
        String message = "(DEBUG)\t" + getSource() + " " + o.toString();
        writeToFile(message);
        Log.d(TAG, message);
    }

    public static void w(Object o) {
        String message = "(WARN)\t" + getSource() + " " + o.toString();
        writeToFile(message);
        Log.w(TAG, message);
    }

    public static void e(Object o) {
        String message = "(ERROR)\t" + getSource() + " " + o.toString();
        writeToFile(message);
        Log.e(TAG,  message);
    }

    public static void wtf(Object o) {
        String message = "(FAILURE)\t" + getSource() + " " + o.toString();
        writeToFile(message);
        Log.wtf(TAG, message);
    }

    public static void writeToFile(String message) {
        /*
        try {
            final Path path;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                path = Paths.get("filosLogs.txt");

                Files.write(path, Collections.singletonList(message), StandardCharsets.UTF_8,
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            } else {
                Log.e("F_LOG", "{ERROR} idk: ");
            }
        } catch (final IOException ignored) {
            Log.e("F_LOG", "{ERROR} idk2: " + ignored.getLocalizedMessage());
        }*/
    }

    public static String readFromFile(Context context) {

        /*
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("filosLogs.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("F_LOG", "{ERROR} File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("F_LOG", "{ERROR} Can not read file: " + e.toString());
        }

        return ret;
*/
        return "";
    }

}

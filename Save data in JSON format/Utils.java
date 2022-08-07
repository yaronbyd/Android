package com.example.appdata;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    /**
     * taken from: https://stackoverflow.com/a/13357785
     */
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    /**
     * based on: https://stackoverflow.com/a/13357785
     */
    public static String getStringFromFile (Context context, String fileName)  {
        try {
            File path = context.getFilesDir();
            File fl = new File(path, fileName);
            FileInputStream fin = new FileInputStream(fl);
            String ret = convertStreamToString(fin);
            //Make sure you close all streams.
            fin.close();
            return ret;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error saving,,,", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static void writeToFile(Context context, String fileName, String content)
    {
        File path = context.getFilesDir();
        File file = new File(path, fileName);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(content.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error loading data,,,", Toast.LENGTH_SHORT).show();
        }

    }
}

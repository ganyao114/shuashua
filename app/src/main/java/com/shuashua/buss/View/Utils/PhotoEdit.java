package com.shuashua.buss.View.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.net.URI;

/**
 * Created by pc on 16/8/12.
 */
public class PhotoEdit {
    public static final int REQUESTCODE_PICK = 0;
    public static final int REQUESTCODE_TAKE = 1;
    public static final int REQUESTCODE_CUTTING = 2;

    public static void takePt(Activity context, String IMAGE_FILE_NAME){
        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        context.startActivityForResult(takeIntent, REQUESTCODE_TAKE);
    }

    public static void PickPt(Activity context, String IMAGE_FILE_NAME){
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        context.startActivityForResult(pickIntent, REQUESTCODE_PICK);
    }

    public static void zoomPt(Activity context,Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 856);
        intent.putExtra("aspectY", 540);
        intent.putExtra("outputX", 856);
        intent.putExtra("outputY", 540);
        intent.putExtra("scale", true);//黑边
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, REQUESTCODE_CUTTING);
    }



}

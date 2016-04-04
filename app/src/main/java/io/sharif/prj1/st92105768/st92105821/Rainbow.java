package io.sharif.prj1.st92105768.st92105821;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

/**
 * Created by alireza on 4/4/16.
 */
public class Rainbow extends CharacterStyle {
    private int[] colors;
    public Rainbow(Context context){
        colors = context.getResources().getIntArray(R.array.rainbow);
    }
    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setStyle(Paint.Style.FILL);
        Shader shader = new LinearGradient(0,0,0,tp.getTextSize()*colors.length,colors,null,Shader.TileMode.MIRROR);
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);
        tp.setShader(shader);
    }
}

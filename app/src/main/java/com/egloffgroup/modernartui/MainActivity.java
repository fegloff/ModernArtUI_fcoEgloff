package com.egloffgroup.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;


public class MainActivity extends Activity {


    private View mRec1,mRec2,mRec3,mRec5;

    static String TAG = "MondernArtUI-FcoEgloff";
    static String MOMA = "https://www.moma.org/artists/4057";

    private static final int MENU_MORE = Menu.FIRST;
    private static final int MENU_EXIT = Menu.FIRST + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recangles_constraint);

        SeekBar seekBar;

        seekBar = findViewById(R.id.seekBar);
        mRec1 = findViewById(R.id.rectangle1);
        mRec2 = findViewById(R.id.rectangle2);
        mRec3 = findViewById(R.id.rectangle3);
        mRec5 = findViewById(R.id.rectangle5);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i(TAG,"seekBar progress" + seekBar.getProgress());

                mRec1.setBackgroundColor (lighter(mRec1,seekBar.getProgress()));
                mRec2.setBackgroundColor (lighter(mRec2,seekBar.getProgress()));
                mRec3.setBackgroundColor (lighter(mRec3,seekBar.getProgress()));
                mRec5.setBackgroundColor (lighter(mRec5,seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /*
     *  Function to change the alpha level of non white rectangles
     */
    private int lighter (View rec, int factor){
        ColorDrawable bckColor = (ColorDrawable) rec.getBackground();
        int c = bckColor.getColor();
        int red = Color.red(c);
        int blue = Color.blue(c);
        int green = Color.green(c);
        return Color.argb(255 - factor,red,blue,green);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, MENU_MORE, Menu.NONE, R.string.menu_more);
        menu.add(Menu.NONE, MENU_EXIT, Menu.NONE, R.string.menu_exit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_MORE:
                startDialog();
                return true;
            case MENU_EXIT:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
     *   Method to create the AlertDialog.
     *   It uses the Layout dialog_layout.xml to to change font color and type, but also to change
     *   backgpround color.
     */
    private void startDialog() {
        View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setView(dialogLayout);
        alertBuilder.setCancelable (false);

        alertBuilder.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(MOMA));
                startActivity(intent);
            }
        });
        alertBuilder.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

       // alertBuilder.show();
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
        Button posButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);

        //posButton.
        posButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        posButton.setTextColor(Color.WHITE);

        negButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        negButton.setTextColor(Color.WHITE);

    }


}

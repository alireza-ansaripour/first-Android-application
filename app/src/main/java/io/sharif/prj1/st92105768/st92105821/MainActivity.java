package io.sharif.prj1.st92105768.st92105821;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.SpannableString;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RotateAnimation anim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1000);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.startAnimation(anim);
        final ImageView golang =(ImageView)findViewById(R.id.imageView1);
        Button btnup=(Button)findViewById(R.id.button1);
        Button btndown=(Button)findViewById(R.id.button2);
        Button btnright=(Button)findViewById(R.id.button3);
        Button btnleft=(Button)findViewById(R.id.button4);
        SharedPreferences settings = getSharedPreferences("settings",0);
        golang.setX(settings.getFloat("x", 0));
        golang.setY(settings.getFloat("y", 0));



        btnup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                golang.setY(golang.getY() - 5);
            }
        });

        btndown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                golang.setY(golang.getY() + 5);
            }
        });

        btnright.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                golang.setX(golang.getX() + 5);
            }
        });

        btnleft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                golang.setX(golang.getX() - 5);
            }
        });
        ImageButton popupMenu = (ImageButton)findViewById(R.id.popup_menu);
        popupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.popup, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        SharedPreferences sharedPreferences = getSharedPreferences("settings", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        final ImageView golang =(ImageView)findViewById(R.id.imageView1);
                        switch (item.getItemId()) {
                            case R.id.new_game:
                                LayoutInflater inflater = getLayoutInflater();
                                View view = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast));
                                Toast toast = new Toast(getApplicationContext());
                                toast.setView(view);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                golang.setX(0);
                                golang.setY(0);
                                editor.putFloat("x", 0);
                                editor.putFloat("y", 0);
                                editor.commit();
                                toast.show();
                                break;
                            case R.id.save_game:
                                editor.putFloat("x",golang.getX());
                                editor.putFloat("y",golang.getY());
                                editor.commit();
                                break;
                        }
                        return false;
                    }
                });
                menu.show();


            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            MyDialog myDialog = new MyDialog();
            myDialog.show(getFragmentManager().beginTransaction(),"Dialog");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onStop() {
//        SharedPreferences sharedPreferences = getSharedPreferences("settings",0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        final ImageView golang =(ImageView)findViewById(R.id.imageView1);
//        editor.putFloat("x",golang.getX());
//        editor.putFloat("y",golang.getY());
//        editor.commit();
//        super.onStop();
//    }
}

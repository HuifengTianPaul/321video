package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.drawable.Drawable;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Bundle;
        import android.text.Html;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.MediaController;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.VideoView;

        import java.lang.reflect.Field;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText videoSearch;  //查找用户感兴趣的视频 Search videos' name
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoSearch = findViewById(R.id.videoSearch);   //获取输入的关联字 get words from Search

        Button button = findViewById(R.id.VideoSkip1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=ZvK_wHjmL-Q");    //设置跳转的网站
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.cancervideo);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);



    }
    public void Video(View v){
        //创建一个String类，储存从输入文本框获取到的内容
        //Create one string classes to store the content obtained from the input text box
        String newVideoSearch = videoSearch.getText().toString().trim();
        TextView t1 = (TextView) findViewById(R.id.txtOne);
        String s1 = "图片：<img src = 'icon'/><br>";
        t1.setText(Html.fromHtml(s1, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable draw = null;
                try {
                    Field field = R.drawable.class.getField(source);
                    int resourceId = Integer.parseInt(field.get(null).toString());
                    draw = getResources().getDrawable(resourceId);
                    draw.setBounds(0, 0, draw.getIntrinsicWidth(), draw.getIntrinsicHeight());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return draw;
            }
        }, null));



    }

}

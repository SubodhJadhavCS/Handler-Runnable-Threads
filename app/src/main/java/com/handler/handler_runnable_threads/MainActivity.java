package com.handler.handler_runnable_threads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    Handler handler;
    //(4)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.Textview);
        button  = findViewById(R.id.start_thread);
        // (1) handler = new Handler();



       handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Toast.makeText(MainActivity.this, msg.obj+"message from looper", Toast.LENGTH_SHORT).show();
            }
        };


        Execute_code();
    }



  /*   (1) private void Execute_code() {

        //this is starting another thread doing work on it and updating the ui also by it
         handler.post(new Runnable() {
             @Override
             public void run() {
                 Toast.makeText(MainActivity.this, "Text updated", Toast.LENGTH_SHORT).show();
             }
         });

    }*/

  //  (2)   by using runnable
  /*    private void Execute_code() {


   Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (this){
               for(int i=0;i<1000;i++){
                   try {
                       Thread.sleep(1000);
                       textView.setText(i+"  value updating...");
//it do not update ui thread but crash the app
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }
    };

    Thread thread = new Thread(runnable);
    thread.start();


    }*/





// (3) by button click

//    public void Start_thread(View view) {        Execute_code();//works with another view hierarchy also
//
//    }




  /* (4) here we sending data to handler and he publishing it  now we are sending from ui thread
      but we can send from  runnable java thread background and publish our result on the main ui thread by sending it to
      handler message argument
   */
    private void Execute_code() {
      Runnable runnable = new Runnable() {
          @Override
          public void run() {

              Message message = Message.obtain();
              message.obj = "this is string";
              handler.sendMessage(message);
          }
      };
        Thread thread = new Thread(runnable);
        thread.start();
    }


}


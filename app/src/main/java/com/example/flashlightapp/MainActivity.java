package com.example.flashlightapp;


import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;

import com.example.flashlightapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewGroup customLayout = findViewById(R.id.layout);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.button.getText().toString().equals("Turn on")){
                    binding.FlashImage.setImageResource(R.drawable.on);
                     // Replace with your layout ID
                    customLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                    binding.FlashImage.setBackgroundColor(getResources().getColor(R.color.blue));

                    binding.button.setText("Turn off");
                    changeLightState(true);

                }else if(binding.button.getText().toString().equals("Turn off")){
                    customLayout.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                    binding.FlashImage.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                    binding.FlashImage.setImageResource(R.drawable.off);
                    binding.button.setText("Turn on");
                    changeLightState(false);
                }

            }
        });
    }

    private void changeLightState(boolean state) {
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        String camId = null;
        try{
            camId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camId,state);
        }
       catch(CameraAccessException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText("Turn on");
    }
}
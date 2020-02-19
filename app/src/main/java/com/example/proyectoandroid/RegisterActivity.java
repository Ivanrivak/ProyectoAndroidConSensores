package com.example.proyectoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import static android.hardware.Sensor.TYPE_LIGHT;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText password2;
    private TextView registroEmail;
    private TextView registroPass;
    private TextView registroPass2;
    private Button cancelar;
    private Button confirmar;
    private FirebaseAuth firebaseAuth;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private View registerVista;
    private float maxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        cancelar = findViewById(R.id.cancelar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmar = findViewById(R.id.confirmar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        registroEmail = findViewById(R.id.registroEmail);
        registroPass = findViewById(R.id.registroPass);
        registroPass2 = findViewById(R.id.registroPass2);
        registerVista = findViewById(R.id.registerVista);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
        maxValue = lightSensor.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                int newValue = (int) (255f * value / maxValue);
                newValue = newValue * 50;
                //root.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
                if (newValue < 200) {
                    email.setHintTextColor(Color.LTGRAY);
                    password.setHintTextColor(Color.LTGRAY);
                    password2.setHintTextColor(Color.LTGRAY);
                    registroEmail.setTextColor(Color.WHITE);
                    registroPass.setTextColor(Color.WHITE);
                    registroPass2.setTextColor(Color.WHITE);
                    registerVista.setBackgroundColor(Color.BLACK);

                } else {
                    email.setHintTextColor(Color.LTGRAY);
                    password.setHintTextColor(Color.LTGRAY);
                    password2.setHintTextColor(Color.LTGRAY);
                    registroEmail.setTextColor(Color.BLACK);
                    registroPass.setTextColor(Color.BLACK);
                    registroPass2.setTextColor(Color.BLACK);
                    registerVista.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = findViewById(R.id.email);
                password = findViewById(R.id.password);
                password2 = findViewById(R.id.password2);

                if (email.getText().toString().equals("")) return;
                if (!password.getText().toString().equals(password2.getText().toString())) return;
                if (password.getText().toString().equals("") || password.getText().toString().equals(""))
                    return;

                Log.d("correo", "correo : " + email.getText().toString());
                Log.d("titulomensaje", "contraseña : " + password.getText().toString());
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();
                registrarUsuario(textEmail, textPassword);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }


    private void registrarUsuario(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Authentication success.", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

    }
}

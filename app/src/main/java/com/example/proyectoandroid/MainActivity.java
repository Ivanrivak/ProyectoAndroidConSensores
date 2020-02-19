package com.example.proyectoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.FirebaseMethods;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;

import static android.hardware.Sensor.TYPE_LIGHT;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView mainLabelEmail;
    private TextView mainLabelPass;
    private Button register;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private FirebaseMethods firebase;
    private HashMap<String, HashMap<String, String>> listaAlmacen;
    private HashMap<String, HashMap<String, String>> listaAutores;
    private HashMap<String, HashMap<String, String>> listaLibros;
    private HashMap<String, HashMap<String, String>> listaNoticias;
    private HashMap<String, HashMap<String, String>> listaCompras;
    private HashMap<String, HashMap<String, HashMap<String, String>>> listado;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private View mainVista;
    private float maxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLabelEmail = (TextView) findViewById(R.id.mainLabelEmail);
        mainLabelPass = (TextView) findViewById(R.id.mainLabelPass);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.password);

        firebase = new FirebaseMethods();
        listaAlmacen = firebase.obtenerAlmacen();
        listaAutores = firebase.obtenerAutores();
        listaLibros = firebase.obtenerLibros();
        listaNoticias = firebase.obtenerNoticias();
        listaCompras = firebase.obtenerCompras();

        firebaseAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = findViewById(R.id.correo);
                password = findViewById(R.id.password);
                String emailText = email.getText().toString();
                String passText = password.getText().toString();
                if (!emailText.equals("") && !passText.equals("")) {
                    loginUsuario(emailText, passText);
                } else return;
            }
        });

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


        mainVista = findViewById(R.id.mainVista);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);

        if (lightSensor == null) {
            Toast.makeText(this, "El dispositivo no tiene sensor de luz ", Toast.LENGTH_SHORT).show();

        }
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
                    email.setTextColor(Color.WHITE);
                    password.setTextColor(Color.WHITE);
                    mainLabelEmail.setTextColor(Color.WHITE);
                    mainLabelPass.setTextColor(Color.WHITE);
                    mainVista.setBackgroundColor(Color.BLACK);

                } else {
                    email.setTextColor(Color.BLACK);
                    email.setHintTextColor(Color.LTGRAY);
                    password.setTextColor(Color.BLACK);
                    password.setHintTextColor(Color.LTGRAY);
                    mainLabelEmail.setTextColor(Color.BLACK);
                    mainLabelPass.setTextColor(Color.BLACK);
                    mainVista.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

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

    private void loginUsuario(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Bundle extras = new Bundle();
                            listado = new HashMap<String, HashMap<String, HashMap<String, String>>>();
                            listado.put("listaAlmacen", listaAlmacen);
                            listado.put("listaAutores", listaAutores);
                            listado.put("listaLibros", listaLibros);
                            listado.put("listaNoticias", listaNoticias);
                            listado.put("listaCompras", listaCompras);
                            extras.putSerializable("listado", listado);

                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            i.putExtras(extras);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

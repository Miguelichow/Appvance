package com.example.appvance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase que maneja el registro de usuarios
 * @author Miguel Prieto Horcajo
 */
public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText etUser, etPass, etPass2;
    Button btRegister;
    Intent intent;
    String nombre, pass, pass2;
    SQLiteHelperUsers sqliteHelperUsers;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        etUser = this.findViewById(R.id.etUser);
        etPass = this.findViewById(R.id.etPass);
        etPass2 = this.findViewById(R.id.etPass2);
        etPass2.setOnEditorActionListener(editorActionListener);

        btRegister = this.findViewById(R.id.btRegister);
        btRegister.setOnClickListener(this);

        sqliteHelperUsers = new SQLiteHelperUsers(this);
    }

    /**
     * Controla la función de la tecla "intro" del teclado
     */
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onClick(btRegister);
                return true;
            }
            return false;
        }
    };

    /**
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        nombre = etUser.getText().toString().trim();
        pass = etPass.getText().toString().trim();
        pass2 = etPass2.getText().toString().trim();

        if(!checkPass(pass, pass2)) {
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else if (!sqliteHelperUsers.isNameExists(nombre)) {
            sqliteHelperUsers.addUser(new User(null, nombre, pass));
            Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(getApplicationContext(), "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Compara las contraseñas
     * @param pass Contraseña
     * @param pass2 Contraseña 2
     * @return Devuelve true o false dependiendo del resultado
     */
    public boolean checkPass(String pass, String pass2) {
        if(pass.equals(pass2)) {
            return true;
        }
        return false;
    }
}

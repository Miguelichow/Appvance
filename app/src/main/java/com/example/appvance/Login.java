package com.example.appvance;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Clase que maneja el inicio de sesión del usuario
 * @author Miguel Prieto Horcajo
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText etUser, etPass;
    Button btLogin;
    TextView tVRegister;
    Intent intent;
    String nombre, pass;
    SQLiteHelperUsers sqliteHelperUsers;
    User currentUser;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        etPass.setOnEditorActionListener(editorActionListener);

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        tVRegister = findViewById(R.id.tVRegister);
        tVRegister.setOnClickListener(this);

        sqliteHelperUsers = new SQLiteHelperUsers(this);
    }

    /**
     * Controla la función de la tecla "intro" del teclado
     */
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onClick(btLogin);
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
        switch(v.getId()) {
            case R.id.btLogin:
                nombre = etUser.getText().toString().trim();
                pass = etPass.getText().toString().trim();

                checkFields(nombre, pass);

                currentUser = sqliteHelperUsers.Authenticate(new User(null, nombre, pass));

                if (currentUser != null) {
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.tVRegister:
                intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * Comprueba si los campos se encuentran vacíos
     * @param nombre Nombre de usuario
     * @param pass Contraseña del usuario
     */
    public void checkFields(String nombre, String pass) {
        if(nombre.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Existe algún campo vacío", Toast.LENGTH_SHORT).show();
        }
    }
}

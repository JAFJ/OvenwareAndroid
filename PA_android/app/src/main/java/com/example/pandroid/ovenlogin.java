package com.example.pandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pandroid.json.Jsonsito;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ovenlogin extends AppCompatActivity {
    public static List<Jsonsito> list;
    public static String TAG = "mensaje";
    public static String json = null;
    public static String usr,pswd;
    private Button button1, buttoncontra;
    private TextView button2;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ovenlogin);

        /*Para el texto vinculoso*/
        /************************************/
        textView=(TextView)findViewById(R.id.textView6);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ovenlogin.this, registro.class);
                startActivity(intent);
            }
        });
        /************************************/

        button1 = findViewById(R.id.button_ingresar);
        button2 = findViewById(R.id.textView6);
        buttoncontra = findViewById(R.id.buttoncontra);
        EditText correito = findViewById(R.id.correito);
        EditText contra = findViewById(R.id.contra);
        Leer();
        JsonLista(json);
        if (json == null || json.length() == 0){
            button1.setEnabled(false);
            buttoncontra.setEnabled(false);
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr = String.valueOf(correito.getText());
                pswd = String.valueOf(contra.getText())+ usr;
                pswd = Metoditos.bytesToHex(Metoditos.createSha1(pswd));
                pasale(usr , pswd);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ovenlogin.this, registro.class);
                startActivity(intent);
            }
        });
        buttoncontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr = String.valueOf(correito.getText());
                pswd = Metoditos.bytesToHex(Metoditos.createSha1(String.valueOf(contra.getText())));
                if(usr.equals("")||pswd.equals("")){
                    Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(ovenlogin.this, contraOlvido.class);
                    startActivity(intent);
                }
            }
        });
    }
    public boolean Leer(){
        if(!isFileExits()){
            return false;
        }
        File file = getFile();
        FileInputStream fileInputStream = null;
        byte[] bytes = null;
        bytes = new byte[(int)file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            json=new String(bytes);
            Log.d(TAG,json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void JsonLista( String json )
    {
        Gson gson = null;
        String mensaje = null;
        if (json == null || json.length() == 0)
        {

            Toast.makeText(getApplicationContext(), "Error json null or empty", Toast.LENGTH_LONG).show();
            return;
        }
        gson = new Gson();
        Type listType = new TypeToken<ArrayList<Jsonsito>>(){}.getType();
        list = gson.fromJson(json, listType);
        if (list == null || list.size() == 0 )
        {
            Toast.makeText(getApplicationContext(), "Error list is null or empty", Toast.LENGTH_LONG).show();
            return;
        }
    }
    private File getFile( )
    {
        return new File( getDataDir() , registro.archivo );
    }
    private boolean isFileExits( )
    {
        File file = getFile( );
        if( file == null )
        {
            return false;
        }
        return file.isFile() && file.exists();
    }
    public void pasale(String usr , String pswd){
        int i=0;
        if(usr.equals("")||pswd.equals("")){
            Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
        }else{
            for(Jsonsito myInfo : list){
                if(myInfo.getUsuario().equals(usr)&&myInfo.getContraseña().equals(pswd)){
                    Intent intent = new Intent(ovenlogin.this, principal.class);
                    startActivity(intent);
                    i=1;
                }
            }
            if(i==0){
                Toast.makeText(getApplicationContext(), "El usuario o contraseña son incorrectos", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void olvidar_contrasena(String usr, String pswd){
        if(usr.equals("")||pswd.equals("")){
            Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(ovenlogin.this,Olvide.class);
            startActivity(intent);
        }
    }
}
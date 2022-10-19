package com.example.pandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


import com.example.pandroid.json.Jsonsito;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class registro extends AppCompatActivity implements View.OnClickListener {

    private Button clic, button, button2;
    private EditText usuario, nombre, contra, correito, numtel, fecha;
    private CheckBox box1, box2;
    private Spinner spinner;
    private RadioButton r1, r2;
    private int dia, mes, año;
    private static final String TAG="MainActivity";
    public static final String archivo = "archivito.json";
    String json = null;
    public static String usr, nom, con, corre, numcel, fec, esta;
    public static boolean sw=false;
    public static boolean booleado;
    public static String[] box = new String[2];
    public static List<Jsonsito> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        spinner = findViewById(R.id.spinner);
        String [] opciones = {"estudiante","egresado","creditos","dictaminado"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        spinner.setAdapter(adapter);
        clic= findViewById(R.id.clic);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        usuario = findViewById(R.id.usuario);
        nombre = findViewById(R.id.nombre);
        contra = findViewById(R.id.contra);
        correito = findViewById(R.id.correito);
        numtel = findViewById(R.id.num);
        fecha = findViewById(R.id.fec);
        fecha.setEnabled(false);

        box1 = findViewById(R.id.checkBox);
        box2 = findViewById(R.id.checkBox2);

        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);

        Switch switch1 = findViewById(R.id.switch1);
        Leer();
        JsonLista(json);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(registro.this, ovenlogin.class);
                startActivity(intent);
            }
        });
        clic.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jsonsito info = new Jsonsito();

                usr = String.valueOf(usuario);
                nom = String.valueOf(nombre);
                con = String.valueOf(contra);
                corre = String.valueOf(correito);
                numcel = String.valueOf(numcel);
                fec = String.valueOf(fecha);
                esta = spinner.getSelectedItem().toString();

                if(box1.isChecked()==true){
                    box[0]="opcion1";
                }else{
                    box[0]="no";
                }
                if(box2.isChecked()==true){
                    box[1]="opcion2";
                }else{
                    box[1]="no";
                }
                if(r1.isChecked()==true){
                    booleado=true;
                }
                if(r2.isChecked()==true){
                    booleado=true;
                }
                if(switch1.isChecked()){
                    sw= true;
                }
                //Validaciones
                if(usr.equals("")||con.equals("")||corre.equals("")){
                    Log.d(TAG,"vacio");
                    Log.d(TAG,usr);
                    Log.d(TAG,con);
                    Log.d(TAG,corre);
                    Toast.makeText(getApplicationContext(), "LLena los campos", Toast.LENGTH_LONG).show();
                }else{
                    if(Metoditos.validarEmail(corre)){
                        if(list.isEmpty()){
                            Log.d(TAG,"lleno");
                            Metoditos.fillInfo(info);
                            JsonLista(info,list);
                        }else{
                            if(Metoditos.usuarios(list,usr)){
                                Log.d(TAG,"Este usuario ya se registro, las personalidades multiples no valen");
                                Toast.makeText(getApplicationContext(), "Este usuario ya se registro, las personalidades multiples no valen", Toast.LENGTH_LONG).show();
                            }else{
                                Metoditos.fillInfo(info);
                                JsonLista(info,list);
                            }
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Correo invalido!!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public void JsonLista (Jsonsito info, List<Jsonsito> list){
        Gson gson =null;
        String json= null;
        gson =new Gson();
        list.add(info);
        json =gson.toJson(list, ArrayList.class);
        if (json == null)
        {
            Log.d(TAG, "Error en json, json == null");
        }
        else
        {
            Log.d(TAG, json);
            escribirArchivo(json);
        }
        Toast.makeText(getApplicationContext(), "Vamooo todo bieeen", Toast.LENGTH_LONG).show();
    }
    private boolean escribirArchivo(String text){
        File file =null;
        FileOutputStream fileOutputStream =null;
        try{
            file=obtenerArchivo();
            fileOutputStream = new FileOutputStream( file );
            fileOutputStream.write( text.getBytes(StandardCharsets.UTF_8) );
            fileOutputStream.close();
            Log.d(TAG, "Buenas tardes");
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    private File obtenerArchivo(){
        return new File(getDataDir(),archivo);
    }

    public boolean Leer(){
        if(!siArchivoExiste()){
            return false;
        }
        File file = obtenerArchivo();
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
        return true;
    }
    private boolean siArchivoExiste( )
    {
        File file = obtenerArchivo( );
        if( file == null )
        {
            return false;
        }
        return file.isFile() && file.exists();
    }
    public void JsonLista( String json)
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

    @Override
    public void onClick(View v) {
        if(v==clic){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            año = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    fecha.setText(i2+"/"+(i1+1)+"/"+i);
                }
            }
                    ,dia,mes,año);
            datePickerDialog.show();
        }
    }
}

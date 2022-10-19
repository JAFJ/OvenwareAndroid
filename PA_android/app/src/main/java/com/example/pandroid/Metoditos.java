package com.example.pandroid;

import androidx.core.util.PatternsCompat;
import com.example.pandroid.json.Jsonsito;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Metoditos {


    public static final String TAG = "Saul";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    //Metodos Sha1
    public static byte[] createSha1( String text )
    {
        MessageDigest messageDigest = null;
        byte[] bytes = null;
        byte[] bytesResult = null;
        try
        {
            messageDigest = MessageDigest.getInstance("SHA-1");
            bytes = text.getBytes("iso-8859-1");
            messageDigest.update(bytes, 0, bytes.length);
            bytesResult = messageDigest.digest();
            return bytesResult;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    //Metodos validacion
    public static boolean validarEmail(String email){
        boolean bandera;
        if(email.isEmpty()){
            bandera=false;
        }else{
            if(PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
                bandera=true;
            }else{
                bandera=false;
            }
        }
        return bandera;
    }
    public static boolean usuarios(List<Jsonsito> list, String usr){
        boolean bandera = false;
        for(Jsonsito informacion : list){
            if(informacion.getUsuario().equals(usr)){
                bandera=true;
            }
        }
        return bandera;
    }
    //Metodos JSON el getFile tiene bug
    //Metodo de llenado
    public static void fillInfo(Jsonsito info){
        info.setUsuario(registro.usr);
        String pass = registro.con + registro.usr;
        info.setContraseña(bytesToHex(createSha1(pass)));
        info.setNumerotel(registro.numcel);
        info.setFecha(registro.fec);
        info.setClien_vend(registro.box);
        info.setCorreoelec(registro.corre);
        info.setEstado(registro.esta);
        info.setTipopago(registro.booleado);
        info.setBooleador(registro.sw);
        info.setNombre(registro.nom);
    }
    public static void vaciaJson(String json){
        json = null;
    }
    //Metodos encuentra contrasena
    public static void encuentra(String cadena){
        for(Jsonsito info: Olvide.list){
            if(ovenlogin.usr.equals(info.getUsuario())){
                cadena = "El usuario existe, recuerde la contraseña";
            }else{
                cadena = "El usuario no existe, recuerde todo";
            }
        }
    }


}

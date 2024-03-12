package com.example.testelaunch;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton botao;
    private LinearLayout frameArquivos;
    //private ActivityResultLauncher<Void> lauchFoto;
    private ImageView imagem;
    private ImageView imagemDeck;
    private AlertDialog dialog;
    private Bitmap bitmap;
    private Intent intentFoto;
    private ActivityResultLauncher<Void> launchFoto = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(),
    result -> {
        if(result != null){
            imagem.setImageBitmap(result);
            bitmap = result;
            inserirGaleria(bitmap);
        }});
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarCampos();

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoMidia();
                //lauchFoto.launch(null);
            }
        });
    }
    public void criarCampos(){
        botao = findViewById(R.id.botao);
        frameArquivos = findViewById(R.id.arquivos);
        imagem = findViewById(R.id.imagem);
    }
    public void inserirGaleria(Bitmap result){
        View childImage = LayoutInflater.from(MainActivity.this).inflate(R.layout.imagem, null);
        childImage.setId(frameArquivos.getChildCount()+1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10,0,10,0);
        //childImage.setLayoutParams(layoutParams);
        imagemDeck = childImage.findViewById(R.id.imagemDeck);
        imagemDeck.setImageBitmap(result);
        frameArquivos.addView(childImage);
    }

    public void dialogoMidia(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.selecionar_arquivo,null);
        alerta.setView(dialogView);
        AppCompatButton galeria = dialogView.findViewById(R.id.galeria);
        AppCompatButton documento = dialogView.findViewById(R.id.documento);
        AppCompatButton foto = dialogView.findViewById(R.id.foto);
        AppCompatButton fechar = dialogView.findViewById(R.id.fechar);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        documento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFoto.launch(null);
                dialog.dismiss();
            }
        });
        fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog = alerta.create();
        dialog.show();
    }
}
package com.example.testelaunch;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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
    private ActivityResultLauncher<Void> lauchFoto;
    private ImageView imagem;
    private ImageView imagemDeck;
    private Bitmap bitmap;
    private Intent intentFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarCampos();
        lauchFoto = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(),
                result -> {
            if(result != null){
                imagem.setImageBitmap(result);
                bitmap = result;
                inserirGaleria(bitmap);
            }});
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lauchFoto.launch(null);
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
}
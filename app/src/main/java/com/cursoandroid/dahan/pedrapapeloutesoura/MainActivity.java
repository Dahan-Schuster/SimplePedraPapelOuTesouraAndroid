package com.cursoandroid.dahan.pedrapapeloutesoura;

import android.graphics.drawable.Icon;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static TextView txtResultado;

    public static final int PEDRA = 0;
    public static final int PAPEL = 1;
    public static final int TESOURA = 2;

    public static final int USUARIO = -1;
    public static final int EMPATE = -2;
    public static final int APP = -3;

    int jogadaDoApp;
    int jogada;

    private TextView txtSuaEscolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSuaEscolha = (TextView) findViewById(R.id.txtSuaEscolha);
    }

    public void jogarPedra(View view) {
        txtSuaEscolha.setText("Sua escolha: PEDRA");
        jogar(PEDRA);
    }

    public void jogarPapel(View view) {
        txtSuaEscolha.setText("Sua escolha: PAPEL");
        jogar(PAPEL);
    }

    public void jogarTesoura(View view) {
        txtSuaEscolha.setText("Sua escolha: TESOURA");
        jogar(TESOURA);
    }

    private void jogar(int jogada) {

        this .jogada = jogada;

        gerarJogadaDoAppAleatoriamente();

        sicronizarIconeComJogada();

        String textoResultadoDoJogo = verificarResultado();

        anunciarTexto(textoResultadoDoJogo);
    }

    private void gerarJogadaDoAppAleatoriamente() {
        this.jogadaDoApp = new Random().nextInt(3); // 0 .. 2
    }

    public void sicronizarIconeComJogada() {
        int id = getIconeCorrespondenteAJogadaDoApp();

        setIcone(id);
    }

    private int getIconeCorrespondenteAJogadaDoApp() {
        if (jogadaDoApp == PEDRA) return R.drawable.pedra;           /* =     =     =     = */

        else if (jogadaDoApp == PAPEL) return R.drawable.papel;      /* =  triple return  = */

        return R.drawable.tesoura; // jogadaDoApp == TESOURA;        /* =     =     =     = */
    }

    private void setIcone(int id){

        ImageButton imageButtonApp = (ImageButton) findViewById(R.id.ibtnApp);

        imageButtonApp.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), id));
    }

    private String verificarResultado() {

        if (vencedor() == USUARIO) return  getString(R.string.usuario_ganhou);       /* =     =     =     = */

        else if (vencedor() == EMPATE) return getString(R.string.empate);            /* =  triple return  = */

        return getString(R.string.app_ganhou);                                       /* =     =     =     = */
    }

    private int vencedor(){

        if (jogada - jogadaDoApp == 1 || jogada - jogadaDoApp == -2) return  USUARIO;           /* =     =     =     = */

        else if (jogada == jogadaDoApp)  return EMPATE;                                         /* =  triple return  = */

        return APP;                                                                             /* =     =     =     = */
    }

    private void anunciarTexto(String textoResultadoDoJogo) {
        txtResultado = (TextView) findViewById(R.id.txtResultado);

        txtResultado.setText(textoResultadoDoJogo);
    }


}

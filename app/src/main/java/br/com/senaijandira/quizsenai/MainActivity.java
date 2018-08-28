package br.com.senaijandira.quizsenai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    // Declaração dos componentes visuais
    LinearLayout linear ;
    TextView txtPergunta;
    Button btn1, btn2;

    int indexPergunta;
    int QtdAcertos = 0;
    int QtdErros = 0;

    // base de perguntas
    String[] perguntas = {
            "Onde se passa a série breaking bad ?",
            "Qual personagem principal da série?",
            "Quantas temporadas tem a série?",
            "Qual era o nome da lanchonete do Gustavo Fring?"
    };

    String[][] respostas = {
            {"Albuquerque","New York"},
            {"Saul Goodman", "Walter White"},
            {"5 temporadas","3 temporadas"},
            {"Burguer King", "los polos hermanos"}
    };

    int[] gabarito = {0, 1, 0, 1};  //posicoes dos vetores certos

    //Ações do botão
    View.OnClickListener clickResposta = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //verificando qual botao foi clicado
            int tag = (int) v.getTag();

            // pegando a resposta correta
            int respostaCorreta = gabarito[indexPergunta];



            if (tag == respostaCorreta){
                alert("Correto", "Resposta correta \uD83D\uDE03");
                QtdAcertos++;
            }else {
                alert("Errou", "Resposta errada \uD83D\uDE29");
                QtdErros++;
            }



        }
    };

    private void gameOver(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Game over");
        alert.setMessage("Qtd acertos:" + QtdAcertos+ "\nQtd Erros:" + QtdErros);

        alert.setNegativeButton("Finalizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Fechar App
                finish();
            }
        });

        alert.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //reiniciar jogo
                iniciarJogo();
            }
        });
        alert.create().show();
    }

    private void proximaPergunta() {

        if (indexPergunta == perguntas.length - 1){
            // fim de jogo
            gameOver();
            return;
        }

        indexPergunta++;

        // definindo a pergunta de acordo cm o indice
        txtPergunta.setText(perguntas[indexPergunta]);

        //definindo as resposta com a pergunta
        btn1.setText(respostas[indexPergunta][0]);
        btn2.setText(respostas[indexPergunta][1]);

    }

    private  void iniciarJogo(){
        indexPergunta = 0;

        // definindo a pergunta de acordo cm o indice
        txtPergunta.setText(perguntas[indexPergunta]);

        //definindo as resposta com a pergunta
        btn1.setText(respostas[indexPergunta][0]);
        btn2.setText(respostas[indexPergunta][1]);

        //definindo as açoes dos botoes
        btn1.setOnClickListener(clickResposta);
        btn2.setOnClickListener(clickResposta);
    }
    private void alert(String titulo,String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(this); //criando a variavel alert

        alert.setTitle(titulo);
        alert.setMessage(msg);

        //botão de proximo da pergunta
        alert.setPositiveButton("Próxima", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                proximaPergunta();
            }
        });
        alert.create().show();
    }

    @Override  //criação de tela
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // criação
        linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);

        // criar a txtPergunta
        txtPergunta = new TextView(this);

        //Criar os botões de resposta
        btn1 = new Button(this);
        btn1.setTag(0);

        btn2 = new Button(this);
        btn2.setTag(1);
        //definindo as açoes dos botoes

        btn1.setOnClickListener(clickResposta);
        btn2.setOnClickListener(clickResposta);


        //colocando os botoes dentro do layout
        linear.addView(txtPergunta);
        linear.addView(btn1);
        linear.addView(btn2);

        iniciarJogo();
        //exibindo o conteudo na tela
        setContentView(linear);

    }
}

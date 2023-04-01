/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication_05;

import java.io.IOException;

/**
 *
 * @author alexandrebarao
 */
public class JavaApplication_05 {

   
    public static void main(String[] args) throws IOException {
        
        
       MeuConversorImagem minhaImagem = new MeuConversorImagem("./imagens/gatogordo.jpg");
          

        minhaImagem.OilPaint("./imagens/gatogordoOilPaint.jpg");
        //minhaImagem.resize("./imagens/gatogordo.jpg","./imagens/gatogordoresized.jpg", 100, 100);
        //minhaImagem.AddLogo(null, null, null, 0, 0);



       minhaImagem.AddLogo("./imagens/brocoli.jpg" , "./imagens/gatogordo.jpg", "./imagens/brocoli+logo.jpg", 0.15, 0.3);
       //minhaImagem.converteBW("./imagens/jeepBW.jpg");
//        minhaImagem.negativoBW("./imagens/anaNegativo.jpg");
        //minhaImagem.detetarContornosBW("./imagens/JeepBWContornos.jpg");
//        minhaImagem.histogramaCsvBW("./ficheiros/histogramaAnaBW.csv");
//        minhaImagem.binarizarBW("./imagens/anaBinario50.jpg", 50);
//        minhaImagem.binarizarBW("./imagens/anaBinario120.jpg", 120);
        //minhaImagem.binarizarBW("./imagens/jeepBinario200.jpg", 200);
//        minhaImagem.converteUmaCorRGB("./imagens/anaRed.jpg", 0);
//        minhaImagem.converteUmaCorRGB("./imagens/anaGreen.jpg", 1);
//        minhaImagem.converteUmaCorRGB("./imagens/anaBlue.jpg", 2);
//        minhaImagem.espelhar("./imagens/anaEspelhado.jpg");
//        minhaImagem.criarMargem(200, "./imagens/anaMargem.jpg");

//        minhaImagem.rodarEsquerda("./imagens/anaEsquerda1.jpg");
// //      
// //        
//        ConversorImagemAED minhaImagem2 = new ConversorImagemAED("./imagens/anaEsquerda1.jpg");
//        minhaImagem2.rodarEsquerda("./imagens/anaEsquerda2.jpg");
// //          
//        ConversorImagemAED minhaImagem3 = new ConversorImagemAED("./imagens/anaEsquerda2.jpg");
//        minhaImagem3.rodarEsquerda("./imagens/anaEsquerda3.jpg");
// //          
//        ConversorImagemAED minhaImagem4 = new ConversorImagemAED("./imagens/anaEsquerda3.jpg");
//        minhaImagem4.rodarEsquerda("./imagens/anaEsquerda4.jpg");
          
         
           
         
    }
    
}

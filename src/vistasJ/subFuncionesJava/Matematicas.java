package vistasJ.subFuncionesJava;

import java.util.ArrayList;
import java.util.List;

public class Matematicas {
    public List <Integer> redutcImg(int cantImgPar, int imgSizeH, int imgSizeW) {
        
        int nin = 0;
        int r = cantImgPar % 4;
        if(cantImgPar > 1 && cantImgPar < 4){
            
            nin = 4;

        }else{
            nin = cantImgPar - r;
        };
        
        int nSizH = imgSizeH / (nin / 2);
        int nSizW = imgSizeW / (nin / 2);
        List<Integer> sizes = new ArrayList<Integer>();
        sizes.add(nSizH);
        sizes.add(nSizW);
        return sizes;
    }
}

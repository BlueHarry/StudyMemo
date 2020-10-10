
public class Array{  // 主类
    public static void main(String args[]) { // 主方法
        
        int myArray[][][] = new int [][][] {
            {
                {111, 112, 113, 114},
                {121,122,123,124},
                {231,232,233,234}
            } ,
            {
                {211,212,213,214},
                {221,222,223,224},
                {231,232,233,234}
            } 
         } ;

        for(int i = 0; i < myArray.length; i++) {
            for(int j = 0; j < myArray[i].length; j++){
                for(int k = 0;k < myArray[i][j].length; k++){

                    System.out.println("第" + i + "_"+j +"_"+ k +"层: " + myArray[i][j][k]);
                }
            }
        } 

    }

}
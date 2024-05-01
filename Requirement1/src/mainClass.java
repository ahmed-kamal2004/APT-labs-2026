public class mainClass {
    public static void main(String args[]){
        Matrix new_matrix = new Matrix(5,4);
        int[] data = new int[]{1,2,3,4,5,6,7,8,9,10,11,22,33,44,55,66,77,88,99,00};
        new_matrix.SetNumbers(data);
        new_matrix.Print();
        new_matrix.Transpose();
        new_matrix.Print();


        Matrix new_matrix2 = new Matrix(5,4);
        new_matrix2.SetNumbers(data);
        new_matrix2.Transpose();

        Matrix output = (Matrix) new_matrix2.Add(new_matrix);
        output.Print();

        IdentityMatrix id_matrix = new IdentityMatrix(3);
        id_matrix.Print();

        int[]dd = new int[]{1,0,0,0,1,0,0,0,1};
        System.out.println(id_matrix.SetNumbers(dd));

        id_matrix.Print();
    }
}

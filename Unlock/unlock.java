import java.util.Scanner;

public class unlock
{
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        int n, m;

        do {
            n = in.nextInt();
            m = in.nextInt();
        } while (n < 1 || n > 16 || m < 1 || m > 16);  //Constraint

        in.nextLine();              //Flush the input from extra chars

        try
        {
            char[][] cardMatrix = GenerateMatrix(n);       //Generate the matrix
            char[][] padMatrix = GenerateMatrix(m);

            /*PrintMatrix(cardMatrix);                          //Print the matrix
            System.out.println();
            PrintMatrix(padMatrix);*/

            //UnlockDoor(cardMatrix, padMatrix);
        }
        catch (Exception e)
        {
            System.out.println("Wrong size of inputs");
        }



    }

    ///////Custom Methods

    static char[][] GenerateMatrix(int k) throws Exception                              //Generate the Pad / Card matrix
    {
        String[] rows = new String[k];
        char[][] matrix = new char[k][k];

        for (int i = 0; i < k; i++)
        {
            rows[i] = in.nextLine();                                                    //Get the 0s and 1s

            if (rows[i].length() < k || rows[i].length() > k) throw new Exception();    //Check if input is same as size

            for (int j = 0; j < k; j++)
            {
                matrix[i][j] = rows[i].charAt(j);                                       //Generate matrix
            }
        }

        return matrix;
    }

    static char[][] RotateMatrix(char[][] matrix, int d)
    {
        switch (d)
        {
            case 90:
                return TransposeReverseMatrix(matrix);

            case 180:
                return TransposeReverseMatrix(TransposeReverseMatrix(matrix));

            case 270:
                return TransposeReverseMatrix(TransposeReverseMatrix(TransposeReverseMatrix(matrix)));

            default:
                return matrix;
        }
    }

    static char[][] TransposeReverseMatrix(char[][] matrix)
    {
        int k = matrix.length;
        char[][] finalMatrix = new char[k][k];

        for (int i = 0; i < k; i++)
        {
            for (int j = 0; j < k; j++)
            {
                finalMatrix[j][i] = matrix[i][j];
            }
        }

        finalMatrix = ReverseMatrix(finalMatrix);

        return finalMatrix;
    }

    static char[][] ReverseMatrix(char[][]matrix)
    {
        int k = matrix.length;
        char[][] reversedMatrix = new char[k][k];

        for (int i = 0; i < k; i++)
        {
            for (int j = 0; j < k; j++)
            {
                reversedMatrix[i][j] = matrix[i][k-1-j];
            }
        }

        return reversedMatrix;
    }

    static void UnlockDoor(char[][] cardMatrix, char[][] padMatrix)
    {
        if (cardMatrix.length > padMatrix.length)
        {
            System.out.println("Card too big to fit pad");
            return;
        }

        int a = 0, b = 0, r = 0;
        int cardLength = cardMatrix.length;
        int padLength = padMatrix.length;
        boolean isEqual = false;
        boolean firstEnter = true;

        first: for (int k = 0; k < padLength; k++)
        {
            if (isEqual) break;

            firstEnter = true;
            a = b = r = 0;

            second: for (int l = 0; l < padLength; l++)
            {
                if (isEqual) break;

                firstEnter = true;
                a = b = r = 0;

                for (int i = 0; i < cardLength; i++)
                {
                    for (int j = 0; j < cardLength; j++)
                    {
                        if (i+cardLength >= padLength || j+cardLength >= padLength) break first;

                        if (cardMatrix[i][j] != padMatrix[i+k][j+l])
                        {
                            isEqual = false;
                            firstEnter = true;
                            a = b = r = 0;
                            System.out.println("RESET");
                            break second;
                        }

                        isEqual = true;

                        System.out.format("I: %d, K: %d, J: %d, L: %d, Card = %c, Pad = %c\n", i, k, j, l, cardMatrix[i][j], padMatrix[i+k][j+l]);

                        if (firstEnter)
                        {
                            firstEnter = false;
                            a = i+k;
                            b = j+l;

                            System.out.format("SAVE: I: %d, K: %d, J: %d, L: %d\n", i, k, j, l);
                        }
                    }
                }
            }
        }

        if (!isEqual) System.out.println("err");
        else System.out.println(a + " " + b + " " + r);
    }


    static void PrintMatrix(char[][] matrix)            //Print matrix to screen
    {
        int k = matrix.length;

        for (int i = 0; i < k; i++)
        {
            System.out.println();                       //New matrix line

            for (int j = 0; j < k; j++)
            {
                System.out.print(matrix[i][j] + " ");  //Print matrix char
            }
        }
    }
}
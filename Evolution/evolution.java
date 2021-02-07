import java.util.*;

public class evolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();

        scanner.nextLine(); // Move scanner to the next line

        char G[][] = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();

            for (int j = 0; j < M; j++) {
                G[i][j] = line.charAt(j);
            }
        }

        // WRITE YOUR SOLUTION HERE


        for (int r = 0; r < K; r++)
        {
            char[][] tempG = new char[N][M];

            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < M; j++)
                {
                    int counter = 0;
                    boolean HasLeft = (j - 1 >= 0) ? true : false;
                    boolean HasRight = (j + 1 < G[0].length) ? true : false;
                    boolean HasUp = (i - 1 >= 0) ? true : false;
                    boolean HasDown = (i + 1 < G.length) ? true : false;

                    if (HasUp && HasLeft && G[i - 1][j - 1] != '.') counter++;
                    if (HasUp && G[i - 1][j] != '.') counter++;
                    if (HasUp && HasRight && G[i - 1][j + 1] != '.') counter++;
                    if (HasLeft && G[i][j - 1] != '.') counter++;
                    if (HasRight && G[i][j + 1] != '.') counter++;
                    if (HasDown && HasLeft && G[i + 1][j - 1] != '.') counter++;
                    if (HasDown && G[i + 1][j] != '.') counter++;
                    if (HasDown && HasRight && G[i + 1][j + 1] != '.') counter++;


                    switch (G[i][j]) {
                        case '.':
                            if (counter > 4) tempG[i][j] = '+';
                            else tempG[i][j] = G[i][j];
                            break;

                        case '+':
                            if (counter > 4) tempG[i][j] = '*';
                            else if (counter < 4) tempG[i][j] = '.';
                            else tempG[i][j] = G[i][j];
                            break;

                        case '*':
                            if (counter > 4) tempG[i][j] = '+';
                            else if (counter < 4) tempG[i][j] = '.';
                            else tempG[i][j] = G[i][j];
                            break;

                        default:
                            break;
                    }
                }
            }

            G = tempG;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(G[i][j]);
            }
            System.out.println();
        }
    }
}
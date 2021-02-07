import java.beans.PropertyEditorSupport;
import java.util.*;

public class unbalancer
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();  //NUMERO PROCESSI
        int K = scanner.nextInt();  //NUMERO MOSSE DISPONIBILI

        scanner.nextLine(); // Move scanner to the next line

        int V[] = new int[N];       //PROCESSI CON TASK

        for(int i=0; i<N; i++) {
            V[i] = scanner.nextInt();
        }

        int solution = 0;
        int index = -1;
        int max = 0;
        // WRITE YOUR SOLUTION HERE

        while (K >= 0)
        {
            for (int i = 0; i < V.length; i++)
            {
                if (V[i] > max)
                {
                    max = V[i];
                    index = i;
                }
            }

            V[index] = 0;
            solution += max;
            max = 0;
            K--;
        }

        System.out.println(solution);
    }
}
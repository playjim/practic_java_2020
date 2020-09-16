
package leastsquaremethod;

import java.util.Scanner;

public class LeastSquareMethod {
    //public static int

    public static void main(String[] args) {
        int var;
        Scanner scan = new Scanner(System.in);
        System.out.print(ANSI_GREEN + "Тема практической работы: Метод наименьших квадратов.\nС помощью этого метода необходимо найти уравнение прямой, проходящей через точки с известными координатами." + ANSI_RESET);
        int[][] matrixX = new int[2][2];
        int[] matrixY = new int[2];

        System.out.println("\nВведем координаты точек: ");
        for (int i = 0; i < 2; i++) {
            System.out.print("x" + (i + 1) + " = ");
            matrixX[i][0] = scan.nextInt();
            System.out.print("y" + (i + 1) + " = ");
            matrixY[i] = scan.nextInt();
        }
        matrixX[0][1] = 1;
        matrixX[1][1] = 1;

        System.out.println(ANSI_GREEN + "Отлично. Мы сформировали матрицы координат:" + ANSI_RESET);
        System.out.print("X = \n");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(" " + matrixX[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("Y = \n");
        for (int i = 0; i < 2; i++) {
            System.out.print(" " + matrixY[i] + " ");

            System.out.println();
        }
        System.out.println(ANSI_GREEN + "Теперь, с помощью нехитрых вычислений, найдем матрицу коэффициентов и получим уравнениe прямой!\nУравнение для матрицы коэффициентов:\nK = ((X^t * X)^(-1) * X^t * Y\nТранспонируем матрицу X:" + ANSI_RESET);
        int[][] matrixXt = new int[2][2];
        matrixXt[0][0] = matrixX[0][0];
        matrixXt[1][0] = matrixX[0][1];
        matrixXt[0][1] = matrixX[1][0];
        matrixXt[1][1] = matrixX[1][1];
        System.out.print("X^t = \n");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(" " + matrixXt[i][j] + " ");
            }
            System.out.println();
        }


        System.out.print(ANSI_GREEN + "Подставляем полученные данные в формулу нахождения матрицы коэффициентов и находим её:\n" + ANSI_RESET);
        int[][] matrixXtX = new int[2][2];
        matrixXtX[0][0] = matrixXt[0][0] * matrixX[0][0] + matrixXt[0][1] * matrixX[1][0];
        matrixXtX[1][0] = matrixXt[1][0] * matrixX[0][0] + matrixXt[1][1] * matrixX[1][0];
        matrixXtX[0][1] = matrixXt[0][0] * matrixX[0][1] + matrixXt[0][1] * matrixX[1][1];
        matrixXtX[1][1] = matrixXt[1][0] * matrixX[0][1] + matrixXt[1][1] * matrixX[1][1];
        System.out.print(ANSI_GREEN + "1.Находим произведение транспонированной матрицы Х и матрицы Х:\nX^t * Х = \n" + ANSI_RESET);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(" " + matrixXtX[i][j] + " ");
            }
            System.out.println();
        }
        double Op = matrixXtX[0][0] * matrixXtX[1][1] - matrixXtX[0][1] * matrixXtX[1][0];
        if (Op == 0) {

            System.out.println(ANSI_RED + "Определитель равен нулю, решения нет!" + ANSI_RESET);
            System.exit(0);

        }

        int[][] matrixXm = new int[2][2];
        matrixXm[0][0] = matrixXtX[1][1];
        matrixXm[0][1] = matrixXtX[1][0] * (-1);
        matrixXm[1][0] = matrixXtX[0][1] * (-1);
        matrixXm[1][1] = matrixXtX[0][0];

        int[][] matrixXx = new int[2][2];
        matrixXx[0][0] = matrixXm[0][0];
        matrixXx[0][1] = matrixXm[1][0];
        matrixXx[1][0] = matrixXm[0][1];
        matrixXx[1][1] = matrixXm[1][1];

        double F = 1 / Op;
        double[][] matrixXobr = new double[2][2];
        matrixXobr[0][0] = F * matrixXx[0][0];
        matrixXobr[0][1] = F * matrixXx[0][1];
        matrixXobr[1][0] = F * matrixXx[1][0];
        matrixXobr[1][1] = F * matrixXx[1][1];

        System.out.print(ANSI_GREEN + "2.Находим обратную матрицу этого произведения:\n(X^t * Х)^(-1) = \n" + ANSI_RESET);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(" " + matrixXobr[i][j] + " ");
            }
            System.out.println();
        }

        double[][] matrixXobrXt = new double[2][2];
        matrixXobrXt[0][0] = matrixXobr[0][0] * matrixXt[0][0] + matrixXobr[0][1] * matrixXt[1][0];
        matrixXobrXt[1][0] = matrixXobr[1][0] * matrixXt[0][0] + matrixXobr[1][1] * matrixXt[1][0];
        matrixXobrXt[0][1] = matrixXobr[0][0] * matrixXt[0][1] + matrixXobr[0][1] * matrixXt[1][1];
        matrixXobrXt[1][1] = matrixXobr[1][0] * matrixXt[0][1] + matrixXobr[1][1] * matrixXt[1][1];

        System.out.print(ANSI_GREEN + "3.Находим произведение обратной матрицы и транспонированной матрицы Х:\n((X^t * Х)^(-1)) * Х^t = \n" + ANSI_RESET);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(" " + matrixXobrXt[i][j] + " ");
            }
            System.out.println();
        }
        double[] matrixK = new double[2];
        matrixK[0] = matrixXobrXt[0][0] * matrixY[0] + matrixXobrXt[0][1] * matrixY[1];
        matrixK[1] = matrixXobrXt[1][0] * matrixY[0] + matrixXobrXt[1][1] * matrixY[1];


        System.out.print(ANSI_GREEN + "4.Находим произведение получившейся матрицы и матрицы Y, то есть матрицу коэффициентов:\nK = \n" + ANSI_RESET);
        for (int i = 0; i < 2; i++) {
            System.out.print(" " + matrixK[i] + " ");

            System.out.println();
        }
        double scale = Math.pow(10, 2);
        double a = Math.ceil(matrixK[0] * scale) / scale;
        double b = Math.ceil(matrixK[1] * scale) / scale;

        System.out.print(ANSI_GREEN + "5. Округляем значения.\nТаким образом, ответом на нашу задачу будет уравнение прямой: y = " + a + "x + " + b + "\n" + ANSI_RESET);


    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
}

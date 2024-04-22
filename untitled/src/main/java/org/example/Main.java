package org.example;

public class Main {

    // Метод, реализующий расширенный алгоритм Евклида
    public static int[] extendedEuclidean(int a, int b) {
        if (b == 0) {
            // Базовый случай: если b = 0, то НОД равен a, а коэффициенты Безу равны (1, 0)
            return new int[] {a, 1, 0};
        } else {
            // Рекурсивный случай: вызываем функцию для b и a % b
            int[] result = extendedEuclidean(b, a % b);
            // Корректируем коэффициенты Безу и возвращаем результат
            int gcd = result[0];
            int x = result[2];
            int y = result[1] - (a / b) * result[2];
            return new int[] {gcd, x, y};
        }
    }

    public static void main(String[] args) {
        int a = 30;
        int b = 12;

        // Вызываем метод расширенного алгоритма Евклида для чисел a и b
        int[] result = extendedEuclidean(a, b);

        // Выводим результаты
        System.out.println("НОД(" + a + ", " + b + ") = " + result[0]);
        System.out.println("Коэффициенты Безу: x = " + result[1] + ", y = " + result[2]);
    }
}



/*
В одной из предыдущих задач требовалось вывести перевернутую матрицу,
теперь задача усложняется:

        При этом поворот необходимо осуществлять in-place, т. е. без выделения
        дополнительной памяти. Для этого вместо результирующей матрицы необходимо
        вывести последовательность операций. За одну операцию можно обменять местами
        два элемента матрицы.

        Вам дана матрица nхn, а так же указано, надо ли повернуть изображение по
        часовой R или против часовой L стрелки. Выведите последовательность операций,
        чтобы исходная матрица повернулась на 90 градусов в указанном направлении.

        Заметьте, что необязательно переставлять элементы в том порядке, в
        котором происходил бы поворот, главное чтобы в результате матрица соответствовала
        повороту на 90 градусов. Также необязательно, чтобы количество операций было
        минимальным, нужно только вписаться в ограничения.

        Формат входных данных

        Первая строка содержит одно натуральное число n (1 ≤ n ≤103) и указание
        направления поворота символ R или L. Следующие и строк содержат описание
        матрицы, по п целых неотрицательных чисел, не превосходящих 1018.

        Формат выходных данных

        В первой строке выведите число к необходимое количество операций, при этом это
        число не должно превосходить 7n^2. В последующих строках выведите по две пары чисел
        координаты (Х1, У1) и (Х2, У2) ячеек, между которыми необходимо обменять элементы
        матрицы.

        Замечание
        Обратите внимание, что нумерация строк и столбцов матрицы ведётся с 0, а не с 1
*//*


package org.example;

import java.util.*;

public class Main {
    static int n;
    static int[][] matrix;
    static boolean isClockwise;
    static int count =0;
    static List<String> permutations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        String s = scanner.nextLine();
        if (s.equals(" R")){
            isClockwise=true;
        } else if (s.equals(" L")){
            isClockwise=false;
        }
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (matrix[i][j]!=matrix[j][i]) {
                    count++;
                    permutations.add(i+" " + j+ " " + j+" " +i);
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        if (!isClockwise) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n/2; j++) {
                    if (matrix[j][i] != matrix[n - 1 - j][i]){
                        count++;
                        permutations.add(j +" "+ i + " " +(n-1-j)+" " + i);
                        int temp = matrix[j][i];
                        matrix[j][i] = matrix[n - 1 - j][i];
                        matrix[n - 1 - j][i] = temp;
                    }
                }
            }
        }else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n/2; j++) {
                    if (matrix[i][j] != matrix[i][n - 1 - j]) {
                        count++;
                        permutations.add(i+" " + j+" " +i +" "+(n-1-j));
                        int temp = matrix[i][j];
                        matrix[i][j] = matrix[i][n - 1 - j];
                        matrix[i][n - 1 - j] = temp;
                    }
                }
            }
        }
        System.out.println(count);
        for (String permutation :
                permutations) {
            System.out.println(permutation);
        }
    }
}

        */
/*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j]=matrix[i][n-j-1];
                matrix[i][n-j-1]=temp;
            }
        }
        writeMatrix();*//*


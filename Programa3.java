import java.util.Scanner;

class NumeroComplejo
{
    private double re;
    private double im;

    public void Set(double r, double i)
    {
        re = r;
        im = i;
    }

    public void Set(NumeroComplejo entrada)
    {
        re = entrada.re;
        im = entrada.im;
    }

    public void Suma(NumeroComplejo entrada)
    {
        re = re + entrada.re;
        im = im + entrada.im;
    }

    public void Resta(NumeroComplejo entrada)
    {
        re = re - entrada.re;
        im = im - entrada.im;
    }

    public void Multiplica(NumeroComplejo entrada)
    {
        double aux;
        aux = (re * entrada.re) - (im * entrada.im);
        im = (re * entrada.im) + (im * entrada.re);
        re = aux;
    }

    public void Divide(NumeroComplejo entrada)
    {
        double Den;
        NumeroComplejo conjugado = new NumeroComplejo();
        conjugado.re = entrada.re;
        conjugado.im = (-1.0) * entrada.im;
        Den = (conjugado.re * conjugado.re) + (conjugado.im * conjugado.im);
        this.Multiplica(conjugado);
        re = re/Den;
        im = im/Den;
        conjugado = null;
    }

    public void Show()
    {
        if (im < 0) 
        {
            System.out.print(re + "" + im + "j");    
        } else 
        {
            System.out.print(re + "+" + im + "j");    
        }
    }

    public double GetRe()
    {
        return re;
    }

    public double GetIm() {
        return im;
    }

}

/**
 * MatrizComplejo
 */
class MatrizCompleja {
    private NumeroComplejo Mat[][];
    private int M;
    private int N;
    
    MatrizCompleja()
    {
        Mat = null;
        M = 0;
        N = 0;
    }

    MatrizCompleja(int Mfilas, int Ncolumnas)
    {
        int i, j;
        Mat = new NumeroComplejo[Mfilas][Ncolumnas];
        for (i = 0; i < Mfilas; i++) {
            for(j = 0; j < Ncolumnas; j++){
                Mat[i][j] = new NumeroComplejo();
            }
        }
        M = Mfilas;
        N = Ncolumnas;
    }

    void ShowMatrizCompleja()
    {
        int i, j;
        for (i = 0; i < M; i++) {
            System.out.print("\n");
            for (j = 0; j < N; j++) {
                Mat[i][j].Show();
                System.out.print(" ");
            }
        }
    }

    void InicializarMatriz()
    {
        int i, j;
        double re, im;
        Scanner Scanf = new Scanner(System.in);
        System.out.println("\nIngese la Matriz Compleja");
        for (i = 0; i < M; i++) {
            for(j = 0; j < N; j++){
                System.out.println("Mat[" + i +"][" + j + "].re = ");
                re = Scanf.nextDouble();
                System.out.println("Mat[" + i +"][" + j + "].im = ");
                im = Scanf.nextDouble();
                Mat[i][j].Set(re, im);;
            }
        }
        Scanf = null;
    }

    void SumaMatrizCompleja(MatrizCompleja entrada)
    {
        int i, j;
        if ((M != entrada.M) || (N != entrada.N))
        {
            System.out.println("Operación Suma no esta definida para matrices de diferentes dimensiones\n");
        }
        else
        {
            for (i = 0; i < M; i++) {
                for(j = 0; j < N; j++){
                    Mat[i][j].Suma(entrada.Mat[i][j]);
                }
            }
        }
    }

    void RestaMatrizCompleja(MatrizCompleja entrada)
    {
        int i, j;
        if ((M != entrada.M) || (N != entrada.N))
        {
            System.out.println("Operación Resta no esta definida para matrices de diferentes dimensiones\n");
        }
        else
        {
            for (i = 0; i < M; i++) {
                for(j = 0; j < N; j++){
                    Mat[i][j].Resta(entrada.Mat[i][j]);
                }
            }
        }
    }

    void TrasnspuestaMatriz()
    {
        int i, j;
        NumeroComplejo Aux[][];
        int auxInt;
        Aux = new NumeroComplejo[N][M];
        for (i = 0; i < N; i++) {
            for(j = 0; j < M; j++){
                Aux[i][j] = new NumeroComplejo();
            }
        }
        for (i = 0; i < M; i++) {
            for(j = 0; j < N; j++){
                Aux[j][i] = Mat[i][j];
            }
        }
        for (i = 0; i < M; i++) {
            for(j = 0; j < N; j++){
                Mat[i][j] = null;
            }
        }
        Mat = null;
        Mat = new NumeroComplejo[N][M];
        for (i = 0; i < N; i++) {
            for(j = 0; j < M; j++){
                Mat[i][j] = new NumeroComplejo();
            }
        }

        for (i = 0; i < M; i++) {
            for(j = 0; j < N; j++){
                Mat[j][i] = Aux[j][i];
            }
        }
        auxInt = M;
        M = N;
        N = auxInt;
        Aux = null;
    }

    void MultiplicaMatrizCompleja(MatrizCompleja entrada)
    {
        if (N != entrada.M) {
            System.out.println("Operación Multiplicación debe tener las mismas columnas en la primera matriz que las filas de la segunda matriz\n");
        }
        else
        {
            NumeroComplejo NumCompl, NumComplF;
            NumCompl = new NumeroComplejo();
            NumComplF = new NumeroComplejo();
            int i, j, k;
            NumeroComplejo Aux[][];
            Aux = new NumeroComplejo[M][entrada.N];
            for (i = 0; i < M; i++) {
                for(j = 0; j < entrada.N; j++){
                    Aux[i][j] = new NumeroComplejo();
                }
            }
            for (i = 0; i < M; i++) {
                for (j = 0; j < entrada.N; j++) {
                    for (k = 0; k < N; k++) {
                        NumCompl.Set(Mat[i][k].GetRe(), Mat[i][k].GetIm());
                        NumCompl.Multiplica(entrada.Mat[k][j]);
                        NumComplF.Suma(NumCompl);
                    }
                    Aux[i][j] = NumComplF;
                    NumComplF = null;
                    NumComplF = new NumeroComplejo();
                }
            }

            for (i = 0; i < M; i++) {
                for(j = 0; j < N; j++){
                    Mat[i][j] = null;
                }
            }
            Mat = null;
            Mat = new NumeroComplejo[M][entrada.N];
            for (i = 0; i < M; i++) {
                for(j = 0; j < entrada.N; j++){
                    Mat[i][j] = new NumeroComplejo();
                }
            }

            for (i = 0; i < M; i++) {
                for (j = 0; j < entrada.N; j++) {
                    Mat[i][j] = Aux[i][j];
                }
            }
            N = entrada.N;
            Aux = null;
        }
    }

    NumeroComplejo DeterminanteMatrizCompleja()
    {
        NumeroComplejo NumCompl, NumComplF;
        NumCompl = new NumeroComplejo();
        NumComplF = new NumeroComplejo();
        if ((N != 3)||(M != 3)) {
            System.out.println("La matríz debe ser de 3x3");
        }
        else{
            int i, j, k;
            NumeroComplejo Aux[][];
            Aux = new NumeroComplejo[M + 2][N];
            for (i = 0; i < M + 2; i++) {
                for(j = 0; j < N; j++){
                    Aux[i][j] = new NumeroComplejo();
                }
            }

            for (i = 0; i < M; i++) {
                for (j = 0; j < N; j++) {
                    Aux[i][j] = Mat[i][j];
                    if (i < 2) {
                        Aux[i + 3][j] = Mat[i][j];
                        System.out.println("[" + (i + 3) + "][" + j + "] -> Elemento[" + i + "][" + j + "]");
                    }
                }
            }

            for (k = 0; k < M; k++) {
                NumCompl.Set(1, 1);
                for (i = k, j = 0; j < N; j++, i++) {
                    NumCompl.Multiplica(Aux[i][j]);
                    System.out.println("Elemento[" + i + "][" + j + "]");
                    Aux[i][j].Show();
                    System.out.println("\n");
                }
                System.out.println("\n");
                NumComplF.Suma(NumCompl);
                NumCompl = null;
                NumCompl = new NumeroComplejo();
            }

            for (k = 0; k < M; k++) {
                NumCompl.Set(1, 1);
                for (i = k, j = N - 1; j > -1; j--, i++) {
                    NumCompl.Multiplica(Aux[i][j]);
                    System.out.println("--> Elemento[" + i + "][" + j + "]:");
                    Aux[i][j].Show();
                    System.out.println("\n");
                }
                System.out.println("\n");
                NumComplF.Resta(NumCompl);
                NumCompl = null;
                NumCompl = new NumeroComplejo();
            }
        }
        return NumComplF;
    }

}

public class Programa3
{
    public static void main(String[] args) {
        MatrizCompleja M1;
        M1 = new MatrizCompleja(3, 3);
        // MatrizCompleja M2;
        // M2 = new MatrizCompleja(3, 3);
        NumeroComplejo Res;
        Res = new NumeroComplejo();
        M1.ShowMatrizCompleja();
        System.out.println(" ");
        M1.InicializarMatriz();
        System.out.println(" ");
        // M2.InicializarMatriz();
        // System.out.println(" ");
        M1.ShowMatrizCompleja();
        System.out.println(" ");
        Res = M1.DeterminanteMatrizCompleja();
        System.out.println(" ");
        Res.Show();
        System.out.println(" ");
        // M2.ShowMatrizCompleja();
        // System.out.println(" ");
        // M1.TrasnspuestaMatriz();
        // System.out.println(" ");
        // M1.MultiplicaMatrizCompleja(M2);
        // System.out.println(" ");
        // M1.ShowMatrizCompleja();
        // System.out.println(" ");
        // M2.InicializarMatriz();
        // System.out.println(" ");
        // M1.ShowMatrizCompleja();
        // System.out.println(" ");
        // M2.ShowMatrizCompleja();
        // System.out.println(" ");
        // M1.SumaMatrizCompleja(M2);
        // System.out.println(" ");
        // System.out.println("La suma de matrices complejas es:");
        // M1.ShowMatrizCompleja();
        // System.out.println(" ");
        // M1.RestaMatrizCompleja(M2);
        // System.out.println(" ");
        // System.out.println("La resta de matrices complejas es:");
        // M1.ShowMatrizCompleja();
    }
}
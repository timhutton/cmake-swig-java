public class main 
{
    public static void main(String argv[]) 
    {
        System.loadLibrary("example"); // Attempts to load example.dll (on Windows) or libexample.so (on Linux)
        System.out.println(example.foo());
    }
}

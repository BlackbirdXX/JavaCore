import java.io.File;

public class Tree {


    public static void main(String[] args) {
        print(new File("./HW_05"), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
            {
                subDirTotal++;
            }
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
            {
                print(files[i], indent, subDirTotal  == ++subDirCounter);
            }
            else
            {
                System.out.println(indent + "  " + files[i].getName());
                // Если не директория, то печатаем что есть.
            }
        }
    }

}
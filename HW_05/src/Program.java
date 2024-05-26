
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {


    /**
     1.  Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
     2.  Написать метод, «склеивающий» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
     3.* Написать метод, который проверяет, присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
     4.* Написать метод, проверяющий, есть ли указанное слово в папке
     */

    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;
    private static final String TO_SEARCH = "GeekBrains";
    private static final String destination = "./HW_05/backup";
    private static final String sourse = "./HW_05/files";



    public static void main(String[] args) throws IOException {


        String[] fileNames = new String[10];
        for (int j = 0; j < fileNames.length; j++){
            fileNames[j] = "./HW_05/files/file_" + j + ".txt";
            writeFileContents(fileNames[j], 30, 3);
            System.out.printf("Файл %s был создан.\n", fileNames[j]);
        }

        List<String> result = searchMatch(new File("."), TO_SEARCH);
        for (String s : result){
            System.out.printf("Файл %s содержит искомое слово '%s'\n", s, TO_SEARCH);
        }

        makeBackUp();

    }

    /**
     * Функция, создающая резервную копию всех файлов в директории во вновь созданную папку ./backup
     * @throws IOException
     */
    public static void makeBackUp() throws IOException {
        File sourseDir = new File(sourse);
        File destinationDir = new File(destination);
        if (!destinationDir.exists())
        {
            destinationDir.mkdir();
        }
        for (File file : sourseDir.listFiles())
        {
            if (file.isFile())
            {
                File newFile = new File(destination, file.getName());
                try (InputStream in = new FileInputStream(file); OutputStream out = new FileOutputStream(newFile)){
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = in.read(bytes)) > 0) {
                        out.write(bytes, 0, length);
                    }
                }
            }
        }
    }

    /**
     * Метод генерации некоторой последовательности символов
     * @param amount кол-во символов
     * @return последовательность символов
     */
    private static String generateSymbols(int amount){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < amount; i++){
            stringBuilder.append((char)random.nextInt(CHAR_BOUND_L, CHAR_BOUND_H + 1));
        }
        return stringBuilder.toString();
    }

    /**
     * Записать последовательность символов в файл
     * @param fileName имя файла
     * @param length длина последовательности символов
     * @throws IOException
     */
    private static void writeFileContents(String fileName, int length) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            fileOutputStream.write(generateSymbols(length).getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * Записать последовательность символов в файл, при этом, случайным образом дописать осознанное слово
     * для поиска
     * @param fileName имя файла
     * @param length длина последовательности символов
     * @param words кол-во слов для поиска
     * @throws IOException
     */
    private static void writeFileContents(String fileName, int length, int words) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            for(int i = 0; i < words; i++){
                if (random.nextInt(4) == 0){
                    fileOutputStream.write(TO_SEARCH.getBytes(StandardCharsets.UTF_8));
                }
                else {
                    fileOutputStream.write(generateSymbols(length).getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }

    private static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException {
        // На запись
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileOut)){
            int c;
            // На чтение
            try(FileInputStream fileInputStream = new FileInputStream(fileIn1)) {
                while ( (c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
            // На чтение
            try(FileInputStream fileInputStream = new FileInputStream(fileIn2)) {
                while ( (c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
        }
    }

    private static long searchInFile(String fileName, String search) throws IOException{
        return searchInFile(fileName, 0, search);
    }

    private static long searchInFile(String fileName, long offset, String search) throws IOException{
        try(FileInputStream fileInputStream = new FileInputStream(fileName)){
            fileInputStream.skipNBytes(offset);
            byte[] searchData = search.getBytes(StandardCharsets.UTF_8);
            int c;
            int i = 0;
            while ((c = fileInputStream.read())  != -1){
                if (c == searchData[i]){
                    i++;
                }
                else{
                    i = 0;
                    if (c == searchData[i])
                        i++;
                }
                if (i == searchData.length)
                {
                    return offset;
                }
                offset++;
            }
            return -1;
        }
    }

    static List<String> searchMatch(File file, String search) throws IOException {
        List<String> list = new ArrayList<>();
        File[] files = file.listFiles();
        if (files == null)
            return list;
        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()){
                if (searchInFile(files[i].getCanonicalPath(), search) > -1){
                    list.add(files[i].getCanonicalPath());
                }
            }
        }
        return list;
    }

}
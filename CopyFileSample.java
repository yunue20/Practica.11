import java.io.*;
public class CopyFileSample{
	public static void copy(File source, File destination) throws IOException{
		try (InputStream in = new FileInputStream(source);
		OutputStream out = new FileOutputStream(destination)){
		int b;
		while((b = in.read())!=-1){
			out.write(b);
		}

		}
	}
   public static void main(String[] args)throws IOException{
     File archivo1 = new File("Pingu.docx");
     File archivo2 = new File("Pingucopia.txt");
   
     copy(archivo1,archivo2);
   }

}
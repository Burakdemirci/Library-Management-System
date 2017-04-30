/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
import java.util.*;

public class mainTester {

	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		try
		{
			Library gtuLibrary = new Library();
			int i = 0;
			int devam;
			System.out.printf("\n\t  Gebze Teknik Üniversitesi Kütüphane Sistemi\n\n");
			do
			{
				System.out.println("Lütfen Giriş Yöntemini Giriniz");
				System.out.println("Görevli   : 1");	
				System.out.printf("Kullanıcı : 2\n> ");
				int type = reader.nextInt();
				System.out.printf("ID :");
				Integer id = reader.nextInt();
				System.out.printf("Şifre :");
				String password = reader.next();

				gtuLibrary.login(id,password,type,i);
				i++;

				System.out.printf("Sistemi Kapatmak İçin : 1\n> ");
				devam = reader.nextInt();				
			}while(devam!=1);	
		}
		catch(Exception e)
		{
			System.out.println("Exception caught: " + e);
		}
	}
}
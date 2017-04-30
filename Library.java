/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
import java.util.*;


public class Library 
{

	private String userFile  = "User.csv"; 
	private String staffFile = "Staff.csv";
	private String bookFile  = "Book.csv";
	/**
	 * Kutuphane icin kullanılacak objeler
	 */
	private Book  gtuBook = new Book(bookFile);
	private Staff gtuStaff= new Staff(staffFile);
	private User  gtuUser = new User(userFile);

	/**
	 *Kutuphane giris sistemi
	 * @param id Kullanıcı ID'si
	 * @param password Kullnıcı Şİfresi
	 * @param type Gorevli veye Kutuphane kullanıcısı icin belirlenen key
	 * @param readOrNot Yanlıs girme durumunda dosyayı tekrar okumayı engelleme amaclı key
	 * @return Giris basarılı veya degil
	 */
 	public boolean login(Integer id, String password, int type , int readOrNot )
	{
		if(type==2)
		{
			/**
			 *  User
			 */
			try
			{
				if(readOrNot == 0)
				{
					gtuUser.readFile();
					gtuBook.readFile();
				}
				if(gtuUser.login(id, password))
				{	

					user(id);
					gtuUser.writeFile();
					gtuBook.writeFile();
					return true;
				}	
				else
				{ 
					System.out.printf("Yanlış ID veya Şifre\n");
					return false;
				}	
			}
			catch(Exception e)
			{
				System.out.println("Exception caught to Library: " + e);
			}	
		} 

		if(type==1)
		{
			/**
			 *  Staff
			 */
			try
			{
				if(readOrNot == 0 )
				{
					gtuUser.readFile();
					gtuStaff.readFile();
					gtuBook.readFile();
				}
				if(gtuStaff.login(id, password))
				{
					staff(id);
					gtuUser.writeFile();
					gtuBook.writeFile();
					gtuStaff.writeFile();
					return true;
				}
				else 
				{	
					System.out.printf("Yanlış ID veya Şifre\n");
					return false;
				}	
			}
			catch(Exception e)
			{
				System.out.println("Exception caught to Library: " + e);
			}	
		}

		return false;
	} 

	/**
	 * Kullanıcı islemleri
	 * @param id Kullanıcı ID
	 */
	public void user(Integer id )
	{
		Scanner reader = new Scanner(System.in);
		int type , bookId;
		System.out.printf("Kullanıcı : "+gtuUser.getName(gtuUser.getIDtoIndex(id))+" ");
		System.out.printf(gtuUser.getSurname(gtuUser.getIDtoIndex(id))+"\nID: ");
		System.out.printf(id+"\n\n");
		do
		{
			System.out.printf("\nLütfen yapmak istediginiz işlemin kodunu giriniz. \n");
			System.out.printf("1) Kitap Ödünç Alma\n2) Kitap iade\n3) Şifre degistirme\n4) Çıkış\n> ");
			type = reader.nextInt();
			
			if(type==1)
			{
				/**
				 * Kitap odunc alma
				 */
				System.out.printf("\t\tKitap Listesi \n");
				gtuBook.seeAllBook();
				System.out.printf("\nÖdünç almak istediginiz kitap id'sini giriniz\n>");
				bookId = reader.nextInt();
				if(gtuBook.barrowBook(bookId))
					gtuUser.barrowBook(id,bookId);
			}
			if(type==2)
			{
				/**
				 * Kitap iade etme
				 */
				returnBook(id);
			}
			if(type==3)
			{

				/**
				 * Şifre degistirme
				 */
				System.out.printf("\nYeni Şifreyi Giriniz >");
				String pass = reader.next();
				gtuUser.setPassword(id, pass);
			}

		}while (type!=4);
		
	}

	/**
	 * Gorevli islemleri
	 * @param id Gorevli ID
	 */
	public void staff(Integer id)
	{
		Scanner reader = new Scanner(System.in);
		int type , bookId;
		String bookN,bookW;
		System.out.printf("Görevli : "+gtuStaff.getName(gtuStaff.getIDtoIndex(id))+" ");
        System.out.printf(gtuStaff.getSurname(gtuStaff.getIDtoIndex(id))+"\nID: ");
        System.out.printf(id+"\n\n");
        do
        {	
        	System.out.printf("\nLütfen yapmak istediginiz işlemin kodunu giriniz. \n");
			System.out.printf("1) Kitap Ekleme\n2) Kitap Kaldırma\n3) Kullanıcı Ekleme\n4) Şifre degiştirme\n5) Çıkış\n> ");
			type = reader.nextInt();
			if(type==1)
			{
				/**
				 * Kitap ekleme islemi
				 */
				System.out.printf("ID Gir > ");
				bookId = reader.nextInt();
				System.out.printf("Kitap İsmi Gir > ");
				reader.nextLine();
				bookN = reader.nextLine();
				System.out.printf("Kitap Yazarı Gir > ");
				bookW = reader.nextLine();
				if(gtuBook.addBook(bookW,bookN,bookId))
					System.out.printf("\nKitap başarı ile kütüphaneye eklendi\n");
			}
			if(type==2)
			{
				/**
				 *  Kitap kaldırma islemi
				 */
				deleteBook();
			}
			
			if(type==3) 
			{
				/**
				 * Yeni kullanıcı ekleme
				 */
				System.out.printf("ID Gir > ");
				bookId = reader.nextInt();
				System.out.printf("İsim Gir > ");
				reader.nextLine();
				bookN = reader.nextLine();
				System.out.printf("Soyisim Gir > ");
				bookW = reader.nextLine();
				if(gtuUser.addUser(bookId,bookN,bookW))
					System.out.printf("\nKisi ekleme başarılı \n");
			}
			if(type==4)	
			{
				/**
				 *  Şifre degistirme
				 */
				System.out.printf("\nYeni Şifreyi Giriniz > ");
				String pass = reader.next();
				gtuStaff.setPassword(id, pass);
			}	
        }while(type!=5);
	}

	/**
	 *  Kitap silme icin helper metod
	 */
	public void deleteBook()
	{
		int id;
		Scanner reader = new Scanner(System.in);
		gtuBook.seeAllBook();
		System.out.printf("Silinecek Kitabın ID'sini Giriniz\n> ");
		id = reader.nextInt();
		if(!gtuBook.checkBookID(id))
		{
			if(gtuBook.getBookCondition(id)==0)
			{
				/**
				 *  Silinecek kitap birine odunc verilmis ise Onun bilgileri kaldırılır
				 */
				gtuUser.removeBook(id);
			}
			gtuBook.removeBook(id);
			System.out.printf("Kitap başarı ile kaldırıldı\n");
		}	
		else
		{
			System.out.printf("Kütüphanede bu ID'ye ait kitap bulunmadı\n");
		}	


	}

	/**
	 *  Kitap iade isislme
	 * @param id Kullanıcı ID
	 */

	public void returnBook(int id)
	{
		int i = 0;
		int val;
		Scanner reader = new Scanner(System.in);
		if(gtuUser.getUserBookNumber(gtuUser.getIDtoIndex(id)) > 0)
		{
			System.out.printf("\nID    Üzerinize kayıtlı kitap Listesi\n");
			int bookID1;
			while(i < gtuUser.getUserBookNumber(gtuUser.getIDtoIndex(id)))
			{
				bookID1 = gtuUser.getUserBookID(id,i);
				System.out.printf(bookID1+" ");
				System.out.printf(gtuBook.getBookName(bookID1)+" ");
				System.out.printf(gtuBook.getBookWriter(bookID1)+"\n");
				i++;
			}
			System.out.printf("\nİade edmek istediginiz kitabın ID'sini giriniz\n>");
			val=reader.nextInt();
			if(gtuBook.returnBook(val))
				if(gtuUser.returnBook(id,val))
					System.out.printf("Kitap teslim edildi \n");
		}
		else
		{
			System.out.printf("Üzerinize kayıtlı kitap bulunmamaktadır\n");
		}	
	}

}
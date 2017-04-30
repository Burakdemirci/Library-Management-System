/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
import java.io.*;
import java.util.*;

public class User implements Person
{ 

	/**
	 * Degiskenlerin tutuldugu arraylistler
	 */
	private  ArrayList<String> userName = new ArrayList<>();
	private  ArrayList<String> userPasssword = new ArrayList<>();
	private  ArrayList<String> userSurname = new ArrayList<>();
	private  ArrayList<Integer> userID = new ArrayList<>();
	private  ArrayList<Integer> userBookNumber = new ArrayList<>();//Kullanicinin sahip oldugu kitap sayisi
	private  ArrayList<ArrayList<Integer>> userBookID = new ArrayList<ArrayList <Integer> >();

	private String fileName ;

	public User(String fileName1)
	{
		/**
		 * Dosya ismini alan constructor
		 */
		fileName=fileName1;
	}

	/**
	 * @throws IOException  If an input or output
	 *                      exception occurred
	 */
	public void readFile() throws Exception
	{
		/**
		 * Kullanicilara ait bilgileri dosyadan okuma
		 */
		File input = new File(fileName);
		Scanner inpFile = new Scanner (input);
		inpFile.useDelimiter(",");
		int booknum;
		while (inpFile.hasNext())
		{
			userID.add( Integer.valueOf(inpFile.next()));
			userName.add(inpFile.next());
			userSurname.add(inpFile.next());
			userPasssword.add(inpFile.next());
			booknum = Integer.valueOf(inpFile.next());
			userBookNumber.add(booknum);

			/**
			 * Bir kullaniciya ait kac tane kitap varsa
			 * Hepsinin ID si bir liste seklinde o kulaniciya atanir
			 */
			ArrayList<Integer> tempList = new ArrayList<>();
			for (int i=0; i < booknum; i++) 
			{
				tempList.add( Integer.valueOf(inpFile.next()));	
			}
			userBookID.add(tempList);
		}
		inpFile.close();	
	}

	/**
	 * @throws IOException  If an input or output
	 *                      exception occurred
	 */
	public void writeFile() throws Exception
	{
		/**
		 * Bilgileri Dosyaya yazma islemi
		 */
		File file = new File(fileName);
      	FileWriter out = new FileWriter(file);
      	int i=0;
      	while(i < size())
		{
			out.write(Integer.toString(getID(i)));
			out.write(",");
			out.write(getName(i));
			out.write(",");
			out.write(getSurname(i));
			out.write(",");
			out.write(getPassword(i));
			out.write(",");
			out.write(Integer.toString(userBookNumber.get(i)));
			out.write(",");
			for (int j=0; j < userBookNumber.get(i); j++)
			{
				out.write(Integer.toString(getUserBookID(getID(i),j)));
				out.write(",");	
			}
			out.flush();
			i++;
		}
		out.close();
	}

	/**
	 *Sisteme giris yapmak isteyen kisinin bilgilerini dogrulama
	 */
	public boolean login(int id, String pass)
	{
		int i=0;
		while(i < userID.size())
		{
			if(getID(i)==id)
				if(getPassword(i).equals(pass))
					return true;
			i++;	
		}
		System.out.printf("Giriş Reddedildi\n");		
		return false;
	}

	/**
	 *Kullanicinin adini dondurur
	 */
	public String getName(int index)
	{
		return userName.get(index);
	}

	/**
	 * Kullanicinin sifresini dondurur
	 */
	public String getPassword(int index)
	{
		return userPasssword.get(index);
	}

	/**
	 * Kullanicinin ID'sini dondurur
	 * @param index array list index
	 * @return ID
	 */
	public Integer getID(int index)
	{
		return userID.get(index);
	}

	/**
	 * Kullanicinin soyadini dondurur
	 * @param index Arraylist indexi
	 * @return Surname
	 */
	public String getSurname(int index)
	{
		return userSurname.get(index);
	}

	//

	/**
	 * Kullaniciya ait kitap sayisini dondurur
	 * @param index Arraylist indexi
	 * @return Sahip olunan kitap sayisi
	 */
	public Integer getUserBookNumber(int index)
	{
		return userBookNumber.get(index);
	}
	
	/**
	 *Bir Id ye ait indexi dondurur
	 * @param id kullanici ID
	 * @return İndex
	 */
	public Integer getIDtoIndex(int id)
	{
		int i =0;
		while(i < userID.size())
		{
			if(getID(i)==id)
				return i;
			i++;
		}
		return -1;	
	}	

	/**
	 *Kullanici sifresini yeniden olusturur
	 * @param id Kullanici ID
	 * @param pass Yeni sifre
	 */
	public void setPassword(int id, String pass)
	{
		int i=0;
		while (i < userID.size())
		{
			if(getID(i)==id)
				userPasssword.set(i,pass);

			i++;
		}
	}

	/**
	 *Kutuphaneden kiap kaldirilacagi zaman kisilerin hesabindada dusulur
	 * @param bookId Kitap ID'si
	 */
	public void removeBook(int bookId )
	{
		int i=0;
		while( i < size())
		{
			for (int j=0; j < userBookNumber.get(i); j++)
			{
				if(getUserBookID( getID(i), j ) == bookId )
				{
					returnBook(getID(i),bookId);
				}	
			}
			i++;
		}
	}

	/**
	 *Bir kullanicinin herhangi bir kitabinin id'sine ulasmak icin kullaniliyor
	 * @param id ID
	 * @param index Arraylist indexi
	 * @return Kisiye ait Kitap ID'si
	 */
	public Integer getUserBookID(int id , int index)
	{
		return userBookID.get(getIDtoIndex(id)).get(index);
	}

	/**
	 *Kitap teslim etme islemi
	 * @param id Kisini,n ID'si
	 * @param bookID Kitap ID'si
	 * @return islem Dogru veya yanlis
	 */
	public boolean returnBook(int id, int bookID)
	{
		int i =0;	
		while(i < getUserBookNumber( getIDtoIndex(id) ))
		{
			if(getUserBookID(id,i)==bookID)
			{
				ArrayList<Integer> tempList = new ArrayList<>();
				
				int j=0;
				while(j < userBookNumber.get(getIDtoIndex(id)) )
				{
					if(getUserBookID(id,j)!=bookID)
					{
						tempList.add(getUserBookID(id,j));
					}	
					j++;
				}	
				userBookID.set(getIDtoIndex(id),tempList);
				userBookNumber.set(getIDtoIndex(id),getUserBookNumber( getIDtoIndex(id) )-1);
				System.out.printf("sczbds>\n");
				return true;
			}	
			i++;
		}
		System.out.printf("Kitap teslim etme işlemi başarısız oldu\n");
		return false;	
	}

	/**
	 *Kitap odunc alma islemi
	 * @param id Kisinin ID'si
	 * @param bookID Kitabin ID'si
	 * @return islem Dogru veya yanlisligi
	 */
	public boolean barrowBook(int id, int bookID)
	{
		ArrayList<Integer> tempList = new ArrayList<>();
		
		int j=0;
		while(j < userBookNumber.get(getIDtoIndex(id)) )
		{
			if(getUserBookID(id,j)==bookID)
			{
				System.out.printf("Kitap ödünç alma işlemi basarısız oldu\n");
				return false;
			}	
			tempList.add(getUserBookID(id,j));
			j++;
		}
		tempList.add(bookID);	
		userBookID.set(getIDtoIndex(id),tempList);
		userBookNumber.set(getIDtoIndex(id),getUserBookNumber( getIDtoIndex(id) )+1);
		return true;
	}

	/**
	 *Arraylistin boyutunu dondurur
	 * @return Toplam kisi sayisi
	 */
	public Integer size()
	{
		return userBookID.size();

	}

	/**
	 *Yeni kullanici ekler
	 * @param id Kisi ID
	 * @param name Kisi İsim
	 * @param surname Kisi Soyisim
	 * @return islem Dogru veya yanlisligi
	 */
	public boolean addUser(int id, String name,String surname)
	{
		int i=0;
		while(i < size())
		{
			if(getID(i) ==id)
			{
				System.out.printf("Ayni ID'ye sahip kişi saptandi.\nKullanıcı ekleme başarısız\n");
				return false;
			}
			i++;
		}
		ArrayList<Integer> tempList = new ArrayList<>();
		userID.add(id);
		userName.add(name);
		userSurname.add(surname);
		/**
		 *  Yeni kullanicin sifresi ismi olarak atanir
		 */
		userPasssword.add(name);
		userBookNumber.add(0);
		userBookID.add(tempList);
		return true;

	}
}

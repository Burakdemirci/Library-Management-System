/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
import java.io.*;
import java.util.*;

public class Book 
{
	/**
	 * Kitap verilerini tutan arraylistlerKitap verilerini tutan arraylistler
	 */
	private  ArrayList<String>  bookName = new ArrayList<>();
	private  ArrayList<String>  bookWriter = new ArrayList<>();
	private  ArrayList<Integer> bookCond =new ArrayList<>();
	private  ArrayList<Integer> bookID =new ArrayList<>();
	
	private String fileName ;
	public Book(String fileName1)
	{
		fileName=fileName1;
	}

	/**
	 * @throws IOException  If an input or output
	 *                      exception occurred
	 */
	public void readFile() throws Exception
	{
		File input = new File(fileName);
		Scanner inpFile = new Scanner (input);
		inpFile.useDelimiter(",");
		while (inpFile.hasNext())
		{
			bookID.add( Integer.valueOf(inpFile.next()));
			bookName.add(inpFile.next());
			bookWriter.add(inpFile.next());
			bookCond.add( Integer.valueOf(inpFile.next()));	
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
		 *  Yenilenen bilgilerde eklenerek tekrardan dosyaya yazma islemi
		 */
		File file = new File(fileName);
      	FileWriter out = new FileWriter(file);
      	int i=0;
      	while(i < bookID.size())
		{
			out.write(Integer.toString(bookID.get(i)));
			out.write(",");
			out.write(bookName.get(i));
			out.write(",");
			out.write(bookWriter.get(i));
			out.write(",");
			out.write(Integer.toString(bookCond.get(i)));
			out.write(",");
			out.flush();
			i++;
		}
		out.close();
	}

	/**
	 * Staff tarafindan yeni kitap eklenecegi zaman kullanilacak metod
	 * @param writerName1 Yazar ismi
	 * @param bookName1 Kitap İsmi
	 * @param bookID1 Kitap ID'si
	 * @return İslem basarili veya degil
	 */
	public boolean addBook(String writerName1, String bookName1, int bookID1)
	{
		if(checkBookID(bookID1))
		{
			bookID.add(bookID1);
			bookName.add(bookName1);
			bookWriter.add(writerName1);
			/**
			 * Yeni eklenen kitabin uygunluk durumu herzaman 1 dir.
			 */
			bookCond.add(1);
			return true;
		}
		else{
			System.out.printf("Aynı ID'ye sahip kitap tespit edildi.\nYeni kitap eklenemedi\n");	
		}
		return false;
	}

	/**
	 *  Verieln ID gercekten bir kitaba karsilik geliyormu bulma islemi
	 * @param bookID1 Kitap ID
	 * @return İslem basarili veya degil
	 */
	public boolean checkBookID(int bookID1)
	{
		int i=0;
		while(i < bookID.size())
		{
			if(bookID.get(i)==bookID1)
				return false;
			i++;
		}
		return true;
	}
	/**
	 * Butun kitaplari ekrana bastirma
	 */
	public void seeAllBook()
	{
		int i=0;
		System.out.printf("ID\n");
		while(i < bookID.size())
		{
			System.out.printf(bookID.get(i)+"  "+bookName.get(i)+" - "+bookWriter.get(i)+" ");
			if(bookCond.get(i)==1)
			{	
				System.out.printf("-> Uygun\n");
			}
			else
			{	
				System.out.printf("-> Uygun Değil\n");		
			}
			i++;
		}
	}

	/**
	 * Staff tafafindan kutuphaneden kitap cikarma islemi
	 * @param bookID1 Kitap ID
	 * @return İslem basarili veya degil
	 */
	public boolean removeBook(int bookID1)
	{
		int i=0;
		while(i < bookID.size())
		{
			if(bookID.get(i)==bookID1)
			{
				bookName.remove(i);
				bookWriter.remove(i);
				bookCond.remove(i);
				bookID.remove(i);
				return true;
			}
			i++;
		}
		System.out.printf("Verilen ID'ye ait kitap bulunamadı.\nKitap kaldırma işlemi başarısız\n");	
		return false;
	}

	/**
	 * Kitap odunc verme islemi
	 * @param bookID1 Kitap ID
	 * @return İslem basariilii veya degil
	 */
	public boolean barrowBook(int bookID1)
	{
		int i=0;
		while(i < bookID.size())
		{
			if(bookID.get(i)==bookID1)
			{
				bookCond.set(i,0);
				return true;
			}
			i++;
		}
		System.out.printf("Kitap ödünç alınamadı\n\n");
		return false;
	}
	/**
	 *  Ödunc verilen kitabi geri alma
	 * @param bookID1 Kitap ID
	 * @return İslem basarili veya degil
	 */
	public boolean returnBook(int bookID1)
	{
		int i=0;
		while(i < bookID.size())
		{
			if(bookID.get(i)==bookID1)
			{
				bookCond.set(i,1);
				return true;
			}
			i++;
		}
		System.out.printf("Kitap iade edilemedi\n");
		return false;
	}


	/**
	 * Kitap ismi Dondurme
	 * @param bookID1 Kitap ID
	 * @return Kitap İsmi
	 */
	public String getBookName(int bookID1)
	{
		int i=0;
		String n="";
		while(i < bookID.size())
		{
			if(bookID.get(i)==bookID1)
			{	
				return bookName.get(i);
			}
			i++;
		}
		return n;
	}

	/**
	 *  Kitap yazarini dondurme
	 * @param bookID1 Kitap ID
	 * @return Kitap YAzari
	 */
	public String getBookWriter(int bookID1)
	{
		int i=0;
		String n="";
		while(i < bookID.size())
		{
			if(bookID.get(i)==bookID1)
			{
				return bookWriter.get(i);
			}
			i++;
		}	
		return n;
	}
	/**
	 *  Kitabin durumunu dondurme
	 * @param bookID1 Kitap ID
	 * @return Kitabin durumu( odunc verilmismi yoksa kutuphanedemi )
	 */
	public Integer getBookCondition(int bookID1)
	{
		int i=0;
		int n = -1;
		while(i < bookID.size())
		{
			if(bookID.get(i)==bookID1)
			{	
				return bookCond.get(i);
			}
			i++;
		}
		return n;	
	}

}
/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
import java.io.*;
import java.util.*;

public class Staff implements Person
{ 
	private  ArrayList<String> staffName = new ArrayList<>();
	private  ArrayList<String> staffPasssword = new ArrayList<>();
	private  ArrayList<String> staffSurname = new ArrayList<>();
	private  ArrayList<Integer> staffID = new ArrayList<>();

	private String fileName ;
	public Staff(String fileName1)
	{
		fileName=fileName1;
	}

	/**
	 * @throws IOException  If an input or output
	 *                      exception occurred
	 */
	public void readFile() throws Exception
	{
		/**
		 * Bilgileri dosyadan okuma
		 */
		File input = new File(fileName);
		Scanner inpFile = new Scanner (input);
		inpFile.useDelimiter(",");
		while (inpFile.hasNext())
		{
			staffID.add( Integer.valueOf(inpFile.next()));
			staffName.add(inpFile.next());
			staffSurname.add(inpFile.next());
			staffPasssword.add(inpFile.next());	
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
      	while(i < staffID.size())
		{
			out.write(Integer.toString(getID(i)));
			out.write(",");
			out.write(getName(i));
			out.write(",");
			out.write(getSurname(i));
			out.write(",");
			out.write(getPassword(i));
			out.write(",");
			out.flush();
			i++;
		}
		out.close();
	}

	/**
	 *Sisteme giris yapmak isteyen kisinin bilgilerini dogrulama
	 * @param id Kisi ID
	 * @param pass Kisi Şifre
	 * @return Giris basarili veya degil
	 */
	public boolean login(int id, String pass)
	{
		int i=0;
		while(i < staffID.size())
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
	 * Verilen indexteki ismi dondurur
	 * @param index Array list index
	 * @return İsim dondurur
	 */
	public String getName(int index)
	{
		return staffName.get(index);
	}
	

	/**
	 * Verilen indexteki soyismi dondurur
	 * @param index Array list index
	 * @return Soyisim dodurur
	 */
	public String getSurname(int index)
	{
		return staffSurname.get(index);
	}

	/**
	 *Verilen indexteki sifreyi dondurur
	 * @param index Arraylist indexi
	 * @return Şİfre dondurur
	 */
	public String getPassword(int index)
	{
		return staffPasssword.get(index);
	}

	/**
	 * Verilen indexteki ID'yi dondurur
	 * @param index Arraylist indexi
	 * @return ID dondurur
	 */
	public Integer getID(int index)
	{
		return staffID.get(index);
	}


	/**
	 * Bir Id ye ait indexi dondurur
	 * @param id Kisi ID
	 * @return Arraylist  index'i
	 */
	public Integer getIDtoIndex(int id)
	{
		int i =0;
		while(i < staffID.size())
		{
			if(getID(i)==id)
				return i;
			i++;
		}
		return -1;	
	}

	/**
	 *Yeni sifre olusturma
	 * @param id Kisi ID
	 * @param pass  Yrni sifre
	 */
	public void setPassword(int id, String pass)
	{
		int i=0;
		while (i < staffID.size())
		{
			if(getID(i)==id)
				staffPasssword.set(i,pass);

			i++;
		}
	}
}
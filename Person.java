/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
public interface Person
{
	/**
	 *  Interface
	 * @throws Exception readFile, writeFile
	 */
	public void readFile() throws Exception ;
	public void writeFile() throws Exception ;
	public Integer getIDtoIndex(int id);
	public void setPassword(int id, String pass);
	public boolean login(int id, String pass);
	public String getName(int index);
	public String getSurname(int index);
	public String getPassword(int index);
	public Integer getID(int index);
}

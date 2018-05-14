package lcsh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.opencsv.CSVReader;

/*
 * This class loads the csv's into an ArrayList of well-formed LCSH objects. The code can be easily modified to exclude
 * certain LCSH fields, or built on, etc. It also saves the ArrayList so it can be loaded more quickly than having to 
 * rebuild from the csv's.
 */

public class LoadCSV {

	// an ArrayList containing of all subject headings with their fields assigned,
	// ordered sequentially by uri (lowest to highest)
	public static ArrayList<LCSH> lcshList = new ArrayList<LCSH>();

	public static void main(String[] args) {

		// load the serialized LCSH ArrayList
		lcshList = loadLCSH("./", "LCSH_save");

		// generate lcshList from csvs
		/*
		 * loadSubjectHeadings(); loadNarrowerHeadings(); loadBroaderHeadings();
		 * loadRelatedHeadings(); loadVariantTerms(); loadSubdivisions(); //save the
		 * structure saveLCSH("LCSH_save");
		 */

	}

	public static void loadSubjectHeadings() {
		FileReader f = null;
		CSVReader c = null;

		String[] HEADING_MAP = { "sh_uri", "sh_name", "sh_count" };

		try {
			f = new FileReader("../lcsh_csvs/subjectheadings.csv");
			c = new CSVReader(f, ';');

			String[] nextHeading = null;
			int counter = 0;

			while ((nextHeading = c.readNext()) != null) {
				System.out.println("subjectheadings: " + counter);

				if (counter == 0) {
					++counter;
					continue;
				}
				/*
				 * System.out.println(nextHeading[0]); System.out.println(nextHeading[1]);
				 * System.out.println(nextHeading[2]);
				 */

				lcshList.add(new LCSH(nextHeading[1], nextHeading[0], Integer.parseInt(nextHeading[2])));

				++counter;
			}
			c.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadNarrowerHeadings() {
		FileReader f = null;
		CSVReader c = null;

		String[] HEADING_MAP = { "nh_sh_uri", "nh_uri" };

		try {
			f = new FileReader("../lcsh_csvs/narrowerheadings.csv");
			c = new CSVReader(f, ';');

			String[] nextHeading = null;
			int csvCounter = 0;
			int lcshCounter = 0;

			while ((nextHeading = c.readNext()) != null) {

				System.out.println("narrowerheadings: " + csvCounter);

				if (csvCounter == 0) {
					++csvCounter;
					continue;
				}

				// find the lcshList heading whose uri equals the nh_sh_uri column in csv
				while (!nextHeading[0].equals(lcshList.get(lcshCounter).getUri())) {
					System.out.println(lcshList.get(lcshCounter).getUri());
					++lcshCounter;
					if (lcshCounter >= lcshList.size()) {
						System.out.println(nextHeading[0] + " not found");
						System.exit(-1);
					}
				}
				// now add the narrower heading ... (the nh_uri column in csv)
				for (int i = 0; i < lcshList.size(); i++) {
					if (lcshList.get(i).getUri().equals(nextHeading[1])) {

						System.out.println(lcshCounter);
						System.out.println(lcshList.get(i).getUri());

						lcshList.get(lcshCounter).addNarrowerHeading(lcshList.get(i));
						continue;
					}
				}
				++csvCounter;
			}
			c.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadBroaderHeadings() {

		FileReader f = null;
		CSVReader c = null;

		String[] HEADING_MAP = { "bh_sh_uri", "bh_uri" };

		try {
			f = new FileReader("../lcsh_csvs/broaderheadings.csv");
			c = new CSVReader(f, ';');

			String[] nextHeading = null;
			int csvCounter = 0;
			int lcshCounter = 0;

			while ((nextHeading = c.readNext()) != null) {

				System.out.println("broaderheadings: " + csvCounter);

				if (csvCounter == 0) {
					++csvCounter;
					continue;
				}

				// find the lcshList heading whose uri equals the nh_sh_uri column in csv
				while (!nextHeading[0].equals(lcshList.get(lcshCounter).getUri())) {
					++lcshCounter;
					if (lcshCounter > lcshList.size()) {
						System.out.println(nextHeading[0] + " not found");
						System.exit(-1);
					}
				}
				// now add the broader heading ... (the bh_uri column in csv)
				for (int i = 0; i < lcshList.size(); i++) {
					if (lcshList.get(i).getUri().equals(nextHeading[1])) {

						System.out.println(lcshCounter);
						System.out.println(lcshList.get(i).getUri());

						lcshList.get(lcshCounter).addBroaderHeading(lcshList.get(i));
						continue;
					}
				}
				++csvCounter;
			}
			c.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadRelatedHeadings() {

		FileReader f = null;
		CSVReader c = null;

		String[] HEADING_MAP = { "rh_sh_uri", "rh_uri" };

		try {
			f = new FileReader("../lcsh_csvs/relatedheadings.csv");
			c = new CSVReader(f, ';');

			String[] nextHeading = null;
			int csvCounter = 0;
			int lcshCounter = 0;

			while ((nextHeading = c.readNext()) != null) {

				System.out.println("relatedheadings: " + csvCounter);

				if (csvCounter == 0) {
					++csvCounter;
					continue;
				}

				// find the lcshList heading whose uri equals the rh_sh_uri column in csv
				while (!nextHeading[0].equals(lcshList.get(lcshCounter).getUri())) {
					++lcshCounter;
					if (lcshCounter > lcshList.size()) {
						System.out.println(nextHeading[0] + " not found");
						System.exit(-1);
					}
				}
				// now add the related heading ... (the rh_uri column in csv)
				for (int i = 0; i < lcshList.size(); i++) {
					if (lcshList.get(i).getUri().equals(nextHeading[1])) {

						System.out.println(lcshCounter);
						System.out.println(lcshList.get(i).getUri());

						lcshList.get(lcshCounter).addRelatedHeading(lcshList.get(i));
						continue;
					}
				}
				++csvCounter;
			}
			c.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadVariantTerms() {
		FileReader f = null;
		CSVReader c = null;

		try {
			f = new FileReader("../lcsh_csvs/variantTerms.csv");
			c = new CSVReader(f, ';');

			String[] nextHeading = null;
			int csvCounter = 0;
			int lcshCounter = 0;

			while ((nextHeading = c.readNext()) != null) {

				System.out.println("variantTerms: " + csvCounter);

				if (csvCounter == 0) {
					++csvCounter;
					continue;
				}

				// find the lcshList heading whose uri equals the vt_sh_uri column in csv
				while (!nextHeading[0].equals(lcshList.get(lcshCounter).getUri())) {
					++lcshCounter;
					if (lcshCounter > lcshList.size()) {
						System.out.println(nextHeading[0] + " not found");
						System.exit(-1);
					}
				}
				// now add the variant term
				lcshList.get(lcshCounter).addVariantTerm(nextHeading[1]);

				++csvCounter;
			}
			c.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadSubdivisions() {
		FileReader f = null;
		CSVReader c = null;

		try {
			f = new FileReader("../lcsh_csvs/subdivisions.csv");
			c = new CSVReader(f, ';');

			String[] nextHeading = null;
			int csvCounter = 0;
			int lcshCounter = 0;

			while ((nextHeading = c.readNext()) != null) {

				System.out.println("subdivisions: " + csvCounter);

				if (csvCounter == 0) {
					++csvCounter;
					continue;
				}

				// find the lcshList heading whose uri equals the sd_sh_uri column in csv
				while (!nextHeading[1].equals(lcshList.get(lcshCounter).getUri())) {
					++lcshCounter;
					if (lcshCounter > lcshList.size()) {
						System.out.println(nextHeading[1] + " not found");
						System.exit(-1);
					}
				}
				// now add the subdivision ... (the sd_uri column in csv)
				for (int i = 0; i < lcshList.size(); i++) {
					if (lcshList.get(i).getUri().equals(nextHeading[2])) {

						System.out.println(lcshCounter);
						System.out.println(lcshList.get(i).getUri());

						lcshList.get(lcshCounter).addSubdivision(lcshList.get(i));
						continue;
					}
				}
				++csvCounter;
			}
			c.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Loads the serialized LCSH ArrayList (lcshList)
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<LCSH> loadLCSH(String filepath, String filename) {

		ArrayList<LCSH> lcshList = new ArrayList<LCSH>();

		File f2 = new File(filepath);
		File[] filesList2 = f2.listFiles();

		for (int j = 0; j < f2.listFiles().length; j++) {
			if (filesList2[j].getName().equals(filename)) {
				System.out.println("recovering lcshList ...");
				try {
					FileInputStream fis = new FileInputStream(filename);
					ObjectInputStream ois = new ObjectInputStream(fis);
					lcshList = (ArrayList<LCSH>) ois.readObject();
					ois.close();
					fis.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					return null;
				} catch (ClassNotFoundException c) {
					System.out.println("Class not found");
					c.printStackTrace();
					return null;
				}

				System.out.println("success.");
			}
		}
		return lcshList;
	}

	/*
	 * saves the built lcshList
	 */
	public static void saveLCSH(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lcshList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
